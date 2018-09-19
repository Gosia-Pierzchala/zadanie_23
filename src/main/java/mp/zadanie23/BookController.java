package mp.zadanie23;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
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
    public String formularzUsuwania(@RequestParam(name = "id", required = false, defaultValue = "1") Long id){
        return "usuwanie";
    }

    @PostMapping("/usuwanie")
    public String usunKsiazke(Book book, @RequestParam(name = "id") Long id) {
        book = bookRepository.findById(id);
        bookRepository.remove(book);
        return "redirect:/";
    }

    @GetMapping("/edytowanie")
    public String formularzEdytowania(Model model, @RequestParam (value = "id", required = false, defaultValue = "1") Long id){
        Book book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "edytowanie";
    }

    @PostMapping("/edytowanie")
    public String edytujKsiazke(Book book, @RequestParam (value = "id") Long id,
                                @RequestParam (value = "title", required = false) String title,
                                @RequestParam (value = "description", required = false) String description,
                                @RequestParam (value = "isbn", required = false) String isbn,
                                @RequestParam (value = "category", required = false) Category category){
        bookRepository.edit(book, title, description, isbn, category);
        return "redirect:/";
    }

    @GetMapping("/sortowanie")
    public String formularzSortowania(@RequestParam(name = "Sposób sortowania", required = false) String userInput){
        return "sortowanie";
    }

    @PostMapping("/sortowanie")
    public String posortujliste (List<Book> books, @RequestParam(name = "Sposób sortowania") String userInput) {
        if(userInput.equals("po tytule rosnaco")){
            books = bookRepository.sortByTitleAsc();
        } else if(userInput.equals("po tytule malejaco")){
            books = bookRepository.sortByTitleDesc();
        }
        return "redirect:/";
    }

}