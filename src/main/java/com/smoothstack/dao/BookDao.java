package com.smoothstack.dao;

import java.sql.Connection;
import java.util.List;

import com.smoothstack.entity.Book;



public interface BookDao extends GenericDao<Book> {

	List<Book> getAllBooksWithNumberOfCopies(Connection conn,int branchId);

}
