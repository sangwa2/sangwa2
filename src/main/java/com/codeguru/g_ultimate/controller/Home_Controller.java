/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.controller;

import com.codeguru.g_ultimate.models.Mdl_structure;
import com.codeguru.g_ultimate.models.Mdl_unit;
import com.codeguru.g_ultimate.service.Srv_unit;
import com.codeguru.g_ultimate.service.Srvc_structure;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author SANGWA
 */
@Controller
public class Home_Controller extends RuntimeException {

    @Autowired
    Srvc_structure srvc_structure;

    @Autowired
    Srv_unit srv_unit;

    @GetMapping("/")
    public String index(Model model) {
        return "home";
//        return "to_be_encrypte";
    }

    @GetMapping("web")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("units_list", srv_unit.list_all_units());
        return mv;
    }

    @GetMapping("/404")
    public String error() {
        return "404";
    }

    private int getErrorcode(HttpServletRequest httpServletRequest) {
        return (Integer) httpServletRequest.getAttribute("javax.servlet.error.status_code");
    }

    @GetMapping("databy")
    public String go_to_unit() {
        return "data_by";
    }

    @RequestMapping(value = "/get_all_units", method = RequestMethod.GET)
    public ModelAndView units_requested(@RequestParam("unit") int unit) {
        ModelAndView mv = new ModelAndView("test_delete");
        mv.addObject("mdl_unit", srv_unit.list_all_units());
        return mv;
    }

    @RequestMapping(value = "/datalist", method = RequestMethod.GET)
    public ModelAndView data_list() {
        ModelAndView mv = new ModelAndView();
        List<Mdl_unit> unit_list = srv_unit.list_all_units();
        mv.addObject("list_units", unit_list);
        List<Mdl_structure> list = srvc_structure.list_all_structure();
        mv.setViewName("data_list");
        mv.addObject("list_structure", list);
        return mv;
    }
    
    @RequestMapping(value = "/data_by", method = RequestMethod.POST)
    public ModelAndView unit_by_structure(HttpServletRequest hreq, HttpServletResponse hres) {
        int structure = Integer.parseInt(hreq.getParameter("structure"));
        ModelAndView mv = new ModelAndView();
        List<Mdl_unit> unit_list = srv_unit.units_by_structure(structure, "courses");
        mv.setViewName("data_by");
        mv.addObject("list_units", unit_list);
        return mv;
    }

    @RequestMapping(value = "/jquery", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Collection<Mdl_unit> get_data_tojquery(@RequestBody HttpServletRequest hreq) throws IOException {
//        ModelAndView mv = new ModelAndView();
        int structure = Integer.parseInt(hreq.getParameter("structure"));
        List<Mdl_unit> unit_list = srv_unit.units_by_structure(structure, "courses");
//        mv.setViewName("js_results");
//        mv.addObject("scripts", unit_list);
        return unit_list;
    }

}
