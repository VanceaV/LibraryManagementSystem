package com.smoothstack.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.Idao.IUserDao;
import com.smoothstack.entity.User;

@Service
@Transactional
public class UserRepository {

	@Autowired
	private IUserDao iUserDao;

	public void create(User user) {
		iUserDao.save(user);
	}

	public User findByUsername(String username) {
		return iUserDao.findByUsername(username);
	}

}
