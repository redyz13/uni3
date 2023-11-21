package ejb;

import javax.naming.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NamingException {
        Context ctx;
        
       ctx = new InitialContext();
       
       BookEJBRemote bookEJB = (BookEJBRemote) ctx.lookup("java:global/Libreria/BookEJB!ejb.BookEJBRemote");
       List<Book> books = bookEJB.findBooks();
       
       for(Book b : books) {
           System.out.println("---" + b);
       }
       
       Book book = new Book("Elden Ring", 1.0F, "Fantasy", "qqq", 471, true);
       
       book =  bookEJB.createBook(book);
        System.out.println("# Book created:" + book);
        book.setTitle("H2G2");
        book = bookEJB.updateBook(book);
        System.out.println("# Book updated:" + book);
        bookEJB.deleteBook(book);
        System.out.println("# Book deleted");
    }
}
