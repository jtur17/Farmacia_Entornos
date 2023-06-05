import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Release")
public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Release() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("1");
		// Sacamos los parametros
		String mail = request.getParameter("mail");
		System.out.println("2");
		int session = Integer.parseInt(request.getParameter("session"));
		System.out.println("3");
		int idXip = Integer.parseInt(request.getParameter("idXip"));
		System.out.println("4");
		int idMed = Integer.parseInt(request.getParameter("idMed"));
		System.out.println("5");
		String date = request.getParameter("date");
		System.out.println("6");
		String mailP = request.getParameter("mailP");
		
		// Verificamos la sesión del doctor
		Doctor doctor = new Doctor();
		doctor.load(mail);
		
		
		if (!doctor.isLogged(mail, session)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		// Creamos la consulta de inserción
		String insertQuery = "INSERT INTO xip (id, doctor_mail, id_medicine, date, id_patient) VALUES (" + idXip + ", '" + mail + "', " + idMed + ", '" + date + "', '" + mailP + "');";
		
		// Ejecutamos la consulta en la base de datos
		BBDD bd = new BBDD();
		bd.executeInsert(insertQuery);
		
		response.setStatus(HttpServletResponse.SC_OK);
		
	}

}
