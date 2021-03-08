package eu.judegam.wopfe.principal.service;

import eu.judegam.wopfe.models.user.Principal;
import eu.judegam.wopfe.principal.repository.PrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Principal class.
 */
@Service
public class PrincipalService {

    @Autowired
    private PrincipalRepository repository;

    public Principal savePrincipal(Principal principal) {
        return repository.save(principal);
    }

    public List<Principal> savePrincipals(List<Principal> principals) {
        return (List<Principal>) repository.saveAll(principals);
    }

    public List<Principal> getPrincipal() {
        return (List<Principal>) repository.findAll();
    }

    public Principal getPrincipalById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Principal getPrincipalByName(String name) {
        return repository.findByName(name);
    }

    public String deletePrincipal(Long id) {
        repository.deleteById(id);
        return "Principal was deleted.";
    }

    public Principal updatePrincipal(Principal principal) {
        Principal existingProduct = repository.findById(principal.getId()).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(principal.getName());
        existingProduct.setLastName(principal.getLastName());
        existingProduct.setSchools(principal.getSchools());
        existingProduct.setStatus(principal.getStatus());
        return repository.save(existingProduct);

    }
}

