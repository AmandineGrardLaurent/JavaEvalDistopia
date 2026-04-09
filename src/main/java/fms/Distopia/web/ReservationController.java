package fms.Distopia.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fms.Distopia.dao.CinemaRepository;
import fms.Distopia.dao.ReservationRepository;
import fms.Distopia.dao.ScreeningRepository;

import fms.Distopia.entities.Reservation;
import fms.Distopia.entities.Screening;
import fms.Distopia.entities.User;
import fms.Distopia.util.AuthHelper;

@Controller
public class ReservationController {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    ScreeningRepository screeningRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/reservation/list")
    public String listReservationByUser(Model model, HttpSession session) {

        if (!AuthHelper.isLogged(session)) {
            return "redirect:/login";
        }
        User user = AuthHelper.getUser(session);
        Long userId = user.getId();
        List<Reservation> reservations = reservationRepository.findByUserId(userId);

        model.addAttribute("reservations", reservations);
        return "/reservation/list";
    }

    @GetMapping("/screening/reservation/form")
    public String form(Model model,
            @RequestParam(name = "screeningId") Long screeningId,
            HttpSession session) {

        if (!AuthHelper.isLogged(session)) {
            return "redirect:/login";
        }
        User user = AuthHelper.getUser(session);

        Reservation reservation = new Reservation();
        reservation.setUser(user);

        Screening screening = new Screening();
        screening.setId(screeningId);

        reservation.setScreening(screening);

        model.addAttribute("reservation", reservation);

        return "reservation/form";
    }

    @PostMapping("/screening/reservation/save")
    public String saveReservation(@ModelAttribute Reservation reservation,
            HttpSession session) {

        if (!AuthHelper.isLogged(session)) {
            return "redirect:/login";
        }

        User user = AuthHelper.getUser(session);

        reservation.setUser(user);
        reservation.setReservationDate(LocalDateTime.now());

        reservationRepository.save(reservation);

        return "redirect:/cinema/list";
    }
}
