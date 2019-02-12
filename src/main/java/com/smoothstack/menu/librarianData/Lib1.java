package com.smoothstack.menu.librarianData;

import java.sql.Connection;
import java.util.Scanner;

import com.smoothstack.dao.BookCopiesDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.LibraryBranchDao;




public class Lib1 {

	public static void lib1(Connection conn, Scanner userInput, BookCopiesDao bookCopiesDao, BookDao bookDao,
			LibraryBranchDao libraryBranchDao) {

		while (true) {

			System.out.println("--------------------------------------------------------------------------");
			System.out.println(" 1) Enter Branch you manage");
			System.out.println(" 2) Quite to previous");
			System.out.println("--------------------------------------------------------------------------");
			String input = userInput.nextLine();

			if (input.equals("1")) {
				Lib2.lib2(conn, userInput, bookCopiesDao, bookDao, libraryBranchDao);
			} else if (input.equals("2")) {
				return;
			}
		}
	}
}
