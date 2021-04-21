package eu.judegam.wopfe.services;

import eu.judegam.wopfe.models.tests.Test;
import eu.judegam.wopfe.repositories.TestsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<Test> getTests() {
        return (List<Test>) repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Test> getUserTests(String clazz) {
        return getTests().stream()
                .filter(t -> Objects.equals(t.getClazz(), clazz))
                .collect(Collectors.toList());
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

    public Test updateTest(Long id, Test test) {
        Test existingProduct = repository.findById(id).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(test.getName());
        existingProduct.setDate(test.getDate());
        existingProduct.setTime(test.getTime());
        return repository.save(existingProduct);

    }
}
