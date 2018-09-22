package mp.zadanie23;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private BookRepository bookRepository;

    public HomeController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false, defaultValue = "ALL") Action action) {
        List<Book> books;
        switch (action) {
            case ALL:
                books = bookRepository.findAll();
                break;
            case SORT_BY_TITLE_ASC:
                books = bookRepository.findAllByOrderByTitleAsc();
                break;
            case SORT_BY_TITLE_DESC:
                books = bookRepository.findAllByOrderByTitleDesc();
                break;
            case SORT_BY_DATE_ASC:
                books = bookRepository.findAllByOrderByDateAsc();
                break;
            case SORT_BY_DATE_DESC:
                books = bookRepository.findAllByOrderByDateDesc();
                break;
            default:
                books = new ArrayList<>();
        }
        model.addAttribute("books", books);
        return "home";
    }

}