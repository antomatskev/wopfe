package eu.judegam.wopfe;

import eu.judegam.wopfe.models.repositories.person.AdminRepo;
import eu.judegam.wopfe.models.repositories.person.manager.ManagerRepo;
import eu.judegam.wopfe.models.repositories.school.school.SchoolRepo;
import eu.judegam.wopfe.models.user.Admin;
import eu.judegam.wopfe.models.user.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final AdminRepo admins;
    private final SchoolRepo schools;
    private final ManagerRepo managers;

    @Autowired
    public DatabaseLoader(AdminRepo adminRepo, SchoolRepo schoolRepo, ManagerRepo managerRepo) {
        this.admins = adminRepo;
        this.schools = schoolRepo;
        this.managers = managerRepo;
    }

    @Override
    public void run(String... strings) throws Exception {

        if (this.managers.findByName("julia") == null) {
            Manager julia = this.managers.save(new Manager("julia", "djomina",
                    "ROLE_MANAGER"));

//            SecurityContextHolder.getContext().setAuthentication(
//                    new UsernamePasswordAuthenticationToken("julia", "doesn't matter",
//                            AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

            this.admins.save(new Admin("Frodo", "Baggins", "ring bearer", julia));
            this.admins.save(new Admin("Bilbo", "Baggins", "burglar", julia));
            this.admins.save(new Admin("Gandalf", "the Grey", "wizard", julia));
        }
        if (this.managers.findByName("ellina") == null) {
            Manager ellina = this.managers.save(new Manager("ellina", "gerdojets",
                    "ROLE_MANAGER"));

//            SecurityContextHolder.getContext().setAuthentication(
//                    new UsernamePasswordAuthenticationToken("ellina", "doesn't matter",
//                            AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

            this.admins.save(new Admin("Samwise", "Gamgee", "gardener", ellina));
            this.admins.save(new Admin("Merry", "Brandybuck", "pony rider", ellina));
            this.admins.save(new Admin("Peregrin", "Took", "pipe smoker", ellina));
        }
        if (this.managers.findByName("anton") == null) {
            Manager anton = this.managers.save(new Manager("anton", "matskevich",
                    "ROLE_MANAGER"));

//            SecurityContextHolder.getContext().setAuthentication(
//                    new UsernamePasswordAuthenticationToken("anton", "doesn't matter",
//                            AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

            this.admins.save(new Admin("Gendalf", "The Gray", "gardener", anton));
            this.admins.save(new Admin("Saruman", "The White", "pony rider", anton));
            this.admins.save(new Admin("Bombadoor", "The Brown", "pipe smoker", anton));
        }

//        SecurityContextHolder.clearContext();
    }

}
