import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DVDDAO {
	
private static Connection getDBConnection() {
		
		Connection dbConnection = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:dvd.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
				return dbConnection;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;

	}
	
	private static void ShowAllRecords() throws SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		String query = "SELECT * FROM collection;";
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			resultset = statement.executeQuery(query);
			while (resultset.next()) {
				System.out.println(resultset.getString("ID")+ " "
								+resultset.getString("Title")+ " "
								+resultset.getString("Genre")+ " "
								+resultset.getString("Year"));	
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
	private static void insertRecordIntoCollectionTable() throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		
		try { 
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:dvd.sqlite");
			connection.setAutoCommit(false);
			System.out.println("Insert Operation - Database Successfully opened");
			
			statement = connection.createStatement();
			String sql = "Insert INTO collections (ID, Title, Genre, Year)\r\n" + 
					"VALUES (6,'SomethingElse','Action',2014);";
			statement.executeUpdate(sql);
			statement.close();
			connection.commit();
			connection.close();
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records Successfully Added");
			
			
		}

}
