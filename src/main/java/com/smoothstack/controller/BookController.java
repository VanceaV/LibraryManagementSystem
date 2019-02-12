package com.smoothstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.dao.BookDao;
import com.smoothstack.entity.Book;
import com.smoothstack.util.DbUtil;

import java.sql.Connection;
import java.util.List;

@RestController
public class BookController {

	@Autowired(required = true)
	private BookDao bookDao;
	Connection conn = DbUtil.getConnection();

	@RequestMapping(path = "/lms/book/{bookId}", method = RequestMethod.GET)
	public Book getBookById(@PathVariable(name = "bookId") int id) {
		return bookDao.getById(conn, id);
	}

	@RequestMapping(path = "/lms/books", method = RequestMethod.GET)
	public List<Book> getAllBooks() {
		return bookDao.getAll(conn);
	}

	@RequestMapping(path = "/lms/book", method = RequestMethod.POST)
	public void addBook(@RequestBody Book book) {
		bookDao.add(conn, book);
	}

	@RequestMapping(path = "/lms/book", method = RequestMethod.PUT)
	public void updateBook(@RequestBody Book book) {
		bookDao.update(conn, book);
	}

	@RequestMapping(path = "/lms/book", method = RequestMethod.DELETE)
	public void deleteBook(@RequestBody int bookId) {
		bookDao.delete(conn, bookId);
	}

	@RequestMapping(path = "/lms/booksWithNumberOfCopies/libraryBranch{branchId}", method = RequestMethod.GET)
	public List<Book> getAllBooksWithNumberOfCopies(@PathVariable(name = "branchId") int id) {
		return bookDao.getAllBooksWithNumberOfCopies(conn, id);
	}

}
