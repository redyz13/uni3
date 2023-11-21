package jpa_lab2;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
    public static void main(String[] args) {
        Book book = new Book("H2G2", 12.5F, "The Hitchhiker's Guide to the Galaxy",
        "1-84023-742-2", 354, false);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Lab2PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        em.persist(book);
        tx.commit();
        
        System.out.println("Libro inserito");
        System.out.println("####### " + book.getDescription());
        
        book = em.createNamedQuery("findBookH2G2", Book.class).getSingleResult();
        System.out.println("Query per H2G2");
        System.out.println("####### " + book.getDescription());
        
        Query all = em.createNamedQuery("findAllBooks", Book.class);
        
        List<Book> result = all.getResultList();
        
        System.out.println("Query per tutti i libri");
        for (Book b : result)
            System.out.println(b.getTitle());
        
        em.close();
        emf.close();
    }
}
