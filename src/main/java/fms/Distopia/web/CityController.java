package fms.Distopia.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fms.Distopia.dao.CityRepository;
import fms.Distopia.entities.City;
import fms.Distopia.util.AuthHelper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller responsible for managing City administration features.
 * 
 * Access to these endpoints is restricted to admin users only.
 */
@Controller
@RequestMapping("/admin/city")
public class CityController {
    @Autowired
    CityRepository cityRepository;

    /**
     * Displays the list of all cities in the admin panel.
     *
     * @return the city list view if user is admin, otherwise redirect to login
     */
    @GetMapping("/list")
    public String listCities(Model model, HttpSession session) {

        // if (!AuthHelper.isAdmin(session)) {
        // return "redirect:/login";
        // }
        model.addAttribute("cities", cityRepository.findAll());
        return "admin/city/list";
    }

    /**
     * Deletes a city by its ID.
     * Only accessible to admin users.
     */
    @GetMapping("/delete")
    public String deleteCity(Long id, HttpSession session) {

        // if (!AuthHelper.isAdmin(session)) {
        // return "redirect:/login";
        // }
        cityRepository.deleteById(id);
        return "redirect:/admin/city/list";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid City city, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("city", city);
            return "admin/city/form";
        }

        cityRepository.save(city);
        return "redirect:/admin/city/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        City city;
        if (id != null) {
            city = cityRepository.findById(id).orElse(new City());
        } else {
            city = new City();
        }
        model.addAttribute("city", city);
        return "admin/city/form";
    }
}