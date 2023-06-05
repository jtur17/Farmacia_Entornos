import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BBDD {

	private Connection connection;
	private Statement st;
	
	public void conectar() {
		
		String url = "jdbc:mysql://localhost:3306/farmacia";
		String user = "root";
		String password = "root";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			
            System.err.println("Error Base de Datos BBDD.connectar.Class: " + e.getMessage());

	
		}
		
		connection = null;
		
		try {

		 	connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {

            System.err.println("Connection error: " + e.getMessage());
        }
		 
		 st = null;
		 
		try {

		 	st = connection.createStatement();

        } catch (SQLException e) {

            System.err.println("Statement error: " + e.getMessage());
        }	
	}
	
	public ResultSet loadSelect(String query) {
		
		ResultSet rs = null;
			
		try {
			
			rs = st.executeQuery(query);
			
		} catch (SQLException e) {
			
            System.err.println("Error en BBDD error query: " + e.getMessage());

		}
		
		return rs;
		
	}
	
	public void updateDoctor(String query) {
				
		try {
			
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			
            System.err.println("Error en BBDD.UpdateDoctor: " + e.getMessage());

		}		
	}
	
	public void executeInsert(String query) {
		
	    try {
	    	
			conectar();
	        st.executeUpdate(query);
	        
	    } catch (SQLException e) {
	    	
	        System.err.println("Error en BBDD.ExecuteInsert: " + e.getMessage());

	    }
	}

	
}