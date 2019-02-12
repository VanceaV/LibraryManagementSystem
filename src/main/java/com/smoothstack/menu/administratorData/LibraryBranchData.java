package com.smoothstack.menu.administratorData;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.entity.LibraryBranch;

public class LibraryBranchData {

	public static void libraryBranch(Connection conn, Scanner userInput, LibraryBranchDao libraryBranchDao) {

		System.out.println("--------------------------------------------------------------------------");
		while (true) {
			System.out.println("1) Add LibraryBranch");
			System.out.println("2) Update LibraryBranch");
			System.out.println("3) Delete LibraryBranch");
			System.out.println("4) Quit to Previous");
			System.out.println("--------------------------------------------------------------------------");
			String inputString = userInput.nextLine();

			if (inputString.equals("1")) {
				add(conn, userInput, libraryBranchDao);
			} else if (inputString.equals("2")) {
				update(conn, userInput, libraryBranchDao);
			} else if (inputString.equals("3")) {
				delete(conn, userInput, libraryBranchDao);
			} else if (inputString.equals("4")) {
				System.out.println("--------------------------------------------------------------------------");
				return;
			}
		}
	}

	private static void add(Connection conn, Scanner userInput, LibraryBranchDao libraryBranchDao) {

		System.out.println("--------------------------------------------------------------------------");

		List<LibraryBranch> LibraryBranchs = libraryBranchDao.getAll(conn);
		LibraryBranchs.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		LibraryBranch libraryBranch = new LibraryBranch();
		System.out.println("Enter LibraryBranch Name");
		System.out.println("--------------------------------------------------------------------------");
		String libraryBranchName = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		libraryBranch.setBranchName(libraryBranchName);
		System.out.println("Enter LibraryBranch Address");
		System.out.println("--------------------------------------------------------------------------");
		String libraryBranchAddress = userInput.nextLine();
		libraryBranch.setBranchAddress(libraryBranchAddress);
		libraryBranchDao.add(conn, libraryBranch);
		LibraryBranchs = libraryBranchDao.getAll(conn);
		System.out.println("--------------------------------------------------------------------------");
		LibraryBranchs.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
	}

	private static void update(Connection conn, Scanner userInput, LibraryBranchDao libraryBranchDao) {

		System.out.println("Choose an LibraryBranch to update");
		System.out.println("--------------------------------------------------------------------------");
		List<LibraryBranch> libraryBranchs = libraryBranchDao.getAll(conn);
		libraryBranchs.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter LibraryBranch Id");
		System.out.println("--------------------------------------------------------------------------");
		int LibraryBranchId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		LibraryBranch libraryBranch = libraryBranchDao.getById(conn, LibraryBranchId);
		System.out.println("Enter new LibraryBranchName");
		System.out.println("--------------------------------------------------------------------------");
		String libraryBranchName = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		libraryBranch.setBranchName(libraryBranchName);
		System.out.println("Enter new LibraryBranchAddress");
		System.out.println("--------------------------------------------------------------------------");
		String libraryBranchAddress = userInput.nextLine();
		libraryBranch.setBranchAddress(libraryBranchAddress);
		libraryBranchDao.update(conn, libraryBranch);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("LibraryBranch " + libraryBranch + " was successfully updated");
		System.out.println("--------------------------------------------------------------------------");
		libraryBranchs = libraryBranchDao.getAll(conn);
		libraryBranchs.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");

	}

	private static void delete(Connection conn, Scanner userInput, LibraryBranchDao libraryBranchDao) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Choose an LibraryBranch to delete  by Id");
		System.out.println("--------------------------------------------------------------------------");
		List<LibraryBranch> libraryBranchs = libraryBranchDao.getAll(conn);
		libraryBranchs.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		int libraryBranchId = Integer.parseInt(userInput.nextLine());
		libraryBranchDao.delete(conn, libraryBranchId);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("LibraryBranch with id" + libraryBranchId + " was successfully deleted");
		System.out.println("--------------------------------------------------------------------------");
		libraryBranchs = libraryBranchDao.getAll(conn);
		libraryBranchs.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
	}

}
