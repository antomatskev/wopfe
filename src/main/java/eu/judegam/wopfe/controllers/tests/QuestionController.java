package eu.judegam.wopfe.controllers.tests;


import eu.judegam.wopfe.services.QuestionService;
import eu.judegam.wopfe.models.tests.Answer;
import eu.judegam.wopfe.models.tests.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService service;

    @RequestMapping(path = "/main/teacher/question/{id}/addQuestion", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public RedirectView saveQuestion(RedirectAttributes redirectAttributes, @ModelAttribute Question question,
                                     @PathVariable("id") Long testId) {
        service.saveQuestion(question, testId);
        final String msg = "Created event <b>" + question.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/teacher/tests/{id}", true);
        redirectAttributes.addFlashAttribute("qMessage", msg);
        return view;
    }


    @RequestMapping(path = "/main/teacher/question/{id}/deleteQuestion/{eId}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public RedirectView deleteQuestion(RedirectAttributes redirectAttributes, @ModelAttribute Question question,
                                       @PathVariable("id") Long ttId, @PathVariable("eId") Long eId) {
        service.deleteQuestion(eId);
        final String msg = "Created event <b>" + question.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/teacher/tests/{id}", true);
        redirectAttributes.addFlashAttribute("evMessage", msg);
        return view;
    }

    @RequestMapping(value = "/main/teacher/questions", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String getQuestions(Model model) {
        List<Question> questions = service.getQuestions();
        model.addAttribute("questions", questions);
        model.addAttribute("question", new Question());
        return "tests/edit_questions";
    }

    @GetMapping(value = "/main/teacher/questions/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String showQuestionById(Model model, @PathVariable("id") Long id) {
        Question question = service.getQuestionById(id);
        List<Answer> answers = question.getAnswers().stream().sorted(Comparator.comparing(Answer::getAnswerText)).collect(Collectors.toList());
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);
        model.addAttribute("answer", new Answer());
        return "tests/edit_questions";
    }


    @RequestMapping(path = "/main/teacher/questions/{id}/update", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String updateQuestion(Model model, @PathVariable("id") Long id, @ModelAttribute Question question) {
        Question dbQuestion = service.updateQuestion(id, question);
        model.addAttribute("question", dbQuestion);
        model.addAttribute("answer", new Answer());
        return "tests/edit_questions";
    }

    @RequestMapping(path = "/main/teacher/questionsAnswer/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String updateQuestionAnswers(Model model, @PathVariable("id") Long id) {
        Question question = service.getQuestionById(id);
        model.addAttribute("question", question);
        model.addAttribute("answers", question.getAnswers());
        model.addAttribute("answer", new Answer());
        return "tests/edit_questions";
    }


}
