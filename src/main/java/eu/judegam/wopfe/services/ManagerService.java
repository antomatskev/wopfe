package eu.judegam.wopfe.services;

import eu.judegam.wopfe.repositories.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagerService {

    private final ManagerRepo repository;

    @Autowired
    public ManagerService(ManagerRepo repository) {
        this.repository = repository;
    }

}
