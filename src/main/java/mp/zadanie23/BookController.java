package mp.zadanie23;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String formularzUsuwania(@RequestParam(value = "id", required = false, defaultValue = "1") Long id, Model model){
        List<Book> books = bookRepository.findAll();
        Book book = bookRepository.findById(id);
        model.addAttribute("books", books);
        model.addAttribute("book", book);
        return "usuwanie";
    }

    @PostMapping("/usuwanie")
    public String usunKsiazke(Book book) {
        bookRepository.remove(book);
        return "redirect:/";
    }

    @GetMapping("/edytowanie")
    public String formularzEdytowania(@RequestParam (value = "id", required = false, defaultValue = "1") Long id, Model model){
        List<Book> books = bookRepository.findAll();
        Book book = bookRepository.findById(id);
        model.addAttribute("books", books);
        model.addAttribute("oldBook", book);
        model.addAttribute("newBook", new Book());
        return "edytowanie";
    }

    @PostMapping("/edytowanie")
    public String edytujKsiazke(Book oldBook, Book newBook){
        bookRepository.edit(oldBook, newBook);
        return "redirect:/";
    }
}