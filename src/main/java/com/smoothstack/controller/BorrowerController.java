package com.smoothstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.dao.BorrowerDao;
import com.smoothstack.entity.Borrower;
import com.smoothstack.util.DbUtil;

import java.sql.Connection;
import java.util.List;

@RestController
public class BorrowerController {

	@Autowired(required = true)
	private BorrowerDao borrowerDao;
	Connection conn = DbUtil.getConnection();

	@RequestMapping(path = "/lms/borrower/{borrowerId}", method = RequestMethod.GET)
	public Borrower getBorrowerById(@PathVariable(name = "borrowerId") int id) {
		return borrowerDao.getById(conn, id);
	}

	@RequestMapping(path = "/lms/borrowers", method = RequestMethod.GET)
	public List<Borrower> getAllBorrowers() {
		return borrowerDao.getAll(conn);
	}

	@RequestMapping(path = "/lms/borrower", method = RequestMethod.POST)
	public void addBorrower(@RequestBody Borrower borrower) {
		borrowerDao.add(conn, borrower);
	}

	@RequestMapping(path = "/lms/borrower", method = RequestMethod.PUT)
	public void updateBorrower(@RequestBody Borrower borrower) {
		borrowerDao.update(conn, borrower);
	}

	@RequestMapping(path = "/lms/borrower", method = RequestMethod.DELETE)
	public void deleteBorrower(@RequestBody int borrowerId) {
		borrowerDao.delete(conn, borrowerId);
	}
}
