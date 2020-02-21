package com.irs.springmvcexporterwebapp.presentacion.controllers.administracion;

import com.irs.springmvcexporterwebapp.negocio.servicios.RolService;
import com.irs.springmvcexporterwebapp.negocio.servicios.UsuarioService;
import com.irs.springmvcexporterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchConfig;
import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchInput;
import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchOutput;
import com.irs.springmvcexporterwebapp.negocio.vo.FilterUsuarioVO;
import com.irs.springmvcexporterwebapp.negocio.vo.RolVO;
import com.irs.springmvcexporterwebapp.negocio.vo.UsuarioVO;
import com.irs.springmvcexporterwebapp.presentacion.ConstPresentacion;
import com.irs.springmvcexporterwebapp.presentacion.commands.administracion.UsuarioSearchCommand;
import com.irs.springmvcexporterwebapp.presentacion.controllers.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"usuarioSearchCommand", "searchExecuted", "page"})
public class UsuarioSearchController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioSearchController.class);

    private static final String FORMATS_REG_EXP = ConstPresentacion.EXPORT_FORMAT_PDF + 
            "|" + ConstPresentacion.EXPORT_FORMAT_XSLT_FO + 
            "|" + ConstPresentacion.EXPORT_FORMAT_XLS + 
            "|" + ConstPresentacion.EXPORT_FORMAT_CSV +
            "|" + ConstPresentacion.EXPORT_FORMAT_JSON +
            "|" + ConstPresentacion.EXPORT_FORMAT_XML;
            
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private SearchConfig searchConfig;

    /**
     * Constructor.
     */
    public UsuarioSearchController() {
        super();
    }

    @ModelAttribute(name = ConstPresentacion.ROLES)
    public List<RolVO> populateRoles() throws ServiceException {
        return rolService.findAll();
    }

    @RequestMapping(value = "/administracion/usuarioSearchLoad.htm", method = RequestMethod.GET)
    public String usuarioSearchLoadHandler(ModelMap model,
            HttpServletRequest request) {
        UsuarioSearchCommand command = new UsuarioSearchCommand();

        model.addAttribute(ConstPresentacion.COMMAND_USUARIO_SEARCH_KEY, command);
        model.addAttribute(ConstPresentacion.SEARCH_EXECUTED_KEY, Boolean.FALSE);
        model.addAttribute(ConstPresentacion.PAGE_KEY, -1);

        return ConstPresentacion.VIEW_NAME_USUARIO_SEACH_KEY;
    }

    @RequestMapping(value = "/administracion/usuarioSearch.htm")
    public ModelAndView usuarioSearchHandler(
            @ModelAttribute("usuarioSearchCommand") UsuarioSearchCommand command,
            ModelMap model, HttpServletRequest request) throws ServiceException {
        addSearchDataResult(model, command, request, null);
        return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_SEACH_KEY, model);
    }

    @RequestMapping(value = "/administracion/usuarioSearchReload.htm", method = RequestMethod.GET)
    public ModelAndView usuarioSearchReloadHandler(
            @ModelAttribute("usuarioSearchCommand") UsuarioSearchCommand command,
            ModelMap model, HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession(false);
        if (isSearchExecuted(session)) {
            addSearchDataResult(model, command, null, session);
        }

        return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_SEACH_KEY, model);
    }

    @RequestMapping(value="/administracion/usuarioListExporter.htm", method=RequestMethod.GET)
    public ModelAndView usuarioListExporterHandler(
            @ModelAttribute("usuarioSearchCommand") UsuarioSearchCommand command,
            @RequestParam(value="format", required=true) String format,
            ModelMap model, HttpServletRequest request) throws ServiceException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Formato de exportación '{}'", format);
        }
        
        if (isFormatValid(format)) {
            addSearchDataResult(model, command, null, null);
            String viewName = String.format("usuarioList%1$sView", StringUtils.capitalize(format.trim().toLowerCase()));
            if (LOG.isDebugEnabled()) {
                LOG.debug("View name '{}'", viewName);
            }
            return new ModelAndView(viewName, model);
        } else {
            // Añadir error de formato para mostrar el mensaje
            return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_SEACH_KEY, model);
        }
    }
          
    private void addSearchDataResult(ModelMap model,
            UsuarioSearchCommand command,
            HttpServletRequest request, HttpSession session) throws ServiceException {
        FilterUsuarioVO filter = (FilterUsuarioVO) toVo(command, FilterUsuarioVO.class);
        int page = getPage(request, session);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Página '{}', Filtro '{}'", page, filter);
        }

        SearchOutput<UsuarioVO> searchOutput = usuarioService.findByFilter(
                new SearchInput<FilterUsuarioVO>(searchConfig, page, filter));
        
        model.addAttribute(ConstPresentacion.SEARCH_EXECUTED_KEY, Boolean.TRUE);
        model.addAttribute(ConstPresentacion.PAGE_KEY, page);
        model.addAttribute(ConstPresentacion.USUARIO_LIST_KEY, searchOutput);
    }

    /**
     * Indica si se ha realizado una busqueda (true) o no (false).
     *
     * @param session Objeto session del usuario.
     * @return true si se ha realizado una busqueda, false en caso contrario.
     */
    private boolean isSearchExecuted(HttpSession session) {
        Boolean searchExecuted = getBooleanAttribute(session,
                ConstPresentacion.SEARCH_EXECUTED_KEY, null);

        return (searchExecuted != null && searchExecuted.booleanValue());
    }

    private int getPage(HttpServletRequest request, HttpSession session) {
        if (request != null) {
            return ServletRequestUtils.getIntParameter(request, ConstPresentacion.REQUEST_PAGE_KEY, SearchConfig.PAGE_NUMBER_DEFAULT);
        } else if (session != null) {
            return getIntegerAttribute(session, ConstPresentacion.PAGE_KEY, SearchConfig.PAGE_NUMBER_DEFAULT);
        } else {
            return -1;
        }
    }
    
    private Boolean getBooleanAttribute(HttpSession session, String attributeName, Boolean defaultValue) {
        Boolean result = (Boolean) session.getAttribute(attributeName);
        if (result == null) {
            LOG.warn("Valor del atributo '{0}' en session null", attributeName);
            result = defaultValue;
        }

        return result;
    }
     
    private Integer getIntegerAttribute(HttpSession session, String attributeName, Integer defaultValue) {
        try {
            return (Integer) session.getAttribute(attributeName);
	} catch (NumberFormatException e) {
            LOG.warn("Valor del atributo '{0}' en session inválido", attributeName, e);
            return defaultValue;
	}
    }

    private boolean isFormatValid(String format) {
        if (format != null && format.matches(FORMATS_REG_EXP)) {
            return true;
        } else {
            return false;
        }
    }
}
