package com.smoothstack.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.BookLoansDao;
import com.smoothstack.dao.BorrowerDao;
import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.entity.BookLoans;

@Component
public class BookLoansRepository implements BookLoansDao {

	private BookDao bookDao = new BookRepository();

	private LibraryBranchDao libraryBranchDao = new LibraryBranchRepository();

	private BorrowerDao borroweDao = new BorrowerRepository();

	@Override
	public List<BookLoans> getAll(Connection conn) {

		List<BookLoans> bookLoans = new ArrayList<BookLoans>();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		BookLoans bookLoan;

		String sqlQuery = "select * from tbl_book_loans";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				bookLoan = new BookLoans();
				bookLoan.setBook(bookDao.getById(conn, resultSet.getInt("bookId")));
				bookLoan.setLibraryBranch(libraryBranchDao.getById(conn, resultSet.getInt("branchId")));
				bookLoan.setBorrower(borroweDao.getById(conn, resultSet.getInt("cardNo")));
				bookLoan.setDateOut(resultSet.getDate("dateOut").toLocalDate());
				bookLoan.setDueDate(resultSet.getDate("dueDate").toLocalDate());
				bookLoans.add(bookLoan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookLoans;

	}

	@Override
	public BookLoans getById(Connection conn, int bookId, int branchId, int cardNo) {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		BookLoans bookLoan = null;
		String sqlQuery = "select * from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookId);
			prepareStatement.setInt(2, branchId);
			prepareStatement.setInt(3, cardNo);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				bookLoan = new BookLoans();
				bookLoan.setBook(bookDao.getById(conn, resultSet.getInt("bookId")));
				bookLoan.setLibraryBranch(libraryBranchDao.getById(conn, resultSet.getInt("branchId")));
				bookLoan.setBorrower(borroweDao.getById(conn, resultSet.getInt("cardNo")));
				bookLoan.setDateOut(resultSet.getDate("dateOut").toLocalDate());
				bookLoan.setDueDate(resultSet.getDate("dueDate").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookLoan;
	}

	@Override
	public void add(Connection conn, BookLoans bookLoan) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "insert into tbl_book_loans(bookId,branchId,cardNo,dateOut,dueDate) values(?,?,?,?,?)";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookLoan.getBook().getId());
			prepareStatement.setInt(2, bookLoan.getLibraryBranch().getId());
			prepareStatement.setInt(3, bookLoan.getBorrower().getId());
			prepareStatement.setDate(4, java.sql.Date.valueOf(bookLoan.getDateOut()));
			prepareStatement.setDate(5, java.sql.Date.valueOf(bookLoan.getDueDate()));
			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Connection conn, BookLoans bookLoan) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "update tbl_book_loans  set bookId = ?, branchId = ?, cardNo = ?, dateOut = ?, dueDate = ?  where bookId= ? and branchId = ? and cardNo= ?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookLoan.getBook().getId());
			prepareStatement.setInt(2, bookLoan.getLibraryBranch().getId());
			prepareStatement.setInt(3, bookLoan.getBorrower().getId());
			prepareStatement.setDate(4, java.sql.Date.valueOf(bookLoan.getDateOut()));
			prepareStatement.setDate(5, java.sql.Date.valueOf(bookLoan.getDueDate()));
			prepareStatement.setInt(6, bookLoan.getBook().getId());
			prepareStatement.setInt(7, bookLoan.getLibraryBranch().getId());
			prepareStatement.setInt(8, bookLoan.getBorrower().getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Connection conn, int bookId, int branchId, int cardNo) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "delete from tbl_book_loans where bookId=? and branchId=? and cardNo=?";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookId);
			prepareStatement.setInt(2, branchId);
			prepareStatement.setInt(3, cardNo);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
