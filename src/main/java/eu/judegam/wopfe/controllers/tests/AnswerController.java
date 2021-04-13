package eu.judegam.wopfe.controllers.tests;

import eu.judegam.wopfe.services.AnswerService;
import eu.judegam.wopfe.models.tests.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AnswerController {

    @Autowired
    private AnswerService service;

    @RequestMapping(path = "/main/teacher/questionsAnswer/{id}/addAnswer", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView saveAnswer(RedirectAttributes redirectAttributes, @ModelAttribute Answer answer,
                                   @PathVariable("id") Long questionId) {
        service.saveAnswer(answer, questionId);
        final String msg = "Created answer <b>" + answer.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/teacher/questions/{id}", true);
        redirectAttributes.addFlashAttribute("qMessage", msg);
        return view;
    }

    @RequestMapping(path = "/main/teacher/question/{id}/deleteAnswer/{eId}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView deleteAnswer(RedirectAttributes redirectAttributes, @ModelAttribute Answer answer,
                                     @PathVariable("id") Long ttId, @PathVariable("eId") Long eId) {
        service.deleteAnswer(eId);
        final String msg = "Created event <b>" + answer.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/teacher/questions/{id}", true);
        redirectAttributes.addFlashAttribute("evMessage", msg);
        return view;
    }


    // TODO: dont think that it is a good idea to update answers, you can just delete old one and create a new one.
    @RequestMapping(path = "/main/teacher/answers/{id}/update", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String updateAnswers(Model model, @PathVariable("id") Long id, @ModelAttribute Answer answer) {
        Answer dbAnswer = service.updateAnswer(id, answer);
        model.addAttribute("answer", dbAnswer);
//        model.addAttribute("answer", new Answer());
        return "tests/edit_questions";
    }


// TODO: 4/7/2021 Change boolean in answers
//    @RequestMapping(path = "/main/teacher/Answer/change/boolean/{id}", method = RequestMethod.POST)
//    public String changeAnswersboolean(Model model, @PathVariable("id") Long id) {
//        Answer answer = service.getAnswerById(id);
//        answer.setTrue(true);
//        model.addAttribute("answer", answer);
//        return "tests/edit_questions";
//    }

}
