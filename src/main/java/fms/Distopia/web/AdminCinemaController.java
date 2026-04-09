package fms.Distopia.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fms.Distopia.dao.CinemaRepository;
import fms.Distopia.dao.CityRepository;
import fms.Distopia.entities.Cinema;
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
@RequestMapping("/admin/cinema")
public class AdminCinemaController {
    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    CityRepository cityRepository;

    /**
     * Displays the list of all cinemas in the admin panel.
     *
     * @return the cinema list view if user is admin, otherwise redirect to login
     */
    @GetMapping("/list")
    public String listCinemas(Model model, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("cinemas", cinemaRepository.findAll());
        return "admin/cinema/list";
    }

    /**
     * Deletes a cinema by its ID.
     * 
     * @return redirect to the cinema list view if successful, otherwise redirect to
     *         login
     */
    @GetMapping("/delete")
    public String deleteCinema(Long id, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }
        cinemaRepository.deleteById(id);
        return "redirect:/admin/cinema/list";
    }

    /**
     * Handles the submission of the cinema form for both creation and update.
     *
     * @return redirect to the cinema list if successful, otherwise return to the
     *         form
     *         view
     */
    @PostMapping("/save")
    public String saveCinema(Model model, @Valid Cinema cinema, BindingResult bindingResult, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("cinema", cinema);
            model.addAttribute("cities", cityRepository.findAll());
            return "admin/cinema/form";
        }

        // Ensure the city is fully loaded from database before saving
        if (cinema.getCity() != null && cinema.getCity().getId() != null) {
            City city = cityRepository
                    .findById(cinema.getCity().getId())
                    .orElse(null);
            cinema.setCity(city);
        }

        cinemaRepository.save(cinema);
        return "redirect:/admin/cinema/list";
    }

    /**
     * Displays the cinema form for creating a new cinema or editing an existing
     * one.
     *
     * @return the cinema form view if user is admin, otherwise redirect to login
     */
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id,
            HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }

        Cinema cinema;
        if (id != null) {
            cinema = cinemaRepository.findById(id).orElse(new Cinema());
        } else {
            cinema = new Cinema();
        }
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("cinema", cinema);
        return "admin/cinema/form";
    }

}