package com.mafv.springprojects.exament4.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mafv.springprojects.exament4.model.Permiso;
import com.mafv.springprojects.exament4.services.PermisosService;

@Controller
@RequestMapping("/permisos")
public class PermisoController {

    @Autowired
    PermisosService permisosService;

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Permiso> permisos = permisosService.findAll();
        modelAndView.addObject("permisos", permisos);
        modelAndView.setViewName("permisos/list");
        return modelAndView;
    }


    @GetMapping(value = "/create")
    public ModelAndView create(Permiso permiso) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permiso", new Permiso());
        modelAndView.setViewName("permisos/create");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Permiso permiso) throws IOException{

        permisosService.insert(permiso);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + permiso.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Permiso permiso = permisosService.findByID(codigo);
                
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permiso", permiso);
        modelAndView.setViewName("permisos/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Permiso permiso) {

        permisosService.update(permiso);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + permiso.getCodigo());
        
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        permisosService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/permisos/list");

        return modelAndView;
    }
}
