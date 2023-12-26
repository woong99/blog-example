package org.example.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/model")
    public String model(Model model) {
        model.addAttribute("title", "Model");
        model.addAttribute("content", "Model Content");

        return "index";
    }

    @GetMapping("/model-map")
    public String modelMap(ModelMap model) {
        model.addAttribute("title", "ModelMap");
        model.addAttribute("content", "ModelMap Content");
        return "index";
    }

    @GetMapping("/model-and-view")
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", "ModelAndView");
        modelAndView.addObject("content", "ModelAndView Content");
        return modelAndView;
    }
}
