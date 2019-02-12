package com.smoothstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.dao.AuthorDao;
import com.smoothstack.entity.Author;
import com.smoothstack.util.DbUtil;

import java.sql.Connection;
import java.util.List;

@RestController
public class AuthorController {

	@Autowired(required = true)
	private AuthorDao authorDao;
	Connection conn = DbUtil.getConnection();

	@RequestMapping(path = "/lms/author/{authorId}", method = RequestMethod.GET)
	public Author getAuthorById(@PathVariable(name = "authorId") int id) {
		return authorDao.getById(conn, id);
	}

	@RequestMapping(path = "/lms/authors", method = RequestMethod.GET)
	public List<Author> getAllAuthors() {
		return authorDao.getAll(conn);
	}

	@RequestMapping(path = "/lms/author", method = RequestMethod.POST)
	public void addAuthor(@RequestBody Author author) {
		authorDao.add(conn, author);
	}

	@RequestMapping(path = "/lms/author", method = RequestMethod.PUT)
	public void updateAuthor(@RequestBody Author author) {
		authorDao.update(conn, author);
	}

	@RequestMapping(path = "/lms/author", method = RequestMethod.DELETE)
	public void deleteAuthor(@RequestBody int authorId) {
		authorDao.delete(conn, authorId);
	}
}
