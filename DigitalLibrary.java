package intership;

import java.util.*;
class Book {
 private int id;
 private String title;
 private String author;
 private boolean isIssued;

 public Book(int id, String title, String author) {
     this.id = id;
     this.title = title;
     this.author = author;
     this.isIssued = false;
 }

 public int getId() {
     return id;
 }

 public String getTitle() {
     return title;
 }

 public String getAuthor() {
     return author;
 }

 public boolean isIssued() {
     return isIssued;
 }

 public void setIssued(boolean issued) {
     isIssued = issued;
 }

 @Override
 public String toString() {
     return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + (isIssued ? "Yes" : "No");
 }
}
class Library {
 private List<Book> books = new ArrayList<>();
 private Scanner scanner = new Scanner(System.in);

 public void addBook() {
     System.out.print("Enter Book ID: ");
     int id = scanner.nextInt();
     scanner.nextLine(); // Consume newline
     System.out.print("Enter Book Title: ");
     String title = scanner.nextLine();
     System.out.print("Enter Book Author: ");
     String author = scanner.nextLine();
     books.add(new Book(id, title, author));
     System.out.println("Book added successfully.");
 }

 public void viewBooks() {
     if (books.isEmpty()) {
         System.out.println("No books available.");
     } else {
         for (Book book : books) {
             System.out.println(book);
         }
     }
 }

 public void issueBook() {
     System.out.print("Enter Book ID to issue: ");
     int id = scanner.nextInt();
     for (Book book : books) {
         if (book.getId() == id) {
             if (!book.isIssued()) {
                 book.setIssued(true);
                 System.out.println("Book issued successfully.");
             } else {
                 System.out.println("Book is already issued.");
             }
             return;
         }
     }
     System.out.println("Book not found.");
 }

 public void returnBook() {
     System.out.print("Enter Book ID to return: ");
     int id = scanner.nextInt();
     for (Book book : books) {
         if (book.getId() == id) {
             if (book.isIssued()) {
                 book.setIssued(false);
                 System.out.println("Book returned successfully.");
             } else {
                 System.out.println("Book was not issued.");
             }
             return;
         }
     }
     System.out.println("Book not found.");
 }

 public void removeBook() {
     System.out.print("Enter Book ID to remove: ");
     int id = scanner.nextInt();
     books.removeIf(book -> book.getId() == id);
     System.out.println("Book removed successfully.");
 }

 public void searchBook() {
     System.out.print("Enter keyword to search: ");
     scanner.nextLine();
     String keyword = scanner.nextLine();
     boolean found = false;
     for (Book book : books) {
         if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword)) {
             System.out.println(book);
             found = true;
         }
     }
     if (!found) {
         System.out.println("No matching books found.");
     }
 }
}

//Main Class
public class DigitalLibrary {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     Library library = new Library();
     int choice;

     System.out.println("Welcome to the Digital Library Management System");
     do {
         System.out.println("\nMain Menu:");
         System.out.println("1. Admin Module");
         System.out.println("2. User Module");
         System.out.println("3. Exit");
         System.out.print("Enter your choice: ");
         choice = scanner.nextInt();

         switch (choice) {
             case 1 -> {
                 System.out.println("\nAdmin Module:");
                 System.out.println("1. Add Book");
                 System.out.println("2. Remove Book");
                 System.out.println("3. View Books");
                 System.out.println("4. Back to Main Menu");
                 System.out.print("Enter your choice: ");
                 int adminChoice = scanner.nextInt();
                 switch (adminChoice) {
                     case 1 -> library.addBook();
                     case 2 -> library.removeBook();
                     case 3 -> library.viewBooks();
                     case 4 -> System.out.println("Returning to Main Menu...");
                     default -> System.out.println("Invalid choice!");
                 }
             }
             case 2 -> {
                 System.out.println("\nUser Module:");
                 System.out.println("1. View Books");
                 System.out.println("2. Issue Book");
                 System.out.println("3. Return Book");
                 System.out.println("4. Search Book");
                 System.out.println("5. Back to Main Menu");
                 System.out.print("Enter your choice: ");
                 int userChoice = scanner.nextInt();
                 switch (userChoice) {
                     case 1 -> library.viewBooks();
                     case 2 -> library.issueBook();
                     case 3 -> library.returnBook();
                     case 4 -> library.searchBook();
                     case 5 -> System.out.println("Returning to Main Menu...");
                     default -> System.out.println("Invalid choice!");
                 }
             }
             case 3 -> System.out.println("Exiting the system. Goodbye!");
             default -> System.out.println("Invalid choice!");
         }
     } while (choice != 3);

     scanner.close();
 }
}

