package eu.judegam.wopfe.controllers.tests;


import eu.judegam.wopfe.models.repositories.school.tests.service.QuestionService;
import eu.judegam.wopfe.models.tests.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService service;

    @PostMapping("/addQuestion")
    public Question addQuestion(@RequestBody Question question) {
        return service.saveQuestion(question);
    }

}
