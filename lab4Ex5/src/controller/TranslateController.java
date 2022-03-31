package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.MyDatabase;

/**
 * This class is created to store the function that retrieve
 * data from the database
 * 
 * @author WM TAN
 *
 */

public class TranslateController {
	
	// get the translated text from the database
	public String findTranslate(String text, String translateLan) throws ClassNotFoundException, SQLException {
		
		String sql = "select " + translateLan + " from language where English = ?";
		
		// Connection
		Connection conn = MyDatabase.doConnection();
		
		// PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, text);
		
		// view
		ResultSet resultSet = preparedStatement.executeQuery();
		
		// close the connection
		conn.close();
		
		return resultSet.getString(1);
	}
}
