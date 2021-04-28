package eu.judegam.wopfe.controllers.tests;

import eu.judegam.wopfe.models.tests.Question;
import eu.judegam.wopfe.services.AnswerService;
import eu.judegam.wopfe.models.tests.Answer;
import eu.judegam.wopfe.utils.Utils;
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

    @RequestMapping(path = "/main/questionsAnswer/{id}/addAnswer", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public RedirectView saveAnswer(RedirectAttributes redirectAttributes, @ModelAttribute Answer answer,
                                   @PathVariable("id") Long questionId) {
        service.saveAnswer(answer, questionId);
        final String msg = "Created answer <b>" + answer.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/questions/{id}", true);
        redirectAttributes.addFlashAttribute("qMessage", msg);
        return view;
    }

    @RequestMapping(path = "/main/question/{id}/deleteAnswer/{eId}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public RedirectView deleteAnswer(RedirectAttributes redirectAttributes, @ModelAttribute Answer answer,
                                     @PathVariable("id") Long ttId, @PathVariable("eId") Long eId) {
        service.deleteAnswer(eId);
        final String msg = "Created event <b>" + answer.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/questions/{id}", true);
        redirectAttributes.addFlashAttribute("evMessage", msg);
        return view;
    }

}
