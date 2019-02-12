package com.smoothstack.menu.administratorData;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.dao.AuthorDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.PublisherDao;
import com.smoothstack.entity.Author;
import com.smoothstack.entity.Book;
import com.smoothstack.entity.Publisher;



public class BookData {

	public static void book(Connection conn, Scanner userInput, BookDao bookDao, AuthorDao authorDao,
			PublisherDao publisherDao) {

		System.out.println("--------------------------------------------------------------------------");
		while (true) {
			System.out.println("1) Add Book");
			System.out.println("2) Update Book");
			System.out.println("3) Delete Book");
			System.out.println("4) Quit to Previous");
			System.out.println("--------------------------------------------------------------------------");
			String inputString = userInput.nextLine();
			
			

			if (inputString.equals("1")) {
				add(conn, userInput, bookDao, authorDao, publisherDao);
			} else if (inputString.equals("2")) {
				update(conn, userInput, bookDao, authorDao, publisherDao);
			} else if (inputString.equals("3")) {
				delete(conn, userInput, bookDao);
			} else if (inputString.equals("4")) {
				return;
			}
		}
	}

	private static void add(Connection conn, Scanner userInput, BookDao bookDao, AuthorDao authorDao,
			PublisherDao publisherDao) {

		List<Book> books = bookDao.getAll(conn);
		List<Author> authors = authorDao.getAll(conn);
		List<Publisher> publishers = publisherDao.getAll(conn);
		System.out.println("--------------------------------------------------------------------------");
		books.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		authors.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		publishers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		Book book = new Book();
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter book title");
		String title = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter authId");
		int authorId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter pubID");
		int publisherId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		book.setTitle(title);
		book.setAuthor(authorDao.getById(conn, authorId));
		book.setPublisher(publisherDao.getById(conn, publisherId));
		bookDao.add(conn, book);
		System.out.println("Book " + book + " was Book was successfully added to DB");
		books = bookDao.getAll(conn);
		System.out.println("--------------------------------------------------------------------------");
		books.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");

	}

	private static void update(Connection conn, Scanner userInput, BookDao bookDao, AuthorDao authorDao,
			PublisherDao publisherDao) {

		System.out.println("Choose a book to update");
		System.out.println("--------------------------------------------------------------------------");
		List<Book> books = bookDao.getAll(conn);
		books.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter book Id");
		int bookId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		Book book = bookDao.getById(conn, bookId);

		System.out.println("Enter book new title");
		String title = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter new authId");
		int authorId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter new pubID");
		int publisherId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		book.setTitle(title);
		book.setAuthor(authorDao.getById(conn, authorId));
		book.setPublisher(publisherDao.getById(conn, publisherId));

		bookDao.update(conn, book);
		System.out.println("Book was successfully updated");
		System.out.println("--------------------------------------------------------------------------");
		books = bookDao.getAll(conn);
		books.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");

	}

	private static void delete(Connection conn, Scanner userInput, BookDao bookDao) {

		System.out.println("Choose a book to delete  by Id");
		System.out.println("--------------------------------------------------------------------------");
		List<Book> books = bookDao.getAll(conn);
		books.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		int bookId = Integer.parseInt(userInput.nextLine());
		bookDao.delete(conn, bookId);
		System.out.println("Book[" + bookId + "]was successfully deleted");
		System.out.println("--------------------------------------------------------------------------");
		books = bookDao.getAll(conn);
		books.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
	}
}
