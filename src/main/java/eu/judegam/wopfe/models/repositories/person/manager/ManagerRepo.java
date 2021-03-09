package eu.judegam.wopfe.models.repositories.person.manager;

import eu.judegam.wopfe.models.user.Manager;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ManagerRepo extends Repository<Manager, Long> {

    Manager save(Manager manager);
    Manager findByName(String name);

}
