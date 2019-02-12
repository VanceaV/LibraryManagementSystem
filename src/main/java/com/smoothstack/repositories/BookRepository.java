package com.smoothstack.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smoothstack.dao.AuthorDao;
import com.smoothstack.dao.BookDao;
import com.smoothstack.dao.PublisherDao;
import com.smoothstack.entity.Book;

@Component
public class BookRepository implements BookDao {

	private AuthorDao authorDao = new AuthorRepository();

	private PublisherDao publisherDao = new PublisherRepository();

	@Override
	public Book getById(Connection conn, int id) {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Book book = null;
		String sqlQuery = "SELECT * FROM library.tbl_book where bookId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				book.setId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setPublisher(publisherDao.getById(conn, resultSet.getInt("pubId")));
				book.setAuthor(authorDao.getById(conn, resultSet.getInt("authId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;

	}

	@Override
	public void add(Connection conn, Book book) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "INSERT INTO `library`.`tbl_book` (`title`, `authId`, `pubId`) VALUES ( ?, ?, ?)";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setString(1, book.getTitle());
			prepareStatement.setInt(2, book.getAuthor().getId());
			prepareStatement.setInt(3, book.getPublisher().getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	@Override
	public void update(Connection conn, Book book) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "update tbl_book  set title = ?, authId = ?, pubId=? where bookId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setString(1, book.getTitle());
			prepareStatement.setInt(2, book.getAuthor().getId());
			prepareStatement.setInt(3, book.getPublisher().getId());
			prepareStatement.setInt(4, book.getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Connection conn, int bookId) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "DELETE FROM library.tbl_book where bookId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Book> getAllBooksWithNumberOfCopies(Connection conn, int branchId) {

		List<Book> books = new ArrayList<Book>();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Book book;

		String sqlQuery = "select bookId,title,authId,pubId from tbl_book,tbl_author where tbl_book.authId=tbl_author.authorId and tbl_book.bookId in (select bookId from tbl_book_copies where tbl_book_copies.branchId=? and tbl_book_copies.noOfCopies > 0)";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, branchId);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				book.setId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(authorDao.getById(conn, resultSet.getInt("authId")));
				book.setPublisher(publisherDao.getById(conn, resultSet.getInt("pubId")));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<Book> getAll(Connection conn) {
		List<Book> books = new ArrayList<Book>();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Book book;

		String sqlQuery = "select * from tbl_book";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				book.setId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(authorDao.getById(conn, resultSet.getInt("authId")));
				book.setPublisher(publisherDao.getById(conn, resultSet.getInt("pubId")));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

}
