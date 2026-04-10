package fms.Distopia.web;

import fms.Distopia.dao.UserRepository;
import fms.Distopia.entities.User;
import fms.Distopia.util.AuthHelper;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsible for authentication logic.
 * Handles login and logout actions, as well as session management.
 */
@Controller
public class AuthController {

    private final UserRepository userRepository;

    /**
     * Constructor-based dependency injection for UserRepository.
     *
     * @param userRepository repository used to fetch users from database
     */
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Displays the login page.
     *
     * @return the login view
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Handles user authentication.
     * Verifies credentials and stores the authenticated user in session.
     * 
     * @return redirect to admin or cinema page if successful, otherwise reload
     *         login page
     */
    @PostMapping("/login")
    public String login(@RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userRepository.findByEmail(email).orElse(null);

        // Validate credentials
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Email ou mot de passe incorrect");
            return "login";
        }

        session.setAttribute("user", user);

        // Redirect admin users to admin dashboard
        if (AuthHelper.isAdmin(session)) {
            return "redirect:/admin/cinema/list";
        }

        // Redirect regular users to cinema list
        return "redirect:/cinema/list";
    }

    /**
     * Logs out the current user by invalidating the session.
     *
     * @param session HTTP session to invalidate
     * @return redirect to cinema list page
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/cinema/list";
    }
}