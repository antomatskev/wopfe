package eu.judegam.wopfe.controllers.tests;


import eu.judegam.wopfe.models.repositories.school.tests.service.QuestionService;
import eu.judegam.wopfe.models.tests.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
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

}
