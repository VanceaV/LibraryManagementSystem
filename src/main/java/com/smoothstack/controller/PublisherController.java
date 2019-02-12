package com.smoothstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.smoothstack.dao.PublisherDao;
import com.smoothstack.entity.Publisher;
import com.smoothstack.util.DbUtil;

import java.sql.Connection;
import java.util.List;

@RestController
public class PublisherController {

	@Autowired(required = true)
	private PublisherDao publisherDao;
	Connection conn = DbUtil.getConnection();

	@RequestMapping(path = "/lms/publisher/{publisherId}", method = RequestMethod.GET)
	public Publisher getPublisherById(@PathVariable(name = "publisherId") int id) {
		return publisherDao.getById(conn, id);
	}

	@RequestMapping(path = "/lms/publishers", method = RequestMethod.GET)
	public List<Publisher> getAllPublishers() {
		return publisherDao.getAll(conn);
	}

	@RequestMapping(path = "/lms/publisher", method = RequestMethod.POST)
	public void addPublisher(@RequestBody Publisher publisher) {
		publisherDao.add(conn, publisher);
	}

	@RequestMapping(path = "/lms/publisher", method = RequestMethod.PUT)
	public void updatePublisher(@RequestBody Publisher publisher) {
		publisherDao.update(conn, publisher);
	}

}
