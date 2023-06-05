import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.*;

@WebServlet("/ServPatients")
public class ServPatients extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServPatients() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Cogemos los parametros
		String mail = request.getParameter("mail");
		int session = Integer.parseInt(request.getParameter("session"));
		
		Doctor doctor = new Doctor();
		doctor.load(mail);
		
		if (!doctor.isLogged(mail, session)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
			
		// Conexion base de datos 
		String query = "SELECT mail FROM patient;";
		
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		// Creamos una lista para almacenar los mails de los pacientes
		ArrayList<String> patientMails = new ArrayList<>();
		
		try {
			while (rs.next()) {
				String mailPatient = rs.getString("mail");
				patientMails.add(mailPatient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(patientMails);
			

		//Configurar la respuesta HTTP
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	    response.setStatus(HttpServletResponse.SC_OK);
			
	}

}
