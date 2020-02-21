package com.irs.springmvcexporterwebapp.presentacion.views.administracion;

import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchOutput;
import com.irs.springmvcexporterwebapp.negocio.vo.UsuarioVO;
import com.irs.springmvcexporterwebapp.presentacion.ConstPresentacion;
import com.irs.springmvcexporterwebapp.presentacion.views.AbstractCsvView;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("usuarioListCsvView")
public class UsuarioListCsvView extends AbstractCsvView {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioListCsvView.class);
    
    public static final DateFormat DATE_FORMAT_DEFAULT = new SimpleDateFormat("dd/MM/yyyy");
     
    public UsuarioListCsvView() {
        super();
    }

    @Override
    protected String buildFileName(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getMessageSourceAccessor().getMessage("csv.fileName.usuarioList");
    }

    @Override
    protected void buildCsv(Map<String, Object> model, ByteArrayOutputStream baos, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SearchOutput<UsuarioVO> searchOutput = (SearchOutput<UsuarioVO>) 
                model.get(ConstPresentacion.USUARIO_LIST_KEY);
        List<UsuarioVO> usuarios = (List<UsuarioVO>) searchOutput.getSearchResult().getResult();
        
        StringBuilder sb = new StringBuilder();
        
        CSVFormat format = CSVFormat.DEFAULT.withDelimiter(';');
                        
        //CSVPrinter printer = new CSVPrinter(sb, CSVFormat.DEFAULT);
        CSVPrinter printer = new CSVPrinter(sb, format);
        
        printer.printRecord("nif", "nombre", "apellido1", "apellido2", "rol", "fechaAlta");
        
	if (CollectionUtils.isNotEmpty(usuarios)) {
            for (UsuarioVO u : usuarios) {
                printer.printRecord(u.getNif(), 
                        u.getNombre(), 
                        u.getApellido1(), 
                        u.getApellido2(), 
                        u.getRol().getRol(), 
                        DATE_FORMAT_DEFAULT.format(u.getFechaAlta()));
                //printer.println();
            }
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug(sb.toString());
        }
        
        baos.write(sb.toString().getBytes());

    }
}
