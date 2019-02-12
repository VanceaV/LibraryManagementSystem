package com.smoothstack.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.entity.LibraryBranch;

@Component
public class LibraryBranchRepository implements LibraryBranchDao {

	@Override
	public List<LibraryBranch> getAll(Connection conn) {
		List<LibraryBranch> branches = new ArrayList<LibraryBranch>();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		LibraryBranch libraryBranch;

		String sqlQuery = "select * from tbl_library_branch";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				libraryBranch = new LibraryBranch();
				libraryBranch.setId(resultSet.getInt("branchId"));
				libraryBranch.setBranchName(resultSet.getString("branchName"));
				libraryBranch.setBranchAddress(resultSet.getString("branchAddress"));
				branches.add(libraryBranch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branches;
	}

	@Override
	public LibraryBranch getById(Connection conn, int id) {

		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		LibraryBranch libraryBranch = null;
		String sqlQuery = "select * from tbl_library_branch where branchId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				libraryBranch = new LibraryBranch();
				libraryBranch.setId(resultSet.getInt("branchId"));
				libraryBranch.setBranchName(resultSet.getString("branchName"));
				libraryBranch.setBranchAddress(resultSet.getString("branchAddress"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libraryBranch;
	}

	@Override
	public void add(Connection conn, LibraryBranch libraryBranch) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "insert into tbl_library_branch (`branchName`, `branchAddress`) VALUES ( ?, ?)";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setString(1, libraryBranch.getBranchName());
			prepareStatement.setString(2, libraryBranch.getBranchAddress());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override
	public void update(Connection conn, LibraryBranch libraryBranch) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "update tbl_library_branch set branchName =?, branchAddress =? where branchId=?";

		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setString(1, libraryBranch.getBranchName());
			prepareStatement.setString(2, libraryBranch.getBranchAddress());
			prepareStatement.setInt(3, libraryBranch.getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Connection conn, int libraryBranchId) {
		PreparedStatement prepareStatement = null;
		String sqlQuery = "delete from tbl_library_branch where branchId=?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, libraryBranchId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
