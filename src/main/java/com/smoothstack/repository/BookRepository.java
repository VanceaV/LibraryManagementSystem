package com.smoothstack.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.Idao.IBookDao;
import com.smoothstack.entity.Book;

@Service
@Transactional
public class BookRepository {

	@Autowired
	private IBookDao bookDao;

	// public List<Book> getAllBoksWithAtLeastOneCopy(int branchId) {
	// return bookDao.getAllBooksWithAtLeastOneCopie(branchId);
	// }

	public Book getById(long id) {
		return bookDao.getOne(id);
	}

	public List<Book> getAll() {
		return bookDao.findAll();
	}

	public void create(Book book) {
		bookDao.save(book);
	}

	public void update(Book book) {
		bookDao.save(book);
	}

	public void delete(long id) {
		bookDao.deleteById(id);
	}

}
