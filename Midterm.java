
class Librarian {
    private String name;
    private int employeeId;

    public Librarian(String name, int employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}

// LibraryItem class (base class)
abstract class LibraryItem {
    private String title;
    private String publicationDate;
    private boolean availability;
    private Librarian librarian;

    public LibraryItem(String title, String publicationDate, boolean availability, Librarian librarian) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.availability = availability;
        this.librarian = librarian;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Librarian getLibrarian() {
        return librarian;
    }
}

// Book class (subclass of LibraryItem)
class Book extends LibraryItem {
    private String author;

    public Book(String title, String publicationDate, boolean availability, Librarian librarian, String author) {
        super(title, publicationDate, availability, librarian);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

// DVD class (subclass of LibraryItem)
class DVD extends LibraryItem {
    private int duration; // duration in minutes

    public DVD(String title, String publicationDate, boolean availability, Librarian librarian, int duration) {
        super(title, publicationDate, availability, librarian);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

// Main class to demonstrate the functionality
public class Midterm{
    public static void main(String[] args) {

        Librarian l1= new Librarian("saif", 101);


        Book b1 = new Book("bange-e-dara", "1925-04-10", true, l1, "iqbal");


        System.out.println("Book Title: " + b1.getTitle());
        System.out.println("Librarian Name: " + b1.getLibrarian().getName());

        b1.setTitle("bange-dara");
        l1.setName("iqbal");


        System.out.println("Updated Book Title: " + b1.getTitle());
        System.out.println("Updated Librarian Name: " + b1.getLibrarian().getName());
    }
}