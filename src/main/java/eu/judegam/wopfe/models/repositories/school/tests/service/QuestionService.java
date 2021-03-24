package eu.judegam.wopfe.models.repositories.school.tests.service;


import eu.judegam.wopfe.models.repositories.school.tests.repository.QuestionRepository;
import eu.judegam.wopfe.models.repositories.school.tests.repository.TestsRepository;
import eu.judegam.wopfe.models.tests.Test;
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


    public Question saveQuestion(Question question, Long testId) {
        Test test = testsRepository.findById(testId).get();
        question.setTest(test);
        question.setTestId(testId);
        test.getQuestions().add(question);
        testsRepository.save(test);
        return question;
    }

}
