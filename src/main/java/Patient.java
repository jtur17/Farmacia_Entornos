import java.sql.ResultSet;
import java.sql.SQLException;

class Patient extends Person {
    public Patient() {
    }

    public Patient(String name, String mail) {
        super(name, mail);
    }

    @Override
    public void load(String id) {
    	String query = "SELECT * FROM patient WHERE mail = '" + id + "';";
    	
    	BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
			
			while (rs.next()) {
				
				this.setName(rs.getString("name"));
				this.setMail(rs.getString("mail"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}