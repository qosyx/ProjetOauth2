package com.github.arocketman.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class HomeController {


    @GetMapping(value = "/")
    public String index(){
        return "Hello world";
    }

    @GetMapping(value = "/private")
    public String privateArea(){
        return "Private area";
    }


    @RequestMapping(value = "/getLogeduser")
        public Map<String,Object> getLogeduser(HttpServletRequest httpServletRequest ){
        HttpSession httpSession = httpServletRequest.getSession();
        SecurityContext securityContext = (SecurityContext)
                httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        List<String> roles = new ArrayList<>();
        for(GrantedAuthority ga:  securityContext.getAuthentication().getAuthorities())
        {
            roles.add(ga.getAuthority());
        }
        Map<String,Object> params = new HashMap<>();
        params.put("username",username);
        params.put("roles",roles);
        return  params;
        }
    }


