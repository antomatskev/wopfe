package eu.judegam.wopfe.services;

import eu.judegam.wopfe.repositories.AnswerRepository;
import eu.judegam.wopfe.repositories.QuestionRepository;
import eu.judegam.wopfe.models.tests.Answer;
import eu.judegam.wopfe.models.tests.Question;
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
        if (answer.isCorrect() == null) {
            answer.setCorrect(false); // TODO: fix receiving null from front.
        }
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
        existingProduct.setCorrect(answer.isCorrect());
        return repository.save(existingProduct);

    }
}
