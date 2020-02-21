package com.irs.springmvcexporterwebapp.presentacion.views.administracion;

import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchOutput;
import com.irs.springmvcexporterwebapp.negocio.vo.UsuarioVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

@Component("usuarioListXmlView")
public class UsuarioListXmlView extends MappingJackson2XmlView {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioListXmlView.class);
     
    public UsuarioListXmlView() {
        super();
    }

    @Override
    protected Object filterModel(Map<String, Object> model) {
       Map<String, Object> result = new HashMap<String, Object>(model.size());
        
        LOG.debug("ANTES DE FILTRAR");
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            LOG.debug("---->" + entry.getKey());
        }
                
        if (model.containsKey("usuarioSearchCommand")) {
            result.put("usuarioSearchCommand", model.get("usuarioSearchCommand"));
        }
        if (model.containsKey("usuarioList")) {
            SearchOutput<UsuarioVO> searchOutput = (SearchOutput<UsuarioVO>) model.get("usuarioList"); 
            List<UsuarioVO> usuarios = (List<UsuarioVO>) searchOutput.getSearchResult().getResult();
            result.put("usuarioList", usuarios);
        }
        
        LOG.debug("DESPUES DE FILTRAR");
        for (Map.Entry<String, Object> entry : result.entrySet()) {
            LOG.debug("---->" + entry.getKey());
        }
        
        return result;
    }
}
