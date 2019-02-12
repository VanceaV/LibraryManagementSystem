package com.smoothstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.dao.BookLoansDao;
import com.smoothstack.entity.BookLoans;
import com.smoothstack.util.DbUtil;

import java.sql.Connection;
import java.util.List;

@RestController
public class BookLoansController {

	@Autowired(required = true)
	private BookLoansDao bookLoansDao;
	Connection conn = DbUtil.getConnection();

	@RequestMapping(path = "/lms/bookLoans/book/{bookId}/libraryBranch/{libraryBranchId}/borrowe{cardNo}", method = RequestMethod.GET)
	public BookLoans getByBranchIdandBookId(@PathVariable(name = "libraryBranchId") int branchId,
			@PathVariable(name = "bookId") int bookId, @PathVariable(name = "cardNo") int cardNo) {
		return bookLoansDao.getById(conn, bookId, branchId, cardNo);
	}

	@RequestMapping(path = "/lms/bookLoanss", method = RequestMethod.GET)
	public List<BookLoans> getAllBookLoanss() {
		return bookLoansDao.getAll(conn);
	}

	@RequestMapping(path = "/lms/bookLoans", method = RequestMethod.POST)
	public void addBookLoans(@RequestBody BookLoans bookLoans) {
		bookLoansDao.add(conn, bookLoans);
	}

	@RequestMapping(path = "/lms/bookLoans", method = RequestMethod.PUT)
	public void updateBookLoans(@RequestBody BookLoans bookLoans) {
		bookLoansDao.update(conn, bookLoans);
	}

}
