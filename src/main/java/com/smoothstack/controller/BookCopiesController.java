package com.smoothstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.dao.BookCopiesDao;
import com.smoothstack.entity.BookCopies;
import com.smoothstack.util.DbUtil;

import java.sql.Connection;
import java.util.List;

@RestController
public class BookCopiesController {

	@Autowired(required = true)
	private BookCopiesDao bookCopiesDao;
	Connection conn = DbUtil.getConnection();

	@RequestMapping(path = "/lms/bookCopiess/libraryBranch/{libraryBranchId}/book/{bookId}", method = RequestMethod.GET)
	public BookCopies getByBranchIdandBookId(@PathVariable(name = "libraryBranchId") int branchId,
			@PathVariable(name = "bookId") int bookId) {
		return bookCopiesDao.getByBranchIdandBookId(conn, branchId, bookId);
	}

	@RequestMapping(path = "/lms/bookCopiess", method = RequestMethod.GET)
	public List<BookCopies> getAllBookCopiess() {
		return bookCopiesDao.getAll(conn);
	}

	@RequestMapping(path = "/lms/bookCopiess/libraryBranch/{libraryBranchId}", method = RequestMethod.GET)
	public List<BookCopies> getAllbyBranch(@PathVariable(name = "libraryBranchId") int branchId) {
		return bookCopiesDao.getAllbyBranch(conn, branchId);
	}

	@RequestMapping(path = "/lms/bookCopiess/numberOfCopies/libraryBranch/{libraryBranchId}/book/{bookId}", method = RequestMethod.GET)
	public int getNumbersOfCopiesByID(@PathVariable(name = "libraryBranchId") int branchId,
			@PathVariable(name = "bookId") int bookId) {
		return bookCopiesDao.getNumbersOfCopiesByID(conn, branchId, bookId);
	}

	@RequestMapping(path = "/lms/bookCopiess/libraryBranch/{libraryBranchId}/book/{bookId}/numberOfCopies{numberOfCopies}", method = RequestMethod.POST)
	public void updateNumbersOfCopies(@PathVariable(name = "libraryBranchId") int branchId,
			@PathVariable(name = "bookId") int bookId, @PathVariable(name = "numberOfCopies") int numberOfCopies) {
		bookCopiesDao.updateNumbersOfCopies(conn, branchId, bookId, numberOfCopies);
	}

	@RequestMapping(path = "/lms/bookCopies", method = RequestMethod.POST)
	public void addBookCopies(@RequestBody BookCopies bookCopies) {
		bookCopiesDao.add(conn, bookCopies);
	}

	@RequestMapping(path = "/lms/bookCopies", method = RequestMethod.PUT)
	public void updateBookCopies(@RequestBody BookCopies bookCopies) {
		bookCopiesDao.update(conn, bookCopies);
	}

}
