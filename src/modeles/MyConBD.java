package modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConBD {
	private String url = "jdbc:mysql://localhost:3306/GStocks";
	private String user = "user";
	private String passwd = "password";
	private Connection connection;
	static MyConBD instance;
	
	private MyConBD() {
		try {
			connection = DriverManager.getConnection(url,user,passwd);
			System.out.println("Connexion etabli");
		}catch (SQLException ex){
			Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static MyConBD getInstance() {
		if(instance == null) {
			instance = new MyConBD();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	
	//Fermer la requete preparée 
		public static void closePreparedStatement(PreparedStatement preparedStatement) {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ignore) {
				}
			}
		}

		//Annuler transaction en cas d'erreur
		public static void rollback(Connection connection) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException ignore) {
				}
			}
		}

		// Fermer la connection
		public static void closeConnection(Connection connection) {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ignore) {
				}
			}
		}

		// Fermer le resultat
		public static void closeResultSet(ResultSet resultSet) {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			}
		}
}
