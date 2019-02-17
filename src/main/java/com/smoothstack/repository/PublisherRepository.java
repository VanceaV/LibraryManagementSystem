package com.smoothstack.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.Idao.IPublisherDao;
import com.smoothstack.entity.Publisher;

@Service
@Transactional
public class PublisherRepository {

	@Autowired
	private IPublisherDao publisherDao;

	public Publisher getById(long id) {
		return publisherDao.getOne(id);
	}

	public List<Publisher> getAll() {
		return publisherDao.findAll();
	}

	public void create(Publisher publisher) {
		publisherDao.save(publisher);
	}

	public void update(Publisher publisher) {
		publisherDao.save(publisher);
	}

	public void delete(long id) {
		publisherDao.deleteById(id);
	}

}
