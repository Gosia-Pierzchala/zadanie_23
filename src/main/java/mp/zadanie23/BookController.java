package mp.zadanie23;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/dodawanie")
    public String formularzDodawania(Model model) {
        model.addAttribute("book", new Book());
        return "dodawanie";
    }

    @PostMapping("/dodawanie")
    public String dodajKsiazke(Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/usuwanie")
    public String formularzUsuwania(@RequestParam(name = "id", required = false, defaultValue = "1") Long id){
        return "usuwanie";
    }

    @PostMapping("/usuwanie")
    public String usunKsiazke(Book book, @RequestParam(name = "id") Long id) {
        book = bookRepository.findById(id);
        bookRepository.remove(book);
        return "redirect:/";
    }

    @GetMapping("/edytujKsiazke")
    public String edit(@RequestParam Long id, Model model) {
        Book book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "edytowanie";
    }

    @PostMapping("/edytujKsiazke")
    public String edit(@RequestParam Long id, Book book) {
        bookRepository.update(book, id);
        return "redirect:/";
    }

}