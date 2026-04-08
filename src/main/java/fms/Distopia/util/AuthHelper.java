package fms.Distopia.util;

import javax.servlet.http.HttpSession;

import fms.Distopia.entities.Role;
import fms.Distopia.entities.User;

public class AuthHelper {

    public static User getUser(HttpSession session) {
        Object obj = session.getAttribute("user");

        if (obj instanceof User) {
            return (User) obj;
        }

        return null;
    }

    public static boolean isLogged(HttpSession session) {
        return getUser(session) != null;
    }

    public static boolean isAdmin(HttpSession session) {
        User user = getUser(session);
        return user != null && user.getRole() == Role.ADMIN;
    }
}