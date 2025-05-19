import java.io.*;
import java.util.*;

// Main class
 class BookStoreWriter {

    // Serializable Person class
    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        public String name;

        public Person(String name) {
            this.name = name;
        }
    }

    // Serializable Book class
    static class Book implements Serializable {
        private static final long serialVersionUID = 1L;
        public String name;
        public String publisher;
        public Person author;

        public Book(String name, String publisher, Person author) {
            this.name = name;
            this.publisher = publisher;
            this.author = author;
        }
    }

    // Method to write 5 Book objects to a file
    public void writeBooksToFile() {
            List<Book> books = new ArrayList<>();

            books.add(new Book("urdu", "Pearson", new Person("By IQBAL")));
            books.add(new Book("DBMS", "O'Reilly", new Person("Nasir Mehdi")));
            books.add(new Book("Islamic study", "Tariq Jameel", new Person("SIR Ali")));
            books.add(new Book("Data Structures", "Oxford", new Person("Sir adnan")));
            books.add(new Book("Algorithms", "it", new Person("noor")));

            for (Book book : books) {
                out.writeObject(book);
            }

            System.out.println("5 Book objects written to 'BookStore' successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Main method
    public static void main(String[] args) {
        BookStoreWriter writer = new BookStoreWriter();
        writer.writeBooksToFile();
    }
}
