package fms.Distopia.util;

import javax.servlet.http.HttpSession;

public class AuthHelper {
    public static boolean isLogged(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
