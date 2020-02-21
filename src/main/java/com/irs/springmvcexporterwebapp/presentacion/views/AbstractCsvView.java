package com.irs.springmvcexporterwebapp.presentacion.views;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;

public abstract class AbstractCsvView extends AbstractView {

    private static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    private static final String ATTACHMENT_FILENAME_FORMAT = "attachment;filename=%s";
    
    public AbstractCsvView() {
        super();
        setContentType("text/csv");
    }
    
    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }
    
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
	response.setHeader(HEADER_CONTENT_DISPOSITION, 
                String.format(ATTACHMENT_FILENAME_FORMAT, buildFileName(model, request, response)));
		
	ByteArrayOutputStream baos = createTemporaryOutputStream();
		
	buildCsv(model, baos, request, response);
		
	// Flush to HTTP response.
	writeToResponse(response, baos);
    }
    
    protected abstract String buildFileName(Map<String, Object> model, 
            HttpServletRequest request, HttpServletResponse response) throws Exception;
    
    protected abstract void buildCsv(Map<String, Object> model, 
            ByteArrayOutputStream baos, HttpServletRequest request, 
            HttpServletResponse response) throws Exception;
}
