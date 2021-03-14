package com.customerking.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.SequenceGenerator;

public class CustomerIdSequenceGenerator  extends SequenceGenerator{

private final String SEQUENCE_NAME = "id_sequence";
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) {
		Serializable nextInvid = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = session.connection();
			statement = connection.createStatement();
			try {
				resultSet = statement.executeQuery("SELECT NEXT VALUE FOR " + SEQUENCE_NAME);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resultSet.next()) {
				nextInvid = resultSet.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextInvid;
	}
}
