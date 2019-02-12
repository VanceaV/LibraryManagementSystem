package com.smoothstack.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smoothstack.dao.BorrowerDao;
import com.smoothstack.entity.Borrower;

@Component
public class BorrowerRepository implements BorrowerDao {

	@Override
	public List<Borrower> getAll(Connection conn) {

		List<Borrower> borrowers = new ArrayList<Borrower>();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Borrower borrower;

		String sqlQuery = "select * from tbl_borrower";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				borrower = new Borrower();
				borrower.setId(resultSet.getInt("cardNo"));
				borrower.setName(resultSet.getString("name"));
				borrower.setAddress(resultSet.getString("address"));
				borrower.setPhone(resultSet.getString("phone"));
				borrowers.add(borrower);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrowers;
	}

	@Override
	public Borrower getById(Connection conn, int id) {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Borrower borrower = null;
		String sqlQuery = "select * from tbl_borrower where cardNo=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				borrower = new Borrower();
				borrower.setId(resultSet.getInt("cardNo"));
				borrower.setName(resultSet.getString("name"));
				borrower.setAddress(resultSet.getString("address"));
				borrower.setPhone(resultSet.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrower;
	}

	@Override
	public void add(Connection conn, Borrower borrower) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "insert into tbl_borrower (`name`, `address`, `phone`) VALUES ( ?, ?, ?)";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setString(1, borrower.getName());
			prepareStatement.setString(2, borrower.getAddress());
			prepareStatement.setString(3, borrower.getPhone());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void update(Connection conn, Borrower borrower) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "update tbl_borrower  set name = ?, address = ?, phone=? where cardNo=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setString(1, borrower.getName());
			prepareStatement.setString(2, borrower.getAddress());
			prepareStatement.setString(3, borrower.getPhone());
			prepareStatement.setInt(4, borrower.getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Connection conn, int borrowerID) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "delete from tbl_borrower where cardNo=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, borrowerID);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
