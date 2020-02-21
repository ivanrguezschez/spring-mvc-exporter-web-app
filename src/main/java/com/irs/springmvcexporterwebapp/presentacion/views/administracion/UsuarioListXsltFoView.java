package com.irs.springmvcexporterwebapp.presentacion.views.administracion;

import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchOutput;
import com.irs.springmvcexporterwebapp.negocio.vo.RolVO;
import com.irs.springmvcexporterwebapp.negocio.vo.UsuarioVO;
import com.irs.springmvcexporterwebapp.presentacion.ConstPresentacion;
import com.irs.springmvcexporterwebapp.presentacion.commands.administracion.UsuarioSearchCommand;
import com.irs.springmvcexporterwebapp.presentacion.views.AbstractXsltFoView;
import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("usuarioListXsltfoView")
public class UsuarioListXsltFoView extends AbstractXsltFoView {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioListXsltFoView.class);
     
    public static final DateFormat DATE_FORMAT_DEFAULT = new SimpleDateFormat("dd/MM/yyyy");
    
    public UsuarioListXsltFoView() {
        super("classpath:xslt/usuarioListXsltFoView");
    }
   
    @Override
    protected void buildXml(Map<String, Object> model, StreamSource xmlSource) throws Exception {
        StringBuilder xml = new StringBuilder();
        
        final UsuarioSearchCommand command = (UsuarioSearchCommand) model.get(ConstPresentacion.COMMAND_USUARIO_SEARCH_KEY);
        List<RolVO> roles = (List<RolVO>)  model.get(ConstPresentacion.ROLES);
            
	RolVO rol = (RolVO) CollectionUtils.find(roles, new Predicate() {
            public boolean evaluate(Object object) {
                if (command.getRol() != null) {
                    return ((RolVO) object).getId().intValue() == command.getRol();
                }
                
                return false;
            }
        });
        
        SearchOutput<UsuarioVO> searchOutput = (SearchOutput<UsuarioVO>) 
                model.get(ConstPresentacion.USUARIO_LIST_KEY);
                
        xml.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
        xml.append("<usuario-list>\n");
        xml.append("\t<list-criteria>\n");
        xml.append("\t\t<nif>" + (StringUtils.isNotEmpty(command.getNif()) ? command.getNif() : "") + "</nif>\n");
        xml.append("\t\t<nombre>" + (StringUtils.isNotEmpty(command.getNombre()) ? command.getNombre() : "") + "</nombre>\n");
        xml.append("\t\t<apellido1>" + (StringUtils.isNotEmpty(command.getApellido1()) ? command.getApellido1() : "") + "</apellido1>\n");
        xml.append("\t\t<apellido2>" + (StringUtils.isNotEmpty(command.getApellido2()) ? command.getApellido2() : "") + "</apellido2>\n");
        xml.append("\t\t<rol>" + (rol != null ? rol.getRol() : "") + "</rol>\n");
        xml.append("\t</list-criteria>\n");
        
        xml.append("\t<list-result>\n");
        List<UsuarioVO> usuarios = (List<UsuarioVO>) searchOutput.getSearchResult().getResult();
	if (CollectionUtils.isNotEmpty(usuarios)) {
            for (UsuarioVO u : usuarios) {
                xml.append("\t\t<result-item>\n");
                xml.append("\t\t\t<nif>" +  u.getNif() + "</nif>\n");
                xml.append("\t\t\t<nombre>" +  u.getNombre() + "</nombre>\n");
                xml.append("\t\t\t<apellido1>" +  u.getApellido1() + "</apellido1>\n");
                xml.append("\t\t\t<apellido2>" +  u.getApellido2() + "</apellido2>\n");
                xml.append("\t\t\t<rol>" +  u.getRol().getRol() + "</rol>\n");
                xml.append("\t\t\t<fecha-alta>" +  DATE_FORMAT_DEFAULT.format(u.getFechaAlta()) + "</fecha-alta>\n");
                xml.append("\t\t</result-item>\n");
            }
        }
        xml.append("\t</list-result>\n");
        
        xml.append("\t<params-config>\n");
        xml.append("\t\t<param-config>\n");
        xml.append("\t\t\t<name>organismo.nivel.1.nombre</name>\n");
        xml.append("\t\t\t<value>DENOMINACIÓN\\nORGANISMO\\nDE PRIMER NIVEL</value>\n");
        xml.append("\t\t</param-config>\n");
        xml.append("\t\t<param-config>\n");
        xml.append("\t\t\t<name>organismo.nivel.1.via.nombre</name>\n");
        xml.append("\t\t\t<value>AAAAA AAAAAAAAAA, 999</value>\n");
        xml.append("\t\t</param-config>\n");
        xml.append("\t\t<param-config>\n");
        xml.append("\t\t\t<name>organismo.nivel.1.via.cp</name>\n");
        xml.append("\t\t\t<value>99999</value>\n");
        xml.append("\t\t</param-config>\n");
        xml.append("\t\t<param-config>\n");
        xml.append("\t\t\t<name>organismo.nivel.1.via.provincia</name>\n");
        xml.append("\t\t\t<value>AAAAAA</value>\n");
        xml.append("\t\t</param-config>\n");
        xml.append("\t\t<param-config>\n");
        xml.append("\t\t\t<name>organismo.nivel.1.telefono</name>\n");
        xml.append("\t\t\t<value>99 999 99 99</value>\n");
        xml.append("\t\t</param-config>\n");
        xml.append("\t\t<param-config>\n");
        xml.append("\t\t\t<name>organismo.nivel.1.fax</name>\n");
        xml.append("\t\t\t<value>99 999 99 99</value>\n");
        xml.append("\t\t</param-config>\n");
        xml.append("\t\t<param-config>\n");
        xml.append("\t\t\t<name>organismo.nivel.2.nombre</name>\n");
        xml.append("\t\t\t<value>DENOMINACIÓN ORGANISMO DE SEGUNDO NIVEL</value>\n");
        xml.append("\t\t</param-config>\n");
        xml.append("\t\t<param-config>\n");
        xml.append("\t\t\t<name>organismo.nivel.3.nombre</name>\n");
        xml.append("\t\t\t<value>DENOMINACIÓN ORGANISMO DE TERCER NIVEL</value>\n");
        xml.append("\t\t</param-config>\n");
        xml.append("\t</params-config>\n");
        xml.append("</usuario-list>");

        if (LOG.isDebugEnabled()) {
            LOG.debug(xml.toString());
        }
        
        xmlSource.setInputStream(new ByteArrayInputStream(xml.toString().getBytes()));
    }
}
