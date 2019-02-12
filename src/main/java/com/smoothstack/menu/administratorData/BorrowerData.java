package com.smoothstack.menu.administratorData;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.dao.BorrowerDao;
import com.smoothstack.entity.Borrower;





public class BorrowerData {

	public static void borrower(Connection conn, Scanner userInput, BorrowerDao borrowerDao) {

		System.out.println("--------------------------------------------------------------------------");
		while (true) {
			System.out.println("1) Add Borrower");
			System.out.println("2) Update Borrower");
			System.out.println("3) Delete Borrower");
			System.out.println("4) Quit to Previous");
			System.out.println("--------------------------------------------------------------------------");
			String inputString = userInput.nextLine();

			if (inputString.equals("1")) {
				add(conn, userInput, borrowerDao);
			} else if (inputString.equals("2")) {
				update(conn, userInput, borrowerDao);
			} else if (inputString.equals("3")) {
				delete(conn, userInput, borrowerDao);
			} else if (inputString.equals("4")) {
				System.out.println("--------------------------------------------------------------------------");
				return;
			}
		}
	}

	private static void add(Connection conn, Scanner userInput, BorrowerDao borrowerDao) {

		System.out.println("--------------------------------------------------------------------------");

		List<Borrower> borrowers = borrowerDao.getAll(conn);
		borrowers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		Borrower borrower = new Borrower();
		System.out.println("Enter Borrower Name");
		System.out.println("--------------------------------------------------------------------------");
		String borrowerName = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		borrower.setName(borrowerName);
		System.out.println("Enter Borrower Address");
		System.out.println("--------------------------------------------------------------------------");
		String borrowerAddress = userInput.nextLine();
		borrower.setAddress(borrowerAddress);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter Borrower Phone");
		String borrowerPhone = userInput.nextLine();
		borrower.setPhone(borrowerPhone);
		borrowerDao.add(conn, borrower);
		borrowers = borrowerDao.getAll(conn);
		System.out.println("--------------------------------------------------------------------------");
		borrowers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
	}

	private static void update(Connection conn, Scanner userInput, BorrowerDao borrowerDao) {

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Choose an Borrower to update");
		System.out.println("--------------------------------------------------------------------------");
		List<Borrower> borrowers = borrowerDao.getAll(conn);
		borrowers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter Borrower Id");
		System.out.println("--------------------------------------------------------------------------");
		int borrowerId = Integer.parseInt(userInput.nextLine());
		Borrower borrower = borrowerDao.getById(conn, borrowerId);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter new borrowerName");
		String borrowerName = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		borrower.setName(borrowerName);
		System.out.println("Enter new borrowerAddress");
		String borrowerAdrress = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		borrower.setAddress(borrowerAdrress);
		System.out.println("Enter new borrowerPhone");
		String borrowerPhone = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		borrower.setPhone(borrowerPhone);
		borrowerDao.update(conn, borrower);
		System.out.println("Author " + borrower + " was successfully updated");
		System.out.println("--------------------------------------------------------------------------");
		borrowers = borrowerDao.getAll(conn);
		borrowers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");

	}

	private static void delete(Connection conn, Scanner userInput, BorrowerDao borrowerDao) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Choose an Borrower to delete  by Id");
		System.out.println("--------------------------------------------------------------------------");
		List<Borrower> borrowers = borrowerDao.getAll(conn);
		borrowers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		int boorrowId = Integer.parseInt(userInput.nextLine());
		borrowerDao.delete(conn, boorrowId);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Author with id" + boorrowId + " was successfully deleted");
		System.out.println("--------------------------------------------------------------------------");
		borrowers = borrowerDao.getAll(conn);
		borrowers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
	}

}
