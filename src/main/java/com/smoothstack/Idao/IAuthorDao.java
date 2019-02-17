package com.smoothstack.Idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smoothstack.entity.Author;


public interface IAuthorDao extends JpaRepository<Author, Long> {

}
