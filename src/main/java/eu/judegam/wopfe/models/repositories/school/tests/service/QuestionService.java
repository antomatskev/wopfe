package eu.judegam.wopfe.models.repositories.school.tests.service;


import eu.judegam.wopfe.models.repositories.school.tests.repository.QuestionRepository;
import eu.judegam.wopfe.models.repositories.school.tests.repository.TestsRepository;
import eu.judegam.wopfe.models.tests.Test;
import eu.judegam.wopfe.models.tests.Question;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
//        repository.save(question);
        return question;
    }

    public String deleteQuestion(Long id) {
        repository.deleteById(id);
        return "Question is not available!";
    }

    public List<Question> getQuestions() {
        return (List<Question>) repository.findAll();
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
