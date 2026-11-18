
/** Represents a book in library */

public class Book {
    private String id;
    private String isbn;
    private String title;
    private String author;
    private String datePublished;
    private String genre;
    private int ageRating;

    // loan info
    private boolean borrowed;
    private String borrowedByMember;

    /** Constructor for creating book */

    public Book(String id, String isbn, String title, String author, String publicationDate, String genre, int ageRating) 
    {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.datePublished = datePublished;
        this.genre = genre;
        this.ageRating = ageRating;

        this.borrowed = false;
        this.borrowedByMember = null;

    }
    // Getters
    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getGenre() {
        return genre;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public String getBorrowedByMember() {
        return borrowedByMember;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.author = author;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public void setBorrowedByMember(String memberId) {
        this.borrowedByMember = memberId;

    }

    // check whether book is borrowed.
    public boolean isBorrowed() {
        return borrowedByMember !=null;
    }


}