package com.smoothstack.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.smoothstack.dao.AuthorDao;
import com.smoothstack.dao.BookCopiesDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.BookLoansDao;
import com.smoothstack.dao.BorrowerDao;
import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.dao.PublisherDao;
import com.smoothstack.repositories.AuthorRepository;
import com.smoothstack.repositories.BookCopiesRepository;
import com.smoothstack.repositories.BookLoansRepository;
import com.smoothstack.repositories.BookRepository;
import com.smoothstack.repositories.BorrowerRepository;
import com.smoothstack.repositories.LibraryBranchRepository;
import com.smoothstack.repositories.PublisherRepository;
import com.smoothstack.util.DbUtil;

public class Start {

	private BookDao bookDao = new BookRepository();

	private AuthorDao authorDao = new AuthorRepository();

	private BookCopiesDao bookCopiesDao = new BookCopiesRepository();

	private LibraryBranchDao libraryBranchDao = new LibraryBranchRepository();

	private PublisherDao publisherDao = new PublisherRepository();

	private BorrowerDao borrowerDao = new BorrowerRepository();

	private BookLoansDao bookLoansDao = new BookLoansRepository();

	public void run() {

		try (Connection conn = DbUtil.getConnection(); Scanner userInput = new Scanner(System.in)) {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

			while (true) {

				String inputString;

				System.out.println("--------------------------------------------------------------------------");
				System.out.println("Welcome to GCIT Library Management System. Which category of user are you");
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("1 Librarian");
				System.out.println("2 Administrator");
				System.out.println("3 Borrower");
				System.out.println("--------------------------------------------------------------------------");

				inputString = userInput.nextLine();

				if (inputString.equals("1")) {
					Librarian.mainMenu(conn, userInput, bookCopiesDao, bookDao, libraryBranchDao);
				} else if (inputString.equals("2")) {
					Administrator.mainMenu(conn, userInput, bookDao, authorDao, libraryBranchDao, publisherDao,
							borrowerDao, bookLoansDao);
				} else if (inputString.equals("3")) {
					Borrower_.mainMenu(conn, userInput, borrowerDao, libraryBranchDao, bookDao, bookLoansDao,
							bookCopiesDao);
				} else {
					System.out.println("Wrong number, Try one more time");
				}
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

}
