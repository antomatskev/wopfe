package eu.judegam.wopfe.models.repositories.school.tests.service;

import eu.judegam.wopfe.models.tests.Test;
import eu.judegam.wopfe.models.repositories.school.tests.repository.TestsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestsService {

    private final TestsRepository repository;

    public TestsService(TestsRepository repository) {
        this.repository = repository;
    }


    public Test saveTest(Test test) {
        return repository.save(test);
    }

    public List<Test> saveTests(List<Test> test) {
        return (List<Test>) repository.saveAll(test);
    }

    public List<Test> getTest() {
        return (List<Test>) repository.findAll();
    }

    public Test getTestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Test getTestByName(String name) {
        return repository.findByName(name);
    }

    public String deleteTest(Long id) {
        repository.deleteById(id);
        return "Test is not available!";
    }

    public Test updateTest(Test test) {
        Test existingProduct = repository.findById(test.getId()).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(test.getName());
        existingProduct.setDate(test.getDate());
        existingProduct.setTime(test.getTime());
        return repository.save(existingProduct);

    }
}
