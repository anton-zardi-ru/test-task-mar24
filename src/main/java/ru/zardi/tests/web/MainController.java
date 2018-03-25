package ru.zardi.tests.web;

import com.google.code.stackexchange.schema.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.zardi.tests.services.StackOverflowService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anton Petrov
 * Time: 16:33
 * Date: 2018-03-24.
 */
@Controller
public class MainController {
    private final StackOverflowService stackOverflowService;

    @Autowired
    public MainController(StackOverflowService stackOverflowService) {
        this.stackOverflowService = stackOverflowService;
    }

    @GetMapping("/")
    public ModelAndView showIndexPage() {
        return new ModelAndView("index", "command", QueryModel.DEFAULT_MODEL);
    }

    @PostMapping("/")
    public ModelAndView showIndexPage(@ModelAttribute QueryModel command) {
        Map<String, Object> model = new HashMap<>(2);
        model.put("command", command);
        try {
            model.put("questions", stackOverflowService.search(command));
        } catch (Exception exepction) {
            model.put("error", true);
        }

        return new ModelAndView("index", model);
    }

    @ModelAttribute("sortOrderValues")
    public User.QuestionSortOrder[] getOrderValues() {
        return new User.QuestionSortOrder[]{User.QuestionSortOrder.MOST_RELEVANT, User.QuestionSortOrder.LEAST_RELEVANT, User.QuestionSortOrder.MOST_VOTED,
                User.QuestionSortOrder.LEAST_VOTED,
                User.QuestionSortOrder.MOST_ACTIVITY, User.QuestionSortOrder.LEAST_ACTIVITY, User.QuestionSortOrder.MOST_RECENTLY_CREATED,
                User.QuestionSortOrder.LEAST_RECENTLY_CREATED
        };
    }

}
