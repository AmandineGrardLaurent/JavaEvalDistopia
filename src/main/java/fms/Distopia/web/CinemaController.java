package fms.Distopia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fms.Distopia.dao.CinemaRepository;
import fms.Distopia.entities.Cinema;

@Controller
public class CinemaController {

    @Autowired
    CinemaRepository cinemaRepository;

    @GetMapping("/cinema/list")
    public String read(Model model,
            @RequestParam(name = "page", defaultValue = "0") int page) {

        Page<Cinema> cinemas;

        cinemas = cinemaRepository.findAll(PageRequest.of(page, 6));
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("pages", new int[cinemas.getTotalPages()]);
        model.addAttribute("currentPage", page);

        return "cinema/list";
    }

}
