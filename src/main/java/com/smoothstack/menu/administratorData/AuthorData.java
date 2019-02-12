package com.smoothstack.menu.administratorData;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.dao.AuthorDao;
import com.smoothstack.entity.Author;





public class AuthorData {

	public static void author(Connection conn, Scanner userInput, AuthorDao authorDao) {

		System.out.println("--------------------------------------------------------------------------");
		while (true) {
			System.out.println("1) Add Author");
			System.out.println("2) Update Author");
			System.out.println("3) Delete Author");
			System.out.println("4) Quit to Previous");
			System.out.println("--------------------------------------------------------------------------");
			String inputString = userInput.nextLine();

			if (inputString.equals("1")) {
				add(conn, userInput, authorDao);
			} else if (inputString.equals("2")) {
				update(conn, userInput, authorDao);
			} else if (inputString.equals("3")) {
				delete(conn, userInput, authorDao);
			} else if (inputString.equals("4")) {
				System.out.println("--------------------------------------------------------------------------");
				return;
			}
		}
	}

	private static void add(Connection conn, Scanner userInput, AuthorDao authorDao) {

		System.out.println("--------------------------------------------------------------------------");

		List<Author> authors = authorDao.getAll(conn);
		authors.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		Author author = new Author();
		System.out.println("Enter Author Name");
		String authorName = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		author.setAuthorName(authorName);
		authorDao.add(conn, author);
		authors = authorDao.getAll(conn);
		authors.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
	}

	private static void update(Connection conn, Scanner userInput, AuthorDao authorDao) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Choose an Author to update");
		System.out.println("--------------------------------------------------------------------------");
		List<Author> authors = authorDao.getAll(conn);
		authors.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter Author Id");
		System.out.println("--------------------------------------------------------------------------");
		int authorId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		Author author = authorDao.getById(conn, authorId);
		System.out.println("Enter new authorName");
		String authorName = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		author.setAuthorName(authorName);
		authorDao.update(conn, author);
		System.out.println("Author " + author + " was successfully updated");
		System.out.println("--------------------------------------------------------------------------");
		authors = authorDao.getAll(conn);
		authors.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");

	}

	private static void delete(Connection conn, Scanner userInput, AuthorDao authorDao) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Choose an Author to delete  by Id");
		System.out.println("--------------------------------------------------------------------------");
		List<Author> authors = authorDao.getAll(conn);
		authors.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		int authorId = Integer.parseInt(userInput.nextLine());
		authorDao.delete(conn, authorId);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Author with " + authorId + " was successfully deleted");
		System.out.println("--------------------------------------------------------------------------");
		authors = authorDao.getAll(conn);
		authors.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
	}
}
