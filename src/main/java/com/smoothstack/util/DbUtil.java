package com.smoothstack.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private static Connection connection = null;

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		if (connection == null)

			try {
				connection = (Connection) DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/library?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
						"root", "root");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return connection;
	}
}
