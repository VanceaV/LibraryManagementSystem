package com.smoothstack.repository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.Idao.IBookLoansDao;
import com.smoothstack.entity.BookLoans;

@Service
@Transactional
public class BookLoansRepository {

	@Autowired
	private IBookLoansDao bookLoansDao;

	public BookLoans getByBookIdAndBranchIdandCardNo(long bookId, long branchId, long cardNo) {
		return bookLoansDao.getByBookIdAndBranchIdAndCardNo(bookId, branchId, cardNo);
	}

	public ResponseEntity<BookLoans> overRideDueDate(long bookId, long branchId, long cardNo, String newDate) {

		try {
			LocalDate dt = LocalDate.parse(newDate);
			bookLoansDao.updateDueDate(bookId, branchId, cardNo, dt);

		} catch (DateTimeException e) {
			return new ResponseEntity<BookLoans>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<BookLoans>(HttpStatus.OK);
	}

	public List<BookLoans> getAll() {
		return bookLoansDao.findAll();
	}

	public void create(BookLoans bookLoans) {
		bookLoansDao.save(bookLoans);
	}

	public void update(BookLoans bookLoans) {
		bookLoansDao.save(bookLoans);
	}

	public void delete(long bookId, long branchId, long cardNo) {
		bookLoansDao.delete(bookId, branchId, cardNo);
	}

	public ResponseEntity<BookLoans> create(long bookId, long branchId, long cardNo, String out, String due) {

		try {
			LocalDate dateOut = LocalDate.parse(out);
			LocalDate dateDue = LocalDate.parse(due);
			bookLoansDao.create(bookId, branchId, cardNo, dateOut, dateDue);
		} catch (DateTimeException e) {
			return new ResponseEntity<BookLoans>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<BookLoans>(HttpStatus.OK);
	}

}
