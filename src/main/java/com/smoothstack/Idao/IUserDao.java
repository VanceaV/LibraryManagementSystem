package com.smoothstack.Idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smoothstack.entity.User;

public interface IUserDao extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
