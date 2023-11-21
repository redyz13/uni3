package ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

public class DatabasePopulator {
    @Inject
    private BookEJB bookEJB;
    private Book h2g2, lord;
    
    @PostConstruct
    private void populateDB() {
        h2g2 = new Book("Beginning Java EE7", 35F, "Great Book", "asd", 605, true);
        lord = new Book("The Lord of the Ring", 50.4F, "SciFi", "asd", 1216, true);
       
        bookEJB.createBook(h2g2);
        bookEJB.createBook(lord);
    }
    
    @PreDestroy
    private void clearDB() {
        bookEJB.deleteBook(lord);
        bookEJB.deleteBook(h2g2);
    }
}
