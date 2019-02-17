package com.smoothstack.Idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smoothstack.entity.Publisher;

public interface IPublisherDao extends JpaRepository<Publisher, Long> {

}
