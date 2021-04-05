package eu.judegam.wopfe.controllers.tests;

import eu.judegam.wopfe.models.repositories.school.tests.service.AnswerService;
import eu.judegam.wopfe.models.tests.Answer;
import eu.judegam.wopfe.models.tests.Question;
import eu.judegam.wopfe.models.tests.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService service;

    @RequestMapping(path = "/main/teacher/questionAnswer/{id}/addQuestion", method = RequestMethod.POST)
    public RedirectView saveAnswer(RedirectAttributes redirectAttributes, @ModelAttribute Answer answer,
                                     @PathVariable("id") Long questionId) {
        service.saveAnswer(answer, questionId);
        final String msg = "Created answer <b>" + answer.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/teacher/answer/{id}", true);
        redirectAttributes.addFlashAttribute("qMessage", msg);
        return view;
    }

    @RequestMapping(path = "/main/teacher/question/{id}/deleteAnswer/{eId}", method = RequestMethod.POST)
    public RedirectView deleteAnswer(RedirectAttributes redirectAttributes, @ModelAttribute Answer answer,
                                       @PathVariable("id") Long ttId, @PathVariable("eId") Long eId) {
        service.deleteAnswer(eId);
        final String msg = "Created event <b>" + answer.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/teacher/question/{id}", true);
        redirectAttributes.addFlashAttribute("evMessage", msg);
        return view;
    }



    @GetMapping(value = "/main/teacher/answers/{id}")
    public String showAnswerById(Model model, @PathVariable("id") Long id) {
        Answer answer = service.getAnswerById(id);
        model.addAttribute("answer", answer);
        model.addAttribute("answer", new Answer());
        return "edit_questions";
    }


    // TODO: 4/5/2021
    @RequestMapping(path = "/main/teacher/answers/{id}/update", method = RequestMethod.POST)
    public String updateAnswers(Model model, @PathVariable("id") Long id, @ModelAttribute Answer answer) {
        Answer dbAnswer = service.updateAnswer(id, answer);
        model.addAttribute("answer", dbAnswer);
//        model.addAttribute("answer", new Answer());
        return "tests/edit_questions";
    }

}
