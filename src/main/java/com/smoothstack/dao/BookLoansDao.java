package com.smoothstack.dao;

import java.sql.Connection;

import com.smoothstack.entity.BookLoans;

public interface BookLoansDao extends GenericDao<BookLoans> {

	BookLoans getById(Connection conn, int bookId, int branchId, int cardNo);

	void delete(Connection conn, int bookId, int branchId, int cardNo);

}
