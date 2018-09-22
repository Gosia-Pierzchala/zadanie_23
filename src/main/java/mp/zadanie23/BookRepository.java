package mp.zadanie23;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
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

    public List<Book> findAllByOrderByTitleAsc(){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.title", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllByOrderByTitleDesc(){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllByOrderByDateAsc(){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.releaseDate", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllByOrderByDateDesc(){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.releaseDate DESC", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

}