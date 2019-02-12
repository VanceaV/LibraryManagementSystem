package com.smoothstack.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smoothstack.dao.AuthorDao;
import com.smoothstack.entity.Author;

@Component
public class AuthorRepository implements AuthorDao {

	@Override
	public List<Author> getAll(Connection conn) {
		List<Author> authors = new ArrayList<Author>();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Author author;

		String sqlQuery = "select * from tbl_author";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				author = new Author();
				author.setId(resultSet.getInt("authorId"));
				author.setAuthorName(resultSet.getString("authorName"));
				authors.add(author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}

	@Override
	public Author getById(Connection conn, int id) {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Author author = null;
		String sqlQuery = "SELECT * FROM library.tbl_author where authorId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				author = new Author();
				author.setId(resultSet.getInt("authorId"));
				author.setAuthorName(resultSet.getString("authorName"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return author;
	}

	@Override
	public void add(Connection conn, Author author) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "insert into tbl_author(authorName) values (?)";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setString(1, author.getAuthorName());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void update(Connection conn, Author author) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "update tbl_author  set authorName = ? where authorId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setString(1, author.getAuthorName());
			prepareStatement.setInt(2, author.getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Connection conn, int authorId) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "DELETE FROM library.tbl_author where authorId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, authorId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
