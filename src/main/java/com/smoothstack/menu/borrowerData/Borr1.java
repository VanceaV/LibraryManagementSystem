package com.smoothstack.menu.borrowerData;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.dao.BookCopiesDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.BookLoansDao;
import com.smoothstack.dao.BorrowerDao;
import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.entity.Book;
import com.smoothstack.entity.BookCopies;
import com.smoothstack.entity.BookLoans;
import com.smoothstack.entity.LibraryBranch;

public class Borr1 {

	public static boolean borr1(Connection conn, Scanner userInput, LibraryBranchDao libraryBranchDao, BookDao bookDao,
			BookLoansDao bookLoansDao, BorrowerDao borrowerDao, BookCopiesDao bookCopiesDao, int cardNumber) {

		String inputString;
		boolean b = false;

		while (true) {

			System.out.println("1) Check out a book");
			System.out.println("2) Return a book");
			System.out.println("3) Quit to previous");
			System.out.println("--------------------------------------------------------------------------");
			inputString = userInput.nextLine();
			if (inputString.equals("3")) {
				b = true;
				break;
			} else if (inputString.equals("1")) {
				checkOutABook(conn, userInput, libraryBranchDao, bookDao, bookLoansDao, borrowerDao, bookCopiesDao,
						cardNumber);

			} else if (inputString.equals("2")) {
				returnABook(conn, userInput, libraryBranchDao, bookDao, bookLoansDao, borrowerDao, bookCopiesDao,
						cardNumber);
			}
		}

		return b;
	}

	private static void checkOutABook(Connection conn, Scanner userInput, LibraryBranchDao libraryBranchDao,
			BookDao bookDao, BookLoansDao bookLoansDao, BorrowerDao borrowerDao, BookCopiesDao bookCopiesDao,
			int cardNumber) {
		List<LibraryBranch> branches = libraryBranchDao.getAll(conn);
		int last = branches.get(branches.size() - 1).getId() + 1;
		System.out.println("--------------------------------------------------------------------------");
		branches.stream().forEach(b -> System.out.println(b));
		System.out.println("" + last + ") " + "Quit to Previous");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Pick the Branch you want to check out from:");
		System.out.println("--------------------------------------------------------------------------");
		int branchId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		if (branchId == last) {
			System.out.println("--------------------------------------------------------------------------");
			return;
		}

		List<Book> books = bookDao.getAllBooksWithNumberOfCopies(conn, branchId);
		int numberofBooks = books.stream().mapToInt(b -> b.getId()).max().orElse(0) + 1;

		books.stream().forEach(b -> System.out.println(b.forPrint()));
		System.out.println("" + numberofBooks + ") " + "Quit to cancel operation");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Pick the Book you want to check out:");
		System.out.println("--------------------------------------------------------------------------");
		int bookId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		if (bookId == numberofBooks) {
			System.out.println("--------------------------------------------------------------------------");
			return;
		} else {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("Number of copies of book before check out");
			System.out.println("--------------------------------------------------------------------------");

			BookCopies bookCopies = bookCopiesDao.getByBranchIdandBookId(conn, branchId, bookId);
			System.out.println(bookCopies);
			System.out.println("--------------------------------------------------------------------------");
			bookCopies.decrementNumberOfCopies();
			LocalDate dateOut = LocalDate.now();
			LocalDate dueDate = dateOut.plus(1, ChronoUnit.WEEKS);
			BookLoans bookLoan = new BookLoans();
			bookLoan.setBook(bookDao.getById(conn, bookId));
			bookLoan.setLibraryBranch(libraryBranchDao.getById(conn, branchId));
			bookLoan.setBorrower(borrowerDao.getById(conn, cardNumber));
			bookLoan.setDateOut(dateOut);
			bookLoan.setDueDate(dueDate);
			bookLoansDao.add(conn, bookLoan);
			System.out.println("BookLoan " + bookLoan + " was added to DB");
			System.out.println("--------------------------------------------------------------------------");
			bookCopiesDao.update(conn, bookCopies);
			bookCopies = bookCopiesDao.getByBranchIdandBookId(conn, branchId, bookId);
			System.out.println("Number of copies of book after check out");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println(bookCopies);
			System.out.println("--------------------------------------------------------------------------");

			List<BookLoans> bookLoans = bookLoansDao.getAll(conn);
			bookLoans.stream().forEach(b -> System.out.println(b));
			System.out.println("--------------------------------------------------------------------------");
		}
	}

	private static void returnABook(Connection conn, Scanner userInput, LibraryBranchDao libraryBranchDao,
			BookDao bookDao, BookLoansDao bookLoansDao, BorrowerDao borrowerDao, BookCopiesDao bookCopiesDao,
			int cardNumber) {
		List<LibraryBranch> branches = libraryBranchDao.getAll(conn);
		int last = branches.get(branches.size() - 1).getId() + 1;
		System.out.println("--------------------------------------------------------------------------");
		branches.stream().forEach(b -> System.out.println(b));
		System.out.println("" + last + ") " + "Quit to Previous");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Pick the Branch you want to return a book:");
		System.out.println("--------------------------------------------------------------------------");
		int branchId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		if (branchId == last) {
			System.out.println("--------------------------------------------------------------------------");
			return;
		}

		List<Book> books = bookDao.getAll(conn);
		int numberofBooks = books.stream().mapToInt(b -> b.getId()).max().orElse(0) + 1;
		books.stream().forEach(b -> System.out.println(b.forPrint()));
		System.out.println("" + numberofBooks + ") " + "Quit to cancel operation");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Pick the book you want to return:");
		System.out.println("--------------------------------------------------------------------------");
		int bookId = Integer.parseInt(userInput.nextLine());
		if (bookId == numberofBooks) {
			System.out.println("--------------------------------------------------------------------------");
			return;
		} else {

			BookCopies bookCopie = bookCopiesDao.getByBranchIdandBookId(conn, branchId, bookId);

			if (bookCopie == null) {
				bookCopie = new BookCopies();
				bookCopie.setBook(bookDao.getById(conn, bookId));
				bookCopie.setLibraryBranch(libraryBranchDao.getById(conn, branchId));
				bookCopie.setNoOfCopies(1);
				bookCopiesDao.add(conn, bookCopie);
			} else {
				bookCopie.incrementNumberOfCopies();
				bookCopiesDao.update(conn, bookCopie);
			}

			bookLoansDao.delete(conn, bookId, branchId, cardNumber);
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("BookLoan with bookId/branchId/cardNumber" + bookId + "/ " + branchId + "/" + cardNumber
					+ "/" + "was deleted to DB");
			System.out.println("--------------------------------------------------------------------------");

			List<BookLoans> bookLoans = bookLoansDao.getAll(conn);
			bookLoans.stream().forEach(b -> System.out.println(b));
			System.out.println("--------------------------------------------------------------------------");
		}
	}
}
