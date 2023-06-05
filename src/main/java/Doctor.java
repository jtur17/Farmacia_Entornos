import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class Doctor extends Person {
	
	private String pass;
	private LocalDate lastLog;
	private int session;
	private ArrayList<Xip> releaseList;
	
	// Constructor vacio
	public Doctor() {
		this.releaseList = new ArrayList<>();
	}
	
	// Constructor
	public Doctor(String name, String mail, String pass, LocalDate lastLog, int session) {
		super(name,mail);
		setPass(pass);
		setLastLog(lastLog);
		setSession(session);
		this.releaseList = new ArrayList<>();
	}
	
	// Getters y Setters
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public LocalDate getLastLog() {
		return lastLog;
	}
	public void setLastLog(LocalDate lastLog) {
		this.lastLog = lastLog;
	}
	public int getSession() {
		return session;
	}
	public void setSession(int session) {
		this.session = session;
	}
	public ArrayList<Xip> getReleaseList() {
		return releaseList;
	}
	public void setReleaseList(ArrayList<Xip> releaseList) {
		this.releaseList = releaseList;
	}
	
	
	// Sirve para sacar los datos de la base de datos
	public void load(String id) {
		
		String query = "SELECT * FROM doctor WHERE mail = '" + id + "';";
		
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
			
			while (rs.next()) {

				this.setName(rs.getString("name"));
				this.setMail(rs.getString("mail"));
				this.setPass(rs.getString("pass"));
				this.setSession(Integer.parseInt(rs.getString("session")));
					
			}
		
		} catch (SQLException e) {
			
            System.err.println("Error en Doctor.load : " + e.getMessage());
		}
	}
	
	public void login(String mail, String pass) {
		
		String query = "SELECT * FROM doctor WHERE mail = '"+mail+"' AND pass = '"+pass+"';";
		
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
			if (rs.next()) {
				
				// Para la base de datos, saber cuando se ha metido
				this.setLastLog(LocalDate.now());
				
				// Generamos números aleatorios para el session del log del doctor.
				Random random = new Random();
			    String code = "";
			   
			    for (int i = 0; i < 9; i++) {
			      code += random.nextInt(10);
			    }
			   
			    int session = Integer.parseInt(code);
			   
			    this.setSession(session);
			   
			    this.load(mail);
				
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		query = "UPDATE doctor SET last_log = '"+this.getLastLog()+"',session='"+this.getSession()+"' WHERE mail = '"+this.getMail()+"';";
		bd.updateDoctor(query);
			
		
	}
	
	public boolean isLogged(String mail, int session) {
		
		if(this.mail.equals(mail) && this.session == session ) {
			return true;
		}
		return false;
		
	}
	
	public void loadReleaseList() {
		String query = "SELECT * FROM xip WHERE doctor_mail = '"+this.mail+"';";
		
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
			while(rs.next()) {
				
				// Creo el objeto xip
				Xip xip = new Xip();
				xip.load(rs.getInt("id"));
				
				// Añado xip a la lista
				this.getReleaseList().add(xip);				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getTable() {
		StringBuilder tableBuilder = new StringBuilder();

	    // Inicio de la tabla HTML
	    tableBuilder.append("<table>");
	    tableBuilder.append("<thead>");
	    tableBuilder.append("<tr><th>ID</th><th>Medicine</th><th>Patient</th><th>Date</th></tr>");
	    tableBuilder.append("</thead>");
	    tableBuilder.append("<tbody>");

	    for (Xip xip : this.getReleaseList()) {
	        if (xip.getDate().isAfter(LocalDate.now())) {
	            // Fila para un xip vigente
	            tableBuilder.append("<tr>");
	            tableBuilder.append("<td>").append(xip.getId()).append("</td>");
	            tableBuilder.append("<td>").append(xip.getMedicine().getName()).append("</td>");
	            tableBuilder.append("<td>").append(xip.getPatient().getName()).append("</td>");
	            tableBuilder.append("<td>").append(xip.getDate()).append("</td>");
	            tableBuilder.append("</tr>");
	        }
	    }

	    // Fin de la tabla HTML
	    tableBuilder.append("</tbody>");
	    tableBuilder.append("</table>");

	    return tableBuilder.toString();
	}
	
	@Override
	public String toString() {
		return "Doctor pass: " + pass + ", Last Log: " + lastLog + ", Session: " + session + ", Release List:" + releaseList;
	}
	
}