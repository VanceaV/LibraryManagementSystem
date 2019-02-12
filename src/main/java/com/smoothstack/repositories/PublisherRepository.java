package com.smoothstack.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smoothstack.dao.PublisherDao;
import com.smoothstack.entity.Publisher;

@Component
public class PublisherRepository implements PublisherDao {

	@Override
	public List<Publisher> getAll(Connection conn) {
		List<Publisher> publishers = new ArrayList<Publisher>();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Publisher publisher;

		String sqlQuery = "select * from tbl_publisher";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				publisher = new Publisher();
				publisher.setId(resultSet.getInt("publisherId"));
				publisher.setPublisherName(resultSet.getString("publisherName"));
				publisher.setPublisherAddress(resultSet.getString("publisherAddress"));
				publisher.setPublisherPhone(resultSet.getString("publisherPhone"));

				publishers.add(publisher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return publishers;
	}

	@Override
	public Publisher getById(Connection conn, int publisherId) {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Publisher publisher = null;
		String sqlQuery = "SELECT * FROM tbl_publisher where publisherId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, publisherId);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				publisher = new Publisher();
				publisher.setId(resultSet.getInt("publisherId"));
				publisher.setPublisherName(resultSet.getString("publisherName"));
				publisher.setPublisherAddress(resultSet.getString("publisherAddress"));
				publisher.setPublisherPhone(resultSet.getString("publisherPhone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return publisher;
	}

	@Override
	public void add(Connection conn, Publisher publisher) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "insert into tbl_publisher(publisherName,publisherAddress,publisherPhone) values (?,?,?)";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setString(1, publisher.getPublisherName());
			prepareStatement.setString(2, publisher.getPublisherAddress());
			prepareStatement.setString(3, publisher.getPublisherPhone());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void update(Connection conn, Publisher publisher) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "update tbl_publisher set publisherName =?, publisherAddress=?,publisherPhone=? where publisherId = ?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setString(1, publisher.getPublisherName());
			prepareStatement.setString(2, publisher.getPublisherAddress());
			prepareStatement.setString(3, publisher.getPublisherPhone());
			prepareStatement.setInt(4, publisher.getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Connection conn, int publisherId) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "DELETE FROM tbl_publisher where publisherId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, publisherId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
