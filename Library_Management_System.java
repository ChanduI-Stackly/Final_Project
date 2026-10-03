package Final_Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library_Management_System {

	// This class stores book information
	static class Book{
		
		String bookId;
		String title;
		String author;
		
		boolean available;
		String issuedTo;
		LocalDate dueDate;
		
		Book(String bookId, String title, String author){
			
			this.bookId = bookId;
			this.title = title;
			this.author =author;
			
			available = true;
			issuedTo = null;
			dueDate = null;
		}
	}
	
	// This class stores Librarian information
	static class Librarian{
		
		String name;
		
		Librarian(String name){
			this.name = name;
		}
	}
	
	// Stores all books
	static ArrayList<Book> books = new ArrayList<>();
	
	// Stores currently issued books
	static Map<String, Book> issuedBooks = new HashMap<>();
	
	// Scanner for user input
	static Scanner scanner = new Scanner(System.in);
	
	// Librarian object
	static Librarian librarian = new Librarian("Library Admin");
	
	// Fine per late day
	static final double FINE_PER_DAY = 5.0;
    
    public static void main(String[] args) {

    	// Adding some books to the library
    	books.add(new Book("B101", "Java Programming", "James"));    	
    	books.add(new Book("B102", "Spring Boot", "Craig"));    	
    	books.add(new Book("B103", "SQL Basics", "John"));
    	
    	System.out.println("-------- LIBRARY MANAGEMENT SYSTEM -----------");
    	
    	while(true) {
    		
    		showMenu();
    		
    		System.out.print("\nEnter your choice: ");
    		
    		try {
				
    			int choice = scanner.nextInt();
    			scanner.nextLine();
    			
    			switch (choice) {
				case 1:
					addBook();
					break;
					
				case 2:
					displayAllBooks();
					break;
					
				case 3:
					searchBook();
					break;
					
				case 4:
					checkBookAvailability();
					break;
					
				case 5:
					requestBook();
					break;
					
				case 6:
					returnBook();
					break;
					
				case 7:
					checkDueDate();
					break;
					
				case 8:
					collectFine();
					break;
					
				case 9:
					showReports();
					break;
					
				case 10:
					System.out.println("\nThank you for using Library Management System.");
					scanner.close();
					return;
					
				default:
					System.out.println("\nPlease enter a valid menu number.");
				}
    			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("\nInvalid input. Please enter a number.");
				
				scanner.nextLine();
			}
    	}
    	
    }
    
    // Display menu
    static void showMenu() {
    	
    	System.out.println("\n--------- MAIN MENU -----------");
    	
    	System.out.println("1. Add Book");
    	System.out.println("2. Display All Books");
    	System.out.println("3. Search Book");
    	System.out.println("4. Check Book Availability");
    	System.out.println("5. Request / Issue Book");
    	System.out.println("6. Return Book");
    	System.out.println("7. Check Due Date");
    	System.out.println("8. Collect Fine");
    	System.out.println("9. Library reports");
    	System.out.println("10. Exit");
    	
    }
    
    // Add a new book
    static void addBook() {
    	
    	System.out.println("\n----- ADD BOOK -----");
    	
    	System.out.print("Enter book ID: ");
    	String bookId = scanner.nextLine();
    	
    	// Check duplicate book ID
    	for (Book book : books) {
			
    		if (book.bookId.equalsIgnoreCase(bookId)) {
				
    			System.out.println("Book ID already exists.");
    			
    			return;
			}
		}
    	
    	System.out.print("Enter book title: ");
    	String title = scanner.nextLine();
    	
    	System.out.print("Enter author name: ");
    	String author = scanner.nextLine();
    	
    	Book newBook = new Book(bookId, title, author);
    	
    	books.add(newBook);
    	
    	System.out.println("Book added successfully.");
    }
    
    // Display all books
    static void displayAllBooks() {
    	
    	System.out.println("\n------ ALL BOOKS --------");
    	
    	if (books.isEmpty()) {
			
    		System.out.println("No books available.");
    		return;
		}
    	
    	for (Book book : books) {
			
    		String status;
    		
    		if (book.available) {
				status = "Available";
			} else {
				status = "Issued";
			}
    		
    		System.out.println("ID: " + book.bookId + "| Title: " + book.title + "| Author: " + book.author + "| Status: " + status);
		}
    }
    
    // Search book by title
    static void searchBook() {
    	
    	System.out.println("\n------ SEARCH BOOK -------");
    	
    	System.out.print("Enter book title: ");
    	String searchTitle = scanner.nextLine();
    	
    	boolean found = false;
    	
    	for (Book book : books) {
			
    		if (book.title.equalsIgnoreCase(searchTitle)) {
				
    			
    			System.out.println("\nBook found.");
    			System.out.println("Book ID: " + book.bookId);
    			System.out.println("Title: " + book.title);
    			System.out.println("Author: " + book.author);
    			
    			if (book.available) {
					System.out.println("Status: Avilable");
				} else {
					System.out.println("Status: Issued");
				}
    			
    			found = true;
			}
		}
    	
    	if (!found) {
			System.out.println("Book noot found.");
		}
    }
    
    // Check whether a book is available
    static void checkBookAvailability() {
    	
    	System.out.println("\n------ CHECK AVAILABILITY ------");
    	
    	System.out.print("Enter book ID: ");
    	String bookId = scanner.nextLine();
    	
    	Book book = findBook(bookId);
    	
    	if (book == null ) {
			
    		System.out.println("Book not found.");
    		return;
		}
    	
    	if (book.available) {
			
    		System.out.println("The book is available.");
		}else {
			System.out.println("The book is currently issued.");
		}
    	
    }
    
    // Student requests and issues a book
    static void requestBook() {
    	
    	System.out.println("\n----- REQUEST / ISSUE BOOK -----");
    	
    	System.out.print("Enter book ID: ");
    	String bookId = scanner.nextLine();
    	
    	Book book = findBook(bookId);
    	
    	if (book == null) {
			
    		System.out.println("Book not found.");
    		return;
		}
    	
    	if (!book.available) {
			
    		System.out.println("This book is already issued.");
    		return;
		}
    	
    	System.out.print("Enter student name: ");
    	String studentName = scanner.nextLine();
    	
    	System.out.println("\nRequest sent to librarian: " + librarian.name);
    	
    	System.out.println("Librarian approval (yes/no)");
    	
    	String approval = scanner.nextLine();
    	
    	if (!approval.equalsIgnoreCase("yes")) {
    		
    		System.out.println("Book issue request was not approved.");
    		return;
			
		}
    	
    	// Book is issued after approval
    	book.available = false;
    	book.issuedTo = studentName;
    	
    	// Student gets 14 days to return the book
    	book.dueDate = LocalDate.now().plusDays(14);
    	
    	// Add book to issued books amp
    	issuedBooks.put(book.bookId, book);
    	
    	System.out.println("\nBook issued successfully.");
    	
    	System.out.println("Student: " + studentName);    	
    	System.out.println("Due Date: " + book.dueDate);
    	
    }
    
    // Return book
    static void returnBook() {
    	
    	System.out.println("\n----- RETURN BOOK ------");
    	
    	System.out.print("Enter book ID: ");
    	String bookID = scanner.nextLine();
    	
    	if (!issuedBooks.containsKey(bookID)) {
			
    		System.out.println("This book is not currently issued.");
    		return;
		}
    	
    	Book book = issuedBooks.get(bookID);
    	
    	// Calculate late days
    	long lateDays = LocalDate.now().toEpochDay()-book.dueDate.toEpochDay();
    	
    	if (lateDays > 0) {
			
    		double fine = lateDays * FINE_PER_DAY;
    		
    		System.out.println("Book returned late.");    		
    		System.out.println("Late Days: " + lateDays);    		
    		System.out.println("Fine Amount: Rs." + fine);
    		
		} else {
			
			System.out.println("Book returned on time.");			
			System.out.println("Fine Amount: Rs.0");
			
		}
    	
    	// Make book available again
    	book.available = true;
    	
    	// Remove student information
    	book.issuedTo = null;
    	
    	// Remove due date
    	book.dueDate = null;
    	
    	//Remove from issued book list
    	issuedBooks.remove(bookID);
    	
    	System.out.println("Book returned successfully.");
    	
    }
    
 // Check due date
    static void checkDueDate() {

        System.out.println("\n----- DUE DATE -----");

        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();
        
        if (!issuedBooks.containsKey(bookId)) {
			
        	System.out.println("This book is not currently issued.");
        	return;
		}
        
        Book book = issuedBooks.get(bookId);
        
        System.out.println("Book Title: " + book.title);
        System.out.println("Issued To: " + book.issuedTo);
        System.out.println("Due Date: " + book.dueDate);
        
        LocalDate today = LocalDate.now();
        
        if (today.isAfter(book.dueDate)) {
			
        	long lateDays = today.toEpochDay()-book.dueDate.toEpochDay();
        	
        	double fine = lateDays * FINE_PER_DAY;
        	
        	System.out.println("Status: Overdue");
        	System.out.println("Late days: " + lateDays);
        	System.out.println("Current Fine: Rs." + fine);
        	
		}else {
			
			System.out.println("Status: Book is within due date.");
		}
    }
    
    // Collect fine
    static void collectFine() {

        System.out.println("\n----- COLLECT FINE -----");

        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();
        
        if (!issuedBooks.containsKey(bookId)) {
			
        	System.out.println("This book is not currently issued.");
        	return;
        	
		}
        
        Book book = issuedBooks.get(bookId);
        
        LocalDate today = LocalDate.now();
        
        if(!today.isAfter(book.dueDate)) {
        	
        	System.out.println("No fine is required.");
        	return;
        }
        
        long lateDays = today.toEpochDay()-book.dueDate.toEpochDay();
        
        double fine = lateDays * FINE_PER_DAY;
        
        System.out.println("Student: " + book.issuedTo);
        
        System.out.println("Late Days: " + lateDays);
        
        System.out.println("Fine Amount: Rs. " + fine);
        
        System.out.print("Has the fine been paid? (yes/no): ");
        
        String payment = scanner.nextLine();
        
        if (payment.equalsIgnoreCase("yes")) {
			
        	System.out.println("Fine collected successfully.");
		}else {
			
			System.out.println("Fine is still pending.");
		}
    }
    
    // Display library reports
    static void showReports() {
    	
    	System.out.println("\n-----LIBRARY REPORTS --------");
    	
    	int availableBooks = 0;
    	int issuedBookCount = 0;
    	
    	for (Book book : books) {
			if (book.available) {
				availableBooks++;
			} else {
				issuedBookCount++;
			}
		}
    	
    	System.out.println("Total Books : " + books.size());
    	
    	System.out.println("Available Books : " + availableBooks);
    	
    	System.out.println("Issued Books : " + issuedBookCount);
    	
    	System.out.println("Currently Issued Book Details:");
    	
    	if (issuedBooks.isEmpty()) {
			
    		System.out.println("No books are currently issued.");
    		
		} else {
			for (Book book : issuedBooks.values()) {
				
				System.out.println(book.bookId + " | " + book.title + " | Issued To: " + book.issuedTo + " | Due: " + book.dueDate);
				
			}
		}
    }
    
    // Find a book using book ID
    static Book findBook(String bookId) {
    	
    	for (Book book : books) {
			
    		if (book.bookId.equalsIgnoreCase(bookId)) {
    			
    			return book;
			}
		}
    	
    	return null;
    }
}