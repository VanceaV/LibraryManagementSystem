package com.smoothstack.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.Idao.IBorrowerDao;
import com.smoothstack.entity.Borrower;

@Service
@Transactional
public class BorrowerRepository {

	@Autowired
	private IBorrowerDao borrowerDao;

	public Borrower getById(long id) {
		return borrowerDao.getOne(id);
	}

	public List<Borrower> getAll() {
		return borrowerDao.findAll();
	}

	public void create(Borrower borrower) {
		borrowerDao.save(borrower);
	}

	public void update(Borrower borrower) {
		borrowerDao.save(borrower);
	}

	public void delete(long id) {
		borrowerDao.deleteById(id);
	}

}
