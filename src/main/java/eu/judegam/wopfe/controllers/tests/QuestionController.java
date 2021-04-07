package eu.judegam.wopfe.controllers.tests;


import eu.judegam.wopfe.models.repositories.school.tests.service.QuestionService;
import eu.judegam.wopfe.models.tests.Answer;
import eu.judegam.wopfe.models.tests.Question;
import eu.judegam.wopfe.models.tests.Test;
import eu.judegam.wopfe.models.timetable.Event;
import eu.judegam.wopfe.models.timetable.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService service;

    @RequestMapping(path = "/main/teacher/question/{id}/addQuestion", method = RequestMethod.POST)
    public RedirectView saveQuestion(RedirectAttributes redirectAttributes, @ModelAttribute Question question,
                                     @PathVariable("id") Long testId) {
        service.saveQuestion(question, testId);
        final String msg = "Created event <b>" + question.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/teacher/tests/{id}", true);
        redirectAttributes.addFlashAttribute("qMessage", msg);
        return view;
    }


    @RequestMapping(path = "/main/teacher/question/{id}/deleteQuestion/{eId}", method = RequestMethod.POST)
    public RedirectView deleteQuestion(RedirectAttributes redirectAttributes, @ModelAttribute Question question,
                                       @PathVariable("id") Long ttId, @PathVariable("eId") Long eId) {
        service.deleteQuestion(eId);
        final String msg = "Created event <b>" + question.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/teacher/tests/{id}", true);
        redirectAttributes.addFlashAttribute("evMessage", msg);
        return view;
    }

    @RequestMapping(value = "/main/teacher/questions", method = RequestMethod.GET)
    public String getQuestions(Model model) {
        List<Question> questions = service.getQuestions();
        model.addAttribute("questions", questions);
        model.addAttribute("question", new Question());
        return "tests/edit_questions";
    }

    @GetMapping(value = "/main/teacher/questions/{id}")
    public String showQuestionById(Model model, @PathVariable("id") Long id) {
        Question question = service.getQuestionById(id);
        model.addAttribute("question", question);
        model.addAttribute("answer", new Answer());
        return "tests/edit_questions";
    }


    @RequestMapping(path = "/main/teacher/questions/{id}/update", method = RequestMethod.POST)
    public String updateQuestion(Model model, @PathVariable("id") Long id, @ModelAttribute Question question) {
        Question dbQuestion = service.updateQuestion(id, question);
        model.addAttribute("question", dbQuestion);
        model.addAttribute("answer", new Answer());
        return "tests/edit_questions";
    }

    @RequestMapping(path = "/main/teacher/questionsAnswer/{id}", method = RequestMethod.POST)
    public String updateQuestionAnswers(Model model, @PathVariable("id") Long id) {
        Question question = service.getQuestionById(id);
        model.addAttribute("question", question);
        model.addAttribute("answers", question.getAnswers());
        model.addAttribute("answer", new Answer());
        return "tests/edit_questions";
    }


}
