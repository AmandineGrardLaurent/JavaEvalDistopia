package fms.Distopia.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fms.Distopia.dao.MovieRepository;
import fms.Distopia.entities.Movie;
import fms.Distopia.util.AuthHelper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller responsible for managing Movie administration features.
 * 
 * Access to these endpoints is restricted to admin users only.
 */
@Controller
@RequestMapping("/admin/movie")
public class AdminMovieController {
    @Autowired
    MovieRepository movieRepository;

    /**
     * Displays the list of all movies in the admin panel.
     *
     * @return the movies list view if user is admin, otherwise redirect to login
     */
    @GetMapping("/list")
    public String listMovies(Model model, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("movies", movieRepository.findAll());
        return "admin/movie/list";
    }

    /**
     * Deletes a movie by its ID.
     * 
     * @return redirect to the movie list view if successful, otherwise redirect to
     *         login
     */
    @GetMapping("/delete")
    public String deleteMovie(Long id, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }
        movieRepository.deleteById(id);
        return "redirect:/admin/movie/list";
    }

    /**
     * Handles the submission of the movie form for both creation and update.
     * 
     * @return redirect to the movie list if successful, otherwise return to the
     *         form
     *         view
     */
    @PostMapping("/save")
    public String saveMovie(Model model, @Valid Movie movie, BindingResult bindingResult, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("movie", movie);
            return "admin/movie/form";
        }

        movieRepository.save(movie);
        return "redirect:/admin/movie/list";
    }

    /**
     * Displays the movie form for creating a new movie or editing an existing one.
     * 
     * @return the movie form view if user is admin, otherwise redirect to login
     */
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id, HttpSession session) {

        if (!AuthHelper.isAdmin(session)) {
            return "redirect:/login";
        }

        Movie movie;
        if (id != null) {
            movie = movieRepository.findById(id).orElse(new Movie());
        } else {
            movie = new Movie();
        }
        model.addAttribute("movie", movie);
        return "admin/movie/form";
    }
}