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

/**
 * Controller responsible for handling reservation-related actions.
 * Includes listing user reservations, displaying reservation forms,
 * and saving new reservations.
 */
@Controller
public class ReservationController {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    ScreeningRepository screeningRepository;

    @Autowired
    ReservationRepository reservationRepository;

    /**
     * Displays all reservations for the currently logged-in user.
     *
     * @return the reservation list view or redirect to login if not authenticated
     */
    @GetMapping("/reservation/list")
    public String listReservationByUser(Model model, HttpSession session) {

        // Redirect to login if user is not authenticated
        if (!AuthHelper.isLogged(session)) {
            return "redirect:/login";
        }

        // Retrieve current user from session
        User user = AuthHelper.getUser(session);
        Long userId = user.getId();
        List<Reservation> reservations = reservationRepository.findByUserId(userId);

        model.addAttribute("reservations", reservations);
        return "/reservation/list";
    }

    /**
     * Displays the reservation form for a specific screening.
     *
     * @return the reservation form view or redirect to login if not authenticated
     */
    @GetMapping("/screening/reservation/form")
    public String form(Model model,
            @RequestParam(name = "screeningId") Long screeningId,
            HttpSession session) {

        // Redirect if user is not logged in
        if (!AuthHelper.isLogged(session)) {
            return "redirect:/login";
        }

        // Get current user
        User user = AuthHelper.getUser(session);

        Reservation reservation = new Reservation();
        reservation.setUser(user);

        Screening screening = new Screening();
        screening.setId(screeningId);

        reservation.setScreening(screening);

        model.addAttribute("reservation", reservation);

        return "reservation/form";
    }

    /**
     * Handles submission of a reservation form and saves it to the database.
     *
     * @return redirect to cinema list page or login if not authenticated
     */
    @PostMapping("/screening/reservation/save")
    public String saveReservation(@ModelAttribute Reservation reservation,
            HttpSession session) {

        // Ensure user is authenticated
        if (!AuthHelper.isLogged(session)) {
            return "redirect:/login";
        }

        // Retrieve current user
        User user = AuthHelper.getUser(session);

        reservation.setUser(user);
        reservation.setReservationDate(LocalDateTime.now());

        reservationRepository.save(reservation);

        return "redirect:/cinema/list";
    }
}
