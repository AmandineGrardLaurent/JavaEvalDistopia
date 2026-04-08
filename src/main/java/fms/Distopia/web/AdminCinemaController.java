package fms.Distopia.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import fms.Distopia.dao.CinemaRepository;

import fms.Distopia.util.AuthHelper;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

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

}