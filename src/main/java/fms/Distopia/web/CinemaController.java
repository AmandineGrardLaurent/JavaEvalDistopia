package fms.Distopia.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fms.Distopia.dao.CinemaRepository;
import fms.Distopia.dao.CityRepository;
import fms.Distopia.dao.ScreeningRepository;
import fms.Distopia.entities.Cinema;
import fms.Distopia.entities.Movie;
import fms.Distopia.entities.Screening;

@Controller
public class CinemaController {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    ScreeningRepository screeningRepository;

    /**
     * Displays the list of cinemas with optional pagination and city name-based
     * filtering.
     * 
     * @return the "cinema/list" view template
     */
    @GetMapping("/cinema/list")
    public String listCinemas(Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "cityName", required = false) String cityName,
            @RequestParam(name = "search", defaultValue = "") String search) {

        Page<Cinema> cinemas;

        if (cityName != null && !cityName.isEmpty()) {
            cinemas = cinemaRepository.findByCityNameAndNameContainsIgnoreCase(cityName, search,
                    PageRequest.of(page, 6));
        } else {
            cinemas = cinemaRepository.findByNameContainsIgnoreCase(search, PageRequest.of(page, 6));
        }

        model.addAttribute("cinemas", cinemas);
        model.addAttribute("pages", new int[cinemas.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("cityName", cityName);
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("search", search);

        return "cinema/list";
    }

    /**
     * Displays the list of screenings for a specific cinema based on the provided
     * cinema ID.
     * 
     * @return the "cinema/screenings" view template
     */
    @GetMapping("/cinema/screenings")
    public String listScreenings(Model model, @RequestParam(name = "cinemaId") Long cinemaId) {

        List<Screening> screenings = screeningRepository.findByCinemaId(cinemaId);

        // Group all screenings by their associated movie
        // Result: each Movie key maps to a list of its screenings
        Map<Movie, List<Screening>> screeningsByMovie = screenings.stream()
                .collect(Collectors.groupingBy(Screening::getMovie));
        Cinema cinema = cinemaRepository.findById(cinemaId).orElse(null);

        model.addAttribute("screeningsByMovie", screeningsByMovie);
        model.addAttribute("cinema", cinema);

        return "cinema/screenings";
    }

}
