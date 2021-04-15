package eu.judegam.wopfe.services;

import eu.judegam.wopfe.models.user.Admin;
import eu.judegam.wopfe.repositories.AdminRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepo repo;

    public AdminService(AdminRepo repository) {
        this.repo = repository;
    }

    public Admin saveAdmin(Admin admin) {
        return repo.save(admin);
    }

    public List<Admin> saveAdmins(List<Admin> admins) {
        return (List<Admin>) repo.saveAll(admins);
    }

    public List<Admin> getAdmins() {
        return (List<Admin>) repo.findAll();
    }

    public Admin getAdminById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Admin getAdminByName(String name) {
        // TODO: here will be first and last names in the play.
        return repo.findByName(name);
    }

    public String deleteAdmin(Long id) {
        repo.deleteById(id);
        return "Admin was deleted.";
    }

    public Admin updateAdmin(Long id, Admin admin) {
        Admin existingProduct = repo.findById(id).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(admin.getName());
        existingProduct.setLastName(admin.getLastName());
        existingProduct.setSchools(admin.getSchools());
        existingProduct.setManager(admin.getManager());
        return repo.save(existingProduct);
    }

    public Admin updateAdmin(Admin admin) {
        return updateAdmin(Long.MAX_VALUE, admin);
    }

}
