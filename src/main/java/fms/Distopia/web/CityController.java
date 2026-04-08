package fms.Distopia.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import fms.Distopia.dao.CityRepository;
import fms.Distopia.util.AuthHelper;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for managing City administration features.
 * 
 * Access to these endpoints is restricted to admin users only.
 */
@Controller
public class CityController {
    @Autowired
    CityRepository cityRepository;

    /**
     * Displays the list of all cities in the admin panel.
     *
     * @return the city list view if user is admin, otherwise redirect to login
     */
    @GetMapping("/admin/city/list")
    public String listCities(Model model, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("cities", cityRepository.findAll());
        return "admin/city/list";
    }

    /**
     * Deletes a city by its ID.
     * Only accessible to admin users.
     */
    @GetMapping("/city/delete")
    public String deleteCity(Long id, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }
        cityRepository.deleteById(id);
        return "redirect:/admin/city/list";
    }
}