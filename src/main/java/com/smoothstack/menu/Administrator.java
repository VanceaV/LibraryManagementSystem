package com.smoothstack.menu;

import java.sql.Connection;
import java.util.Scanner;

import com.smoothstack.dao.AuthorDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.BookLoansDao;
import com.smoothstack.dao.BorrowerDao;
import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.dao.PublisherDao;
import com.smoothstack.menu.administratorData.AuthorData;
import com.smoothstack.menu.administratorData.BookData;
import com.smoothstack.menu.administratorData.BorrowerData;
import com.smoothstack.menu.administratorData.LibraryBranchData;
import com.smoothstack.menu.administratorData.OverRideDueDateData;
import com.smoothstack.menu.administratorData.PublisherData;

public class Administrator {

	public static void mainMenu(Connection conn, Scanner userInput, BookDao bookDao, AuthorDao authorDao,
			LibraryBranchDao libraryBranchDao, PublisherDao publisherDao, BorrowerDao borrowerDao,
			BookLoansDao bookLoansDao) {

		System.out.println("--------------------------------------------------------------------------");
		while (true) {

			System.out.println("1) Add/Update/Delete Book");
			System.out.println("2) Add/Update/Delete Author");
			System.out.println("3) Add/Update/Delete Publishers");
			System.out.println("4) Add/Update/Delete Library Branches");
			System.out.println("5) Add/Update/Delete Borrowers");
			System.out.println("6) Over-ride Due Date for a Book Loan");
			System.out.println("7) Quit to main Menu");
			System.out.println("--------------------------------------------------------------------------");

			String input = userInput.nextLine();

			if (input.equals("1")) {
				BookData.book(conn, userInput, bookDao, authorDao, publisherDao);
			} else if (input.equals("2")) {
				AuthorData.author(conn, userInput, authorDao);
			} else if (input.equals("3")) {
				PublisherData.publisher(conn, userInput, publisherDao);
			} else if (input.equals("4")) {
				LibraryBranchData.libraryBranch(conn, userInput, libraryBranchDao);
			} else if (input.equals("5")) {
				BorrowerData.borrower(conn, userInput, borrowerDao);
			} else if (input.equals("6")) {
				OverRideDueDateData.bookLoan(conn, userInput, bookLoansDao);
			} else if (input.equals("7")) {
				return;
			}
		}
	}
}
