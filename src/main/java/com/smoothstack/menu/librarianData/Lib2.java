package com.smoothstack.menu.librarianData;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.dao.BookCopiesDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.entity.LibraryBranch;


public class Lib2 {

	public static void lib2(Connection conn, Scanner userInput, BookCopiesDao bookCopiesDao, BookDao bookDao,
			LibraryBranchDao libraryBranchDao) {

		List<LibraryBranch> branches = libraryBranchDao.getAll(conn);

		int last = branches.get(branches.size() - 1).getId() + 1;

		while (true) {
			System.out.println("--------------------------------------------------------------------------");
			branches.stream().forEach(b -> System.out.println(b));
			System.out.println("" + last + ") " + "Quit to Previous");
			System.out.println("--------------------------------------------------------------------------");
			int branchId = Integer.parseInt(userInput.nextLine());
			if (branchId == last)
				return;
			else {
				Lib3.lib3(conn, userInput, bookCopiesDao, bookDao, libraryBranchDao, branchId, branches);
			}
		}
	}
}
