package com.irs.springmvcexporterwebapp.presentacion.views;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.AbstractView;

public abstract class AbstractXsltFoView extends AbstractView {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractXsltFoView.class);
     
    private static final String EXTENSION = ".xsl";
           
    private String xslTemplate;
     
    public AbstractXsltFoView(String xslTemplate) {
        setContentType("application/pdf");
        this.xslTemplate = xslTemplate;
    }

    public String getXslTemplate() {
        return xslTemplate;
    }

    public void setXslTemplate(String xslTemplate) {
        this.xslTemplate = xslTemplate;
    }
    
    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }
    
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Resource resource = getXslResource(this.xslTemplate, request);
        Source xslSource = getXslSource(this.xslTemplate, request, resource);
        
        StreamSource xmlSource = new StreamSource();
        buildXml(model, xmlSource);
        
        FopFactory fopFactory = FopFactory.newInstance();
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        
        //URL baseURL = resource.createRelative("").getURL();
        
        foUserAgent.setBaseURL(resource.getURI().toString());
        
        ByteArrayOutputStream baos = createTemporaryOutputStream();
        
        //Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, response.getOutputStream());
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, baos);
        
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslSource);
        
        Result result = new SAXResult(fop.getDefaultHandler());
        
        transformer.transform(xmlSource, result);

        writeToResponse(response, baos);
        //response.getOutputStream().flush();
        //response.getOutputStream().close();
    }
  
    private Resource getXslResource(String url, HttpServletRequest request) {
        LocalizedResourceHelper helper = new LocalizedResourceHelper(getApplicationContext());
        Locale userLocale = RequestContextUtils.getLocale(request);
        
        return helper.findLocalizedResource(url, EXTENSION, userLocale);
    }
    
    private Source getXslSource(String url, HttpServletRequest request, Resource resource) {
        /*
        LocalizedResourceHelper helper = new LocalizedResourceHelper(getApplicationContext());
        Locale userLocale = RequestContextUtils.getLocale(request);
        Resource resource = helper.findLocalizedResource(url, EXTENSION, userLocale);
        */
        if (LOG.isDebugEnabled()) {
            LOG.debug("Cargando la XSLT stylesheet '{}'", resource);
        }

        try {
            return new StreamSource(resource.getInputStream());
        } catch (IOException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("Error cargando la XSLT stylesheet '{}': {}", url, e.getMessage());
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("Crea la XSLT stylesheet desde cero");
            }

            return new StreamSource();
        }
    }
    
    protected abstract void buildXml(Map<String, Object> model, StreamSource xmlSource) throws Exception;
}
