package com.smoothstack.dao;

import java.sql.Connection;

import java.util.List;

import com.smoothstack.entity.BookCopies;



public interface BookCopiesDao extends GenericDao<BookCopies> {

	int getNumbersOfCopiesByID(Connection conn, int branchId, int bookId);

	void updateNumbersOfCopies(Connection conn, int branchId, int bookId, int numberofCopies);

	List<BookCopies> getAllbyBranch(Connection conn, int branchId);

	BookCopies getByBranchIdandBookId(Connection conn, int branchId, int bookId);

}
