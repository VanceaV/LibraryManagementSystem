package com.smoothstack.menu.librarianData;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.dao.BookCopiesDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.entity.Book;
import com.smoothstack.entity.BookCopies;
import com.smoothstack.entity.LibraryBranch;


public class Lib3 {

	public static void lib3(Connection conn, Scanner userInput, BookCopiesDao bookCopiesDao, BookDao bookDao,
			LibraryBranchDao libraryBranchDao, int branchId, List<LibraryBranch> branches) {

		String inputString;

		while (true) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("1) Update details of library");
			System.out.println("2) Add Coppies of Book to the Branch");
			System.out.println("3) Quit to Previous");
			System.out.println("--------------------------------------------------------------------------");
			inputString = userInput.nextLine();
			if (inputString.equals("1")) {
				updateDetailsOfLibrary(conn, userInput, branchId, libraryBranchDao, branches);
			} else if (inputString.equals("2")) {
				addCopiesOfBookToTheBranch(conn, userInput, bookCopiesDao, bookDao, branchId);
			} else if (inputString.equals("3")) {
				return;
			}
		}
	}

	private static void updateDetailsOfLibrary(Connection conn, Scanner userInput, int branchId,
			LibraryBranchDao libraryBranchDao, List<LibraryBranch> branches) {

		String inputString;

		System.out.println("--------------------------------------------------------------------------");
		LibraryBranch branch = branches.stream().filter(b -> b.getId() == branchId).findFirst().orElse(null);
		System.out.println("You have chosen to update the Branch with Branch Id: " + branch.getId()
				+ " and Branch Name:" + branch.getBranchName());
		System.out.println("Enter 'quit' at any promt to cancel operation");
		System.out.println("Please enter new branch name or enter N/A for no change:");
		inputString = userInput.nextLine();
		if (inputString.equals("quit")) {
			return;
		} else if (!inputString.equals("N/A")) {
			branch.setBranchName(inputString);
		}
		System.out.println("Please enter new branch address or enter N/A for no change:");
		inputString = userInput.nextLine();
		if (inputString.equals("quit")) {
			return;
		} else if (!inputString.equals("N/A")) {
			branch.setBranchAddress(inputString);
		}

		libraryBranchDao.update(conn, branch);
		branches = libraryBranchDao.getAll(conn);
		System.out.println("--------------------------------------------------------------------------");
		branches.stream().forEach(b -> System.out.println(b));

	}

	private static void addCopiesOfBookToTheBranch(Connection conn, Scanner userInput, BookCopiesDao bookCopiesDao,
			BookDao bookDao, int branchId) {

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Pick the Book you want to add to copies of, to your branch");
		List<Book> books = bookDao.getAllBooksWithNumberOfCopies(conn, branchId);
		int numberofBooks = books.get(books.size() - 1).getId() + 1;
		books.stream().forEach(b -> System.out.println(b.forPrint()));
		System.out.println("" + numberofBooks + ") " + "Quit to cancel operation");
		int bookId = Integer.parseInt(userInput.nextLine());
		if (bookId == numberofBooks)
			return;
		else {

			System.out.println("--------------------------------------------------------------------------");
			System.out.println(
					"Existing number of copies:N is " + bookCopiesDao.getNumbersOfCopiesByID(conn, branchId, bookId));
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("Enter new Number of Copies");
			System.out.println("--------------------------------------------------------------------------");
			int numberOfcopies = Integer.parseInt(userInput.nextLine());
			bookCopiesDao.updateNumbersOfCopies(conn, branchId, bookId, numberOfcopies);
			System.out.println("--------------------------------------------------------------------------");
			List<BookCopies> bookCopies = bookCopiesDao.getAllbyBranch(conn, branchId);
			bookCopies.stream().forEach(b -> System.out.println(b));
			System.out.println("--------------------------------------------------------------------------");

			return;
		}
	}
}
