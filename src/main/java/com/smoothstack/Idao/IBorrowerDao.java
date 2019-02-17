package com.smoothstack.Idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smoothstack.entity.Borrower;

public interface IBorrowerDao extends JpaRepository<Borrower, Long> {

}
