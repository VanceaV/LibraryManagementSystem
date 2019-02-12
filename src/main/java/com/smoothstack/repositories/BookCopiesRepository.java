package com.smoothstack.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smoothstack.dao.BookCopiesDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.entity.BookCopies;

@Component
public class BookCopiesRepository implements BookCopiesDao {

	private BookDao bookDao = new BookRepository();
	private LibraryBranchDao libraryBranchDao = new LibraryBranchRepository();

	@Override
	public int getNumbersOfCopiesByID(Connection conn, int branchId, int bookId) {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "select noOfCopies from tbl_book_copies  where bookId=? and branchId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookId);
			prepareStatement.setInt(2, branchId);
			resultSet = prepareStatement.executeQuery();

			if (resultSet.next())
				return resultSet.getInt("noOfCopies");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void updateNumbersOfCopies(Connection conn, int branchId, int bookId, int numberofCopies) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId=?;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, numberofCopies);
			prepareStatement.setInt(2, bookId);
			prepareStatement.setInt(3, branchId);
			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<BookCopies> getAll(Connection conn) {
		PreparedStatement prepareStatement = null;
		List<BookCopies> bookCopies = new ArrayList<BookCopies>();
		BookCopies bookCopie = null;
		ResultSet resultSet = null;
		String sqlQuery = "select * from tbl_book_copies";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				bookCopie = new BookCopies();
				bookCopie.setBook(bookDao.getById(conn, resultSet.getInt("bookId")));
				bookCopie.setLibraryBranch(libraryBranchDao.getById(conn, resultSet.getInt("branchId")));
				bookCopie.setNoOfCopies(resultSet.getInt("noOfCopies"));
				bookCopies.add(bookCopie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bookCopies;
	}

	@Override
	public List<BookCopies> getAllbyBranch(Connection conn, int branchId) {

		PreparedStatement prepareStatement = null;
		List<BookCopies> bookCopies = new ArrayList<BookCopies>();
		BookCopies bookCopie = null;
		ResultSet resultSet = null;
		String sqlQuery = "select * from tbl_book_copies where branchId=?";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, branchId);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				bookCopie = new BookCopies();
				bookCopie.setBook(bookDao.getById(conn, resultSet.getInt("bookId")));
				bookCopie.setLibraryBranch(libraryBranchDao.getById(conn, resultSet.getInt("branchId")));
				bookCopie.setNoOfCopies(resultSet.getInt("noOfCopies"));
				bookCopies.add(bookCopie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bookCopies;
	}

	@Override
	public BookCopies getByBranchIdandBookId(Connection conn, int branchId, int bookId) {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		BookCopies bookCopie = null;
		String sqlQuery = "select * from tbl_book_copies  where bookId=? and branchId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookId);
			prepareStatement.setInt(2, branchId);
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				bookCopie = new BookCopies();
				bookCopie.setBook(bookDao.getById(conn, resultSet.getInt("bookId")));
				bookCopie.setLibraryBranch(libraryBranchDao.getById(conn, resultSet.getInt("branchId")));
				bookCopie.setNoOfCopies(resultSet.getInt("noOfCopies"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookCopie;
	}

	@Override
	public void update(Connection conn, BookCopies bookCopie) {

		PreparedStatement prepareStatement = null;
		String sqlQuery = "update tbl_book_copies  set bookId = ?, branchId=?, noOfCopies=?  where bookId = ? and  branchId= ?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookCopie.getBook().getId());
			prepareStatement.setInt(2, bookCopie.getLibraryBranch().getId());
			prepareStatement.setInt(3, bookCopie.getNoOfCopies());
			prepareStatement.setInt(4, bookCopie.getBook().getId());
			prepareStatement.setInt(5, bookCopie.getLibraryBranch().getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add(Connection conn, BookCopies bookCopie) {

		PreparedStatement prepareStatement = null;
		String sqlQuery = "insert tbl_book_copies(bookId, branchId, noOfCopies) values (?,?,?)";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookCopie.getBook().getId());
			prepareStatement.setInt(2, bookCopie.getLibraryBranch().getId());
			prepareStatement.setInt(3, bookCopie.getNoOfCopies());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
