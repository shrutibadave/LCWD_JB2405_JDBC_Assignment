package com.JDBC.JB2405;

import com.JDBC.JB2405.Entity.Book;
import com.JDBC.JB2405.Entity.IssueBook;
import com.JDBC.JB2405.Entity.User;
import com.JDBC.JB2405.Service.BookDao;
import com.JDBC.JB2405.Service.UserDao;
import com.JDBC.JB2405.Service.issueBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Jb2405Application implements CommandLineRunner {
	@Autowired
	UserDao userDao;

	@Autowired
	BookDao bookDao;
	@Autowired
	issueBookDao issueBookDao;
	public static void main(String[] args) {
		SpringApplication.run(Jb2405Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while(true)
		{
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			try {

				System.out.println("***Welcome to Library Management System***");
				System.out.println("PRESS 1 to Add a Book");
				System.out.println("PRESS 2 to View All Books");
				System.out.println("PRESS 3 to Search a Book");
				System.out.println("PRESS 4 to Update a Book");
				System.out.println("PRESS 5 to Delete a Book");
				System.out.println("PRESS 6 to View book details");
				System.out.println("PRESS 7 to Exit");

				System.out.println("Press 8 to issue book to user");
				System.out.println("Press 9 to return book from user");
				System.out.println("Press 10 to view all issued books");


				System.out.println("Enter your choice: ");
				int choice = Integer.parseInt(bufferedReader.readLine());
				if (choice == 1) {
					/// book add-- add logic
					System.out.println("Enter the book name: ");
					String name = bufferedReader.readLine();

					System.out.println("Enter the book description: ");
					String description = bufferedReader.readLine();

					System.out.println("Enter the book available:[T/F] ");
					String available = bufferedReader.readLine();

					System.out.println("Enter the price for day: ");
					int price = Integer.parseInt(bufferedReader.readLine());

					if(available.equalsIgnoreCase("T"))
					{
						available = "1";
					}
					else
					{
						available = "0";
					}



					Book book = new Book();
					book.setName(name);
					book.setDescription(description);
					book.setPrice(price);
					book.setAvailable(Integer.parseInt(available));

					bookDao.addBook(book);
					System.out.println("Book added successfully!");
					System.out.println("________________________________");
					System.out.println();


				} else if (choice == 2) {
					///  book view-- view logic

					System.out.println("All books in the library:");
					System.out.println("________________________________");
					System.out.println();
					System.out.println("ID | Title");
					List<Book> all = bookDao.findAllBook();
					all.forEach(book -> {
						System.out.println(book.getId() + " | " + book.getName());
					});

					System.out.println("________________________________");
					System.out.println();


				} else if (choice == 3) {
					/// book search-- search logic

					System.out.println("Enter the book title to search: ");
					String titleKeyword = bufferedReader.readLine();
					List<Book> search = bookDao.searchBook(titleKeyword);
					System.out.println("ID | Title");
					search.forEach(book -> {
						System.out.println(book.getId() + " | " + book.getName());
					});

					System.out.println("________________________________");
					System.out.println();


				} else if (choice == 4) {
					/// book update-- update logic
					System.out.println("Enter the book id to update: ");
					int bookId = Integer.parseInt(bufferedReader.readLine());
					Book book = bookDao.findBookById(bookId);
					if (book == null) {
						System.out.println("Book not found with ID: " + bookId);
						continue;
					}
					System.out.println("Enter the new book name: ");
					String name = bufferedReader.readLine();
					System.out.println("Enter the new book description: ");
					String description = bufferedReader.readLine();
					System.out.println("Enter the new book available:[T/F] ");
					String available = bufferedReader.readLine();
					System.out.println("Enter the new price for day: ");
					int price = Integer.parseInt(bufferedReader.readLine());
					available = available.equalsIgnoreCase("T") ? "1" : "0";
					book.setName(name);
					book.setDescription(description);
					book.setPrice(price);
					book.setAvailable(Integer.parseInt(available));
					bookDao.updateBook(book);

				} else if (choice == 5) {
					///  delete logic

					System.out.println("Enter the book id: ");
					int bookId = Integer.parseInt(bufferedReader.readLine());

					bookDao.deleteBook(bookId);
					System.out.println("Book deleted successfully!");
					System.out.println("________________________________");
					System.out.println();


				} else if (choice == 6) {
					///  view single book detail
					System.out.println("Enter the book id: ");
					int bookId = Integer.parseInt(bufferedReader.readLine());

					Book book = bookDao.findBookById(bookId);

					System.out.println("_________________________________");
					System.out.println("Book ID: " + book.getId());
					System.out.println("Book Name: " + book.getName());
					System.out.println("Book Description: " + book.getDescription());
					System.out.println("Book Available: " + (book.isAvailable()==1 ? "Yes" : "No"));
					System.out.println("Book Price per Day: " + book.getPrice());

					System.out.println("_________________________________");
					System.out.println();


				} else if (choice == 7) {
					System.out.println("Exiting the application...");
					break;
				} else if (choice == 8) {

					System.out.println("Enter the book id: ");
					int bookId = Integer.parseInt(bufferedReader.readLine());

					Book book = bookDao.findBookById(bookId);

					if (book.isAvailable()==0) {
						System.out.println("book is not aviable for issue");
						continue;
					}

					System.out.println("Enter the user id: ");
					int userid = Integer.parseInt(bufferedReader.readLine());

					User user = userDao.getUser(userid);

					LocalDate issueDate = LocalDate.now();

					System.out.println("Enter days to issue: ");
					int days = Integer.parseInt(bufferedReader.readLine());

					int totalPrice = days * book.getPrice();

					LocalDate submitDate = issueDate.plusDays(days);

					// lets create object of issuebook

					IssueBook issueBook = new IssueBook();
					issueBook.setBookId(book.getId());
					issueBook.setUserId(user.getId());
					issueBook.setIssuedDate(issueDate);
					issueBook.setIssuedForDay(days);
					issueBook.setPenalty(0);
					issueBook.setTotalAmount(totalPrice);
					issueBook.setSubmitDate(submitDate);
					issueBook.setReturned(false);
					// save issue book
					int issue_bookId = issueBookDao.insertBook(issueBook);
					System.out.println("Book issued successfully!");
					System.out.println("Issue Book ID: " + issue_bookId);

					book.setAvailable(0);
					bookDao.updateBook(book);


				} else if(choice == 9)
				{
					System.out.println("Enter the issue id: ");;
					int issueBookId = Integer.parseInt(bufferedReader.readLine());
					IssueBook b1=issueBookDao.getbookById(issueBookId);
					if(b1==null)
					{
						System.out.println("Issue book not found with ID: " + issueBookId);
						continue;
					}

					b1.setReturned(true);
					issueBookDao.updateIssueBook(b1);
					Book b = new Book();
					b=bookDao.findBookById(b1.getBookId());
					b.setAvailable(1);
					bookDao.updateBook(b);
					System.out.println("Book returned successfully!");
				}
				else if(choice == 10)
				{
					List<IssueBook> issueBookList=issueBookDao.getAllIssueBook();
					issueBookList.forEach(issueBook -> {
						System.out.println("ID: " + issueBook.getId());
						System.out.println("Book ID: " + issueBook.getBookId());
						System.out.println("User ID: " + issueBook.getUserId());
						System.out.println("Issued Date: " + issueBook.getIssuedDate());
						System.out.println("Issued For Days: " + issueBook.getIssuedForDay());
						System.out.println("Penalty: " + issueBook.getPenalty());
						System.out.println("Total Amount: " + issueBook.getTotalAmount());
						System.out.println("Submit Date: " + issueBook.getSubmitDate());
						System.out.println("Is Returned: " + (issueBook.isReturned() ? "Yes" : "No"));
						System.out.println("-------------------------------");
					});
				}
				else {
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}
		}


	}
}
