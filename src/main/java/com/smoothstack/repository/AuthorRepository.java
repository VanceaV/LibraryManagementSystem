package com.smoothstack.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.Idao.IAuthorDao;
import com.smoothstack.entity.Author;

@Service
@Transactional
public class AuthorRepository {

	@Autowired
	private IAuthorDao authorDao;

	public Author getById(long id) {
		return authorDao.getOne(id);
	}

	public List<Author> getAll() {
		return authorDao.findAll();
	}

	public void create(Author author) {
		authorDao.save(author);
	}

	public void update(Author author) {
		authorDao.save(author);
	}

	public void delete(long id) {
		authorDao.deleteById(id);
	}

}
