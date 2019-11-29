package com.groupnine.macramicia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public Model renderErrorPage(HttpServletRequest request, Model model) {

        String errormsg = "";

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer statusCode = Integer.valueOf(status.toString());

        switch (statusCode) {
            case 400: { errormsg = "HTTP Error Code: 400 - Bad Request"; break; }
            case 401: { errormsg = "HTTP Error Code: 401 - Unauthorized"; break; }
            case 404: { errormsg = "HTTP Error Code: 404 - Resource not found"; break; }
            case 500: { errormsg = "HTTP Error Code 500 - Internal Server Error"; break; }
        }

        model.addAttribute("errormsg", errormsg);
        model.addAttribute("statuscode", statusCode);

        return model;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
