import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
		
	public static void main(String[] args) {
		
		//This shows the original table before any changes.
		try {
			ShowAllRecords();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//This runs the insert record function and adds a new row into the table
		try {
			InsertRecordIntoCollectionTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
//		
//		//This runs the updaterecords function and updates an entity in the table
//		try {
//			UpdateRecords();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		//This runs the DeleteRecords function and deletes a row in the table
//		try {
//			DeleteRecords();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
		//Prints off the records a second time so you can see that a query has worked
		try {
			ShowAllRecords();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}	
	}
	
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
	
	private static void InsertRecordIntoCollectionTable() throws SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;
		
		try { 
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:dvd.sqlite");
			dbConnection.setAutoCommit(false);
			System.out.println("Insert Operation - Database Successfully opened");
			
			statement = dbConnection.createStatement();
			String sql = "Insert INTO collection (ID, Title, Genre, Year)\r\n" + 
					"VALUES (10,'SomethingElse','Action',2014);";
			statement.executeUpdate(sql);			
			dbConnection.commit();
			dbConnection.close();
			statement.close();
			System.out.println("Records Successfully Added");
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	
			
			
		}
	
	private static void UpdateRecords() throws SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:dvd.sqlite");
			dbConnection.setAutoCommit(false);
			System.out.println("Update Operation - Database Successfully Opened");
			
			statement = dbConnection.createStatement();
			String sql = "UPDATE collection set Title = 'Something New' WHERE ID=6;";
			statement.executeUpdate(sql);
			dbConnection.commit();
			statement.close();
			dbConnection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records Successfully Updated");
		
	}
	
	private static void DeleteRecords() throws SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:dvd.sqlite");
			dbConnection.setAutoCommit(false);
			System.out.println("Delete Operation - Database Successfully Opened");
			
			statement = dbConnection.createStatement();
			String sql = "DELETE FROM collection WHERE ID=6;";
			statement.executeUpdate(sql);
			dbConnection.commit();
			statement.close();
			dbConnection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records Successfully Deleted");
		}
	
		
}




