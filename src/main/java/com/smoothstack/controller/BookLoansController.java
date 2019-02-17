package com.smoothstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.entity.BookLoans;
import com.smoothstack.repository.BookCopiesRepository;
//import com.smoothstack.repository.BookCopiesRepository;
import com.smoothstack.repository.BookLoansRepository;

@RestController
@RequestMapping("/lms")
public class BookLoansController {

	@Autowired
	private BookLoansRepository bookLoansRepository;

	@Autowired
	private BookCopiesRepository bookCopiesRepository;

	@PutMapping("/administrator/overRideDueDate")
	public ResponseEntity<BookLoans> overRideDueDate(@RequestParam("bookId") long bookId,
			@RequestParam("branchId") long branchId, @RequestParam("cardNo") long cardNo,
			@RequestParam String newDate) {
		return bookLoansRepository.overRideDueDate(bookId, branchId, cardNo, newDate);
	}

	@PostMapping("/borrower/checkOutABook")
	public ResponseEntity<BookLoans> checkOutABook(@RequestParam("bookId") long bookId,
			@RequestParam("branchId") long branchId, @RequestParam("cardNo") long cardNo,
			@RequestParam("dateOut") String dateOut, @RequestParam("dateDue") String dateDue) {
		bookLoansRepository.create(bookId, branchId, cardNo, dateOut, dateDue);
		bookCopiesRepository.decrementNumberOfCopiesbyOne(bookId, branchId);
		return new ResponseEntity<BookLoans>(HttpStatus.CREATED);
	}

	@DeleteMapping("/borrower/returnABook")
	public ResponseEntity<BookLoans> returnABook(@RequestParam("bookId") long bookId,
			@RequestParam("branchId") long branchId, @RequestParam("cardNo") long cardNo) {
		bookLoansRepository.delete(bookId, branchId, cardNo);
		bookCopiesRepository.incrementNumberOfCopiesbyOne(bookId, branchId);
		return new ResponseEntity<BookLoans>(HttpStatus.ACCEPTED);

	}

}

