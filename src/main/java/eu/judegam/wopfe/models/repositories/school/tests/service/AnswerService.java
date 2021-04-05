package eu.judegam.wopfe.models.repositories.school.tests.service;

import eu.judegam.wopfe.controllers.tests.AnswerController;
import eu.judegam.wopfe.models.repositories.school.tests.repository.AnswerRepository;
import eu.judegam.wopfe.models.repositories.school.tests.repository.QuestionRepository;
import eu.judegam.wopfe.models.repositories.school.tests.repository.TestsRepository;
import eu.judegam.wopfe.models.tests.Answer;
import eu.judegam.wopfe.models.tests.Question;
import eu.judegam.wopfe.models.tests.Test;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private final AnswerRepository repository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository repository, QuestionRepository questionRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
    }


    public Answer saveAnswer(Answer answer, Long questionId) {
        Question question = questionRepository.findById(questionId).get();
        answer.setQuestion(question);
        answer.setQuestionId(questionId);
        question.getAnswers().add(answer);
        questionRepository.save(question);
        return answer;
    }
    public String deleteAnswer(Long id) {
        repository.deleteById(id);
        return "Answer is not available!";
    }

    public Answer getAnswerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Answer updateAnswer(Long id, Answer answer) {
        Answer existingProduct = repository.findById(id).orElse(null);
        assert existingProduct != null;
        existingProduct.setAnswerText(answer.getAnswerText());
        existingProduct.setTrue(answer.isTrue());
        return repository.save(existingProduct);

    }
}
