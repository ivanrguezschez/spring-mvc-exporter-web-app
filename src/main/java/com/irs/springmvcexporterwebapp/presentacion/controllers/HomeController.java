package com.irs.springmvcexporterwebapp.presentacion.controllers;


import com.irs.springmvcexporterwebapp.presentacion.ConstPresentacion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller de home.
 *
 * @author IRS
 * @version 1.0.0
 */
@Controller
public class HomeController extends BaseController {

    /**
     * Constructor por defecto.
     */
    public HomeController() {
        super();
    }

    /**
     * Metodo que atiende peticiones de la url /home.htm para acceder a la
     * pagina de home.
     */
    @RequestMapping(value = "/home.htm", method = RequestMethod.GET)
    public String homeHandler() {
        return ConstPresentacion.VIEW_NAME_HOME_KEY;
    }
}
