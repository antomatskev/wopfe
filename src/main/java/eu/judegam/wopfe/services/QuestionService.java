package eu.judegam.wopfe.services;

import eu.judegam.wopfe.models.tests.Question;
import eu.judegam.wopfe.models.tests.Test;
import eu.judegam.wopfe.repositories.QuestionRepository;
import eu.judegam.wopfe.repositories.TestsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String deleteQuestion(Long id) {
        repository.deleteById(id);
        return "Question is not available!";
    }

    public List<Question> getQuestions() {
        return (List<Question>) repository.findAll(Sort.by(Sort.Direction.DESC, "questionText"));
    }

    public Question getQuestionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Question updateQuestion(Long id, Question question) {
        Question existingProduct = repository.findById(id).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(question.getName());
        existingProduct.setQuestionText(question.getQuestionText());
        return repository.save(existingProduct);

    }

}
