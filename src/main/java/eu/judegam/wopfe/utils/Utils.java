package eu.judegam.wopfe.utils;

import eu.judegam.wopfe.models.User;
import eu.judegam.wopfe.security.UserRole;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

/**
 * Class with utility methods.
 */
public class Utils {

    /**
     * Add authenticated user attributes to the model.
     *
     * @param model   to add attributes to.
     * @param defPath default path to return in the controller.
     * @return the path to the html file to render.
     */
    public static String addUsrAttrToModel(Model model, String defPath) {
        final String ret;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            Object user = auth.getPrincipal();
            if (user instanceof User) {
                UserRole userRole = ((User) user).getUserRole();
                model.addAttribute("role", userRole);
                model.addAttribute("username", ((User) user).getUsername());
                ret = defPath;
            } else {
                ret = "error";
            }
        } else {
            ret = "error";
        }
        return ret;
    }

}
