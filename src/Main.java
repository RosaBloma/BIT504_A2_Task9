import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**BIT504 Assessment 2
 Library Management System */

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Singleton design pattern
    static class LibraryData {

        private static final LibraryData INSTANCE = new LibraryData();

        private final ArrayList<Book> books = new ArrayList<>();
        private final ArrayList<Member> members = new ArrayList<>();
        
        private LibraryData() {
        }

        public static LibraryData getInstance() {
            return INSTANCE;
        }

        public ArrayList<Book> getBooks() {
            return books;
        }

        public ArrayList<Member> getMembers() {
            return members;
        }

        // load books from books.txt
        public void loadBooks() {

            try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",", -1);
                    if (data.length != 7) {
                        System.out.println("Invalid book record: " + line);
                        
                        continue;
                    }

                    try {
                        Book book = new Book(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim(),
                            data[5].trim(),
                            Integer.parseInt(data[6].trim())

                        );
                        books.add(book);

                    }
                    
                    catch (NumberFormatException e) {
                        System.out.println("Invalid book age rating.");

                    }

                }
                
                System.out.println("Books loaded; " + books.size());

            }

            catch (IOException e) {
                System.out.println("Error reading books.txt: " + e.getMessage());

            }
        }

        // loading members from the file.
        public void loadMembers() {

            try (BufferedReader reader = new BufferedReader(new FileReader("member.txt")))
            {
                String line;

                while ((line = reader.readLine()) != null)
                {
                    String[] data= line.split(",", -1);

                    if (data.length != 4) {
                        System.out.println("Invalid member record:" + line);

                        continue;
                    }

                    try {
                        Member member = new Member(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            Integer.parseInt(data[3].trim()));
                        
                        members.add(member);
                            
                    }

                    catch (NumberFormatException e) {
                        System.out.println("Invalid member age.");
                    }

                }

                System.out.println("Members loaded: " + members.size());
            }

            catch (IOException e) {
                System.out.println("Error reading members.txt: " + e.getMessage());

            }
            }
        }

        // Main method
        public static void main(String[] args) {

            LibraryData library = LibraryData.getInstance();

            System.out.println("--------------------");
            System.out.println(" Library management System ");

            library.loadBooks();
            library.loadMembers();

            mainMenu();
        }

        // making Main menu
        private static void mainMenu() {

            boolean running = true;

            while (running) {

                System.out.println("\n----- Main Menu -----");
                System.out.println("1. Book Mnagement");
                System.out.println("2. Member Management");
                System.out.println("3. Loan Management");
                System.out.println("4. Search");
                System.out.println("5. Exit");

                System.out.print("Select an option: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {

                    case "1":
                    bookMenu();
                    break;
                    
                    case "2":
                    memberMenu();
                    break;

                    case "3":
                        loanMenu();
                        break;

                    case "4":
                        searchMenu();
                        break;

                    case "5":
                        running = false;
                        System.out.println("Thank you for using the library.");
                        break;

                        default:
                            System.out.println("invalid option. Try again.");
                }
                
                }
            }

            // Book management menu
            private static void bookMenu() {

                boolean back = false;

                while (!back) {

                    System.out.println("\n------ Book Management -----");
                    System.out.println("a. Display all books");
                    System.out.println("b. Display borrowed books");
                    System.out.println("c. Display unborrowed books");
                    System.out.println("d. Add a new book");
                    System.out.println("e. Return");

                    System.out.print("Select an option: ");

                    String choice =
                
                scanner.nextLine().trim().toLowerCase();

                switch (choice) {

                    case "a":
                        displayBooks("all");
                        break;

                    case "b":
                        displayBooks("borrowed");
                        break;

                    case "c":
                        displayBooks("available");
                        break;

                    case "d":
                        addBook();
                        break;

                    case "e":
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid Option.");
                }


                }
            }

            // Member management menu
            private static void memberMenu() {

                boolean back = false;

                while (!back) {

                    System.out.println("\n------- Member management ------");
                    System.out.println("a. Display all members");
                    System.out.println("b. Add a new member");
                    System.out.println("c. Return");

                    System.out.print("Select an option: ");

                    String choice =
                
                scanner.nextLine().trim().toLowerCase();

                    switch (choice) {

                        case "a":
                            displayMembers();
                            break;

                        case "b":
                            addMember();
                            break;

                        case "c":
                            back = true;
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }
                }
            }

            // loan management menu
            private static void loanMenu() {

                boolean back = false;

                while (!back) {

                    System.out.println("\n---------- Loan management------");
                    System.out.println("a. Check out a book");
                    System.out.println("b. Check in a book");
                    System.out.println("c. Return");

                    System.out.print("Select an option: ");

                    String choice = scanner.nextLine().trim().toLowerCase();

                    switch (choice) {

                        case "a":
                            checkOutBook();
                            break;

                        case "b":
                            checkInBook();
                            break;

                        case "c":
                            back = true;
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }

                }
            }
            
            //Search menu
            private static void searchMenu() {

                boolean back = false;

                while (!back) {

                    System.out.println("\n---------- Search --------");
                    System.out.println("a. Find a member");
                    System.out.println("b. Find a book");
                    System.out.println("c. Return");

                    System.out.print("Select an option: ");

                    String choice = scanner.nextLine().trim().toLowerCase();

                    switch (choice) {

                        case "a":
                            searchMember();
                            break;

                        case "b":
                            searchBook();
                            break;

                        case "c":
                            back = true;
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }
                }
            }
            
            // Display books
            private static void displayBooks(String filter) {

                ArrayList<Book> books =
                    LibraryData.getInstance().getBooks();

                    System.out.println("\n-------- Book List ------");

                    System.out.printf("%-5s %-22s %-28s %-20s %-12s%n", "ID", "ISBN", "TITLE", "AUTHOR", "STATUS");

                    for (Book book : books) {
                    
                    boolean show = filter.equals("all") || (filter.equals("borrowed") && book.isBorrowed()) || (filter.equals("available") && ! book.isBorrowed());

                    if (show) {

                        System.out.printf("%-5s %-22s %-28s %-20s %-12s%n", book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.isBorrowed() ? "Borrowed" : "Available");

                    
                    }
                    }
            }
            // Display members
            private static void displayMembers() {

                ArrayList<Member> members = 
                    LibraryData.getInstance().getMembers();

                System.out.println("\n-------- Member list -------");
                System.out.printf("%-8s %-18s %-18s %-8s%n", "ID", "FIRST NAME", "LAST NAME", "AGE");

                for (Member member : members) {

                    System.out.printf("%-8s %-18s %-18s %-8s%n", member.getId(), member.getFirstName(), member.getLastName(), member.getAge());

                }
            }

            // Find book by ID
            private static Book findBookById(String id) {

                for (Book book : LibraryData.getInstance().getBooks()) {

                    if (book.getId
                    ().equals(id)) {
                        return book;

                    }
                }

                return null;

            }

            // Find member by ID
            private static Member findMemberById(String id) {

                for (Member member : LibraryData.getInstance().getMembers()) {

                    if (member.getId().equals(id)) {
                        return member;
                    }
                }

                return null;
            }

            // Validate required input
            private static String getRequiredInput(String message) {

                while (true) {

                    System.out.print(message);

                    String value = scanner.nextLine().trim();

                    if (!value.isEmpty() && !value.contains(",")) {
                        return value;

                    }

                    System.out.println("Invalid input. Try again.");

                }
            }

            // Validate numbers
            private static int getNumber(String message, int min, int max)
            {

                while (true) {

                    System.out.print(message);

                    String value = scanner.nextLine().trim();

                    try {

                        int number = Integer.parseInt(value);

                        if (number >= min && number <= max) { 
                            return number; 

                        }

                    }

                    catch (NumberFormatException e) {
                        
                    }

                    System.out.println("Enter a number between " + min + " and " + max);
                }
                }

                // Add a new book
                private static void addBook() {

                    ArrayList<Book> books =
                    LibraryData.getInstance().getBooks();

                    System.out.println("\n------ Add new book ----");

                    String id;

                    while (true) {

                        id = getRequiredInput("Enter book ID: ");

                        if (findBookById(id) == null) {
                            break;
                        }

                        System.out.println("Book ID already exists.");
                    }

                    String isbn;

                    while (true) {

                        isbn = getRequiredInput("Enter ISBN (10 characters): ");

                        if (isbn.length() != 10) {
                            System.out.println("ISBN must be exactly 10 characters.");
                            continue;

                        }

                        boolean duplicate = false;

                        for (Book book : books) {

                            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                                duplicate = true;
                                break;
                            }
                        }

                        if (!duplicate) {
                            break;
                        }

                        System.out.println("ISBN already exists.");

                    }

                    String title = getRequiredInput("Enter book title: ");
                    String author = getRequiredInput("Enter author: ");
                    String date = getRequiredInput("Enter publication date: ");
                    String genre = getRequiredInput("Enter genre: ");
                    int ageRating = getNumber("Enter age rating: ", 0, 125);

                    Book newBook = new Book(id, isbn, title, author, date, genre, ageRating);

                    books.add(newBook);

                    System.out.println("Book added successfully.");
                    
            }

            // Add new member
            private static void addMember() {

                System.out.println("\n------ Add new member ------");

                String id;

                while (true) {

                    id = getRequiredInput("Enter member ID: ");

                    if (findMemberById(id) == null) {
                        break;
                    }

                    System.out.println("Member ID already exists.");
                }

                String firstName = getRequiredInput("Enter first name: ");
                String lastName = getRequiredInput("Enter last name: ");
                int age = getNumber("Enter age (0-125): ", 0, 125);

                Member newMember = new Member(id, firstName, lastName, age);

                LibraryData.getInstance().getMembers().add(newMember);

                System.out.println("Member added successfully.");
            }

            // Checking out book
            private static void checkOutBook() {

                System.out.println("\n-------- Check out book -------");

                String bookId = getRequiredInput("Enter book ID: ");

                Book book = findBookById(bookId);

                if (book == null) {
                    System.out.println("Book not found.");
                    return;
                }

                if (book.isBorrowed()) {
                    System.out.println("This book is already borrowed");
                    return;

                }

                String memberId = getRequiredInput("Enter member ID: ");

                Member member = findMemberById(memberId);

                if (member == null) {
                    System.out.println("Member not found.");
                    return;
                }

                if (member.getAge() < book.getAgeRating()) {

                    System.out.println("Age restriction warning");
                    System.out.println("member must be at least " + book.getAgeRating() + " years old.");
                    return;
                }

                System.out.println("Book: " + book.getTitle());
                System.out.println("Borrower: " + member.getFullName());

                System.out.print("Confirm checkout? (yes/no): ");

                String confirmation = scanner.nextLine().trim();

                if (confirmation.equalsIgnoreCase("yes")) {
                    book.setBorrowedByMember(member.getId());

                    System.out.println("Book checked out successfully.");

                }

                else {

                    System.out.println("Checkout cancelled.");

                }
            }

            // Checking out book
            private static void checkInBook() {

                System.out.println("\n------- Check in book ----");

                String id = getRequiredInput("Enter book ID: ");

                Book book = findBookById(id);

                if (book == null) {
                    System.out.println("Book not found.");
                    return;

                }

                if (!book.isBorrowed()) {
                    System.out.println("This book is not currently borrowed.");
                    return;

                }

                book.setBorrowedByMember(null);

                System.out.println("Book retruned successfully: " + book.getTitle());

            }

            // Search members by last name
            private static void searchMember() {

                System.out.println("\n------- Find member -------");

                String search = getRequiredInput("Enter meber last name: ").toLowerCase();

                boolean found = false;

                for (Member member : LibraryData.getInstance().getMembers()) {

                    if (member.getLastName().toLowerCase().contains(search)) {

                        found = true;

                        System.out.println("\nMember ID: " + member.getId());
                        System.out.println("Name: " + member.getFullName());
                        System.out.println("Age: " + member.getAge());
                        System.out.println("Currently borrowed books:");

                        boolean hasBooks = false;

                        for (Book book : LibraryData.getInstance().getBooks()) {

                            if (member.getId().equals(book.getBorrowedByMember())) {

                                System.out.println("- " + book.getTitle());
                                hasBooks = true;
                            }
                        }

                        if (!hasBooks) {
                            System.out.println("No books currently borrowed.");

                        }
                    }
                }

                if (!found) {
                    System.out.println("No matching members found.");
                }
            }
            
            // Search books by title
            private static void searchBook() {

                System.out.println("\n--------- Find book ------");

                String search = getRequiredInput("Enter book title: ").toLowerCase();
            
                boolean found = false;

                for (Book book : LibraryData.getInstance().getBooks()) {

                    if (book.getTitle().toLowerCase().contains(search)) {

                        found = true;

                        System.out.println("\nID: " + book.getId());
                        System.out.println("ISBN: " + book.getIsbn());
                        System.out.println("Author: " + book.getAuthor());
                        System.out.println("Publish date: " + book.getDatePublished());
                        System.out.println("Genre: " + book.getGenre());
                        System.out.println("Age rating: " + book.getAgeRating());
                        
                        if (book.isBorrowed()) {

                            Member borrower = findMemberById(book.getBorrowedByMember());

                            if (borrower != null) {
                                System.out.println("Borrowed by: " + borrower.getFullName());

                            }

                        } else

                            {

                                System.out.println("Status: Available");
                            }

                        
                    }
                }
            
                if (!found) {
                    System.out.println("No matching books found.");
                }
            }
}


