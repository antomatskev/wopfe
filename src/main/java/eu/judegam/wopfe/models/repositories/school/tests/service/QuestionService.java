package eu.judegam.wopfe.models.repositories.school.tests.service;


import eu.judegam.wopfe.models.repositories.school.tests.repository.QuestionRepository;
import eu.judegam.wopfe.models.repositories.school.tests.repository.TestsRepository;
import eu.judegam.wopfe.models.school.tests.Test;
import eu.judegam.wopfe.models.tests.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository repository;
    private final TestsRepository testsRepository;


    public QuestionService(QuestionRepository repository, TestsRepository testsRepository) {
        this.repository = repository;
        this.testsRepository = testsRepository;
    }


    public Question saveQuestion(Question question) {
        Test test = testsRepository.findById(question.getTestId()).get();
        question.setTest(test);
        return repository.save(question);
    }

}
