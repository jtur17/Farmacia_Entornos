import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


class Xip {
    private int id;
    private Medicine medicine;
    private Patient patient;
    private LocalDate date; // Fecha de caducidad

    public Xip() {
    }

    public Xip(int id, Medicine medicine, Patient patient, LocalDate date) {
        this.id = id;
        this.medicine = medicine;
        this.patient = patient;
        this.date = date;
    }

  
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void load(int id) {
        
    	String query = "SELECT * FROM xip WHERE id = '" + id + "';";
		
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
			
			while (rs.next()) {
				
				// Sacamos el id de xip
				this.setId(rs.getInt("id"));
				
				// Sacamos los valores de las medicinas y creamos el objeto
				Medicine medicine = new Medicine();
				medicine.load(rs.getInt("id_medicine"));
				this.setMedicine(medicine);
				
				// Sacamos los valores de los pacientes y creamos el objeto
				Patient patient = new Patient();
				patient.load(rs.getString("id_patient"));
				this.setPatient(patient);
				
				// Sacamos la fecha de caducidad, la pasamos a LocalDate y lo añadimos al xip
				Date date = rs.getDate("date");

		        // Obtener LocalDate
		        LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				this.setDate(localDate);
				
				
				
			}
		
		} catch (SQLException e) {
			
            System.err.println("Error en Doctor.load : " + e.getMessage());
		}
	}
   
}