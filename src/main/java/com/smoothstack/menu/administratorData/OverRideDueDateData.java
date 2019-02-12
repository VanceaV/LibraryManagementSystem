package com.smoothstack.menu.administratorData;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.dao.BookLoansDao;
import com.smoothstack.entity.BookLoans;


public class OverRideDueDateData {

	public static void bookLoan(Connection conn, Scanner userInput, BookLoansDao bookLoansDao) {

		System.out.println("--------------------------------------------------------------------------");
		while (true) {
			System.out.println("1) Over-ride Due Date for a Book Loan");
			System.out.println("2) Quit to Previous");
			System.out.println("--------------------------------------------------------------------------");
			String inputString = userInput.nextLine();

			if (inputString.equals("1")) {
				overRide(conn, userInput, bookLoansDao);
			} else if (inputString.equals("2")) {
				System.out.println("--------------------------------------------------------------------------");
				return;
			}
		}
	}

	private static void overRide(Connection conn, Scanner userInput, BookLoansDao bookLoansDao) {

		System.out.println("--------------------------------------------------------------------------");

		List<BookLoans> bookLoans = bookLoansDao.getAll(conn);
		bookLoans.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter BookId");
		int bookId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter BranchId");
		int branchId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter CardNo");
		int cardNo = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		BookLoans bookLoan = bookLoansDao.getById(conn, bookId, branchId, cardNo);
		System.out.println("Enter new Date to OverRide \"year-month-day\"");
		System.out.println("--------------------------------------------------------------------------");
		String newDate = userInput.nextLine();
		LocalDate dt = LocalDate.parse(newDate);
		bookLoan.setDueDate(dt);
		bookLoansDao.update(conn, bookLoan);
		System.out.println("--------------------------------------------------------------------------");
		bookLoans = bookLoansDao.getAll(conn);
		bookLoans.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
	}

}
