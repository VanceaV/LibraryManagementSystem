package com.smoothstack.menu;

import java.sql.Connection;
import java.util.Scanner;

import com.smoothstack.dao.BookCopiesDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.menu.librarianData.Lib1;

public class Librarian {

	public static void mainMenu(Connection conn, Scanner userInput, BookCopiesDao bookCopiesDao, BookDao bookDao,
			LibraryBranchDao libraryBranchDao) {
		Lib1.lib1(conn, userInput, bookCopiesDao, bookDao, libraryBranchDao);
	}
}
