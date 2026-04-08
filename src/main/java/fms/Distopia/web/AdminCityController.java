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
public class AdminCityController {
    @Autowired
    CityRepository cityRepository;

    /**
     * Displays the list of all cities in the admin panel.
     *
     * @return the city list view if user is admin, otherwise redirect to login
     */
    @GetMapping("/list")
    public String listCities(Model model, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("cities", cityRepository.findAll());
        return "admin/city/list";
    }

    /**
     * Deletes a city by its ID.
     * 
     * @return redirect to the city list view if successful, otherwise redirect to
     *         login
     */
    @GetMapping("/delete")
    public String deleteCity(Long id, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }
        cityRepository.deleteById(id);
        return "redirect:/admin/city/list";
    }

    /**
     * Handles the submission of the city form for both creation and update.
     * 
     * @return redirect to the city list if successful, otherwise return to the form
     *         view
     */
    @PostMapping("/save")
    public String save(Model model, @Valid City city, BindingResult bindingResult, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("city", city);
            return "admin/city/form";
        }

        cityRepository.save(city);
        return "redirect:/admin/city/list";
    }

    /**
     * Displays the city form for creating a new city or editing an existing one.
     * 
     * @return the city form view if user is admin, otherwise redirect to login
     */
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }

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