package eu.judegam.wopfe.controllers.person;

import eu.judegam.wopfe.models.repositories.person.principal.service.PrincipalService;
import eu.judegam.wopfe.models.user.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for Principal class.
 */
@RestController
public class PrincipalController {

    @Autowired
    private PrincipalService service;

    @PostMapping("/addPrincipal")
    public Principal addPrincipal(@RequestBody Principal principal) {
        return service.savePrincipal(principal);
    }

    @PostMapping("/addPrincipals")
    public List<Principal> addTests(@RequestBody List<Principal> principals) {
        return service.savePrincipals(principals);
    }

    @PostMapping("/principals")
    public List<Principal> findAllPrincipals() {
        return service.getPrincipal();
    }

    @PostMapping("/principal/{id}")
    public Principal getPrincipalById(@PathVariable Long id) {
        return service.getPrincipalById(id);
    }

    @PostMapping("/principal/{name}")
    public Principal getPrincipalByName(@PathVariable String name) {
        return service.getPrincipalByName(name);
    }

    @PutMapping("/principal/{id}/update")
    public Principal updatePrincipal(@RequestBody Principal principal) {
        return service.updatePrincipal(principal);
    }

    @DeleteMapping("/principal/{id}/delete/{id}")
    public String deletePrincipal(@PathVariable Long id) {
        return service.deletePrincipal(id);
    }

    @GetMapping("/main/principal")
    public String principal(Model model) {
        return "mains/principal_main";
    }

}
