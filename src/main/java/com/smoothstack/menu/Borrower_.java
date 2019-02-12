package com.smoothstack.menu;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.dao.BookCopiesDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.BookLoansDao;
import com.smoothstack.dao.BorrowerDao;
import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.entity.Borrower;
import com.smoothstack.menu.borrowerData.Borr1;

public class Borrower_ {

	public static void mainMenu(Connection conn, Scanner userInput, BorrowerDao borrowerDao,
			LibraryBranchDao libraryBranchDao, BookDao bookDao, BookLoansDao bookLoansDao,
			BookCopiesDao bookCopiesDao) {

		List<Borrower> borrowers = borrowerDao.getAll(conn);

		System.out.println("--------------------------------------------------------------------------");
		borrowers.stream().forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");

		int cardNumber;
		while (true) {
			System.out.println("Enter your card number");
			System.out.println("--------------------------------------------------------------------------");
			cardNumber = Integer.parseInt(userInput.nextLine());
			if (!checkIfCardNumberExits(borrowers, cardNumber))
				System.out.println("Please re-enter the card nunmber");
			else
				break;
			System.out.println("--------------------------------------------------------------------------");
		}
		System.out.println("--------------------------------------------------------------------------");

		if (Borr1.borr1(conn, userInput, libraryBranchDao, bookDao, bookLoansDao, borrowerDao, bookCopiesDao,
				cardNumber))
			return;

	}

	static boolean checkIfCardNumberExits(List<Borrower> borrowers, int cardNumber) {
		for (Borrower borrower : borrowers) {
			if (borrower.getId() == cardNumber)
				return true;
		}
		return false;
	}

}
