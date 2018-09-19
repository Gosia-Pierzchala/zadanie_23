package mp.zadanie23;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BookRepository {
    private EntityManager entityManager;

    public BookRepository(EntityManagerFactory entityManagerFactory) {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public Book findById(Long id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }

    public void save(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public void remove(Book book) {
        entityManager.getTransaction().begin();
        entityManager.remove(book);
        entityManager.getTransaction().commit();
    }

    public void edit(Book book, String title, String description, String isbn, Category category) {
        entityManager.getTransaction().begin();
        book.setTitle(title);
        book.setDescription(description);
        book.setIsbn(isbn);
        book.setCategory(category);
        entityManager.getTransaction().commit();
    }

    public List<Book> sortByTitleAsc(){
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b order by b.title", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> sortByTitleDesc(){
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b order by b.title DESC", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

}