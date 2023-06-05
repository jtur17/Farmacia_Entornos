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

@WebServlet("/ServMedicines")
public class ServMedicines extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServMedicines() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("mail");
		int session = Integer.parseInt(request.getParameter("session"));
		
		// Creamos el arraylist donde vamos a meter todas las medicinas
		ArrayList<Medicine> listMedicine = new ArrayList<>();
		
		Doctor doctor = new Doctor();
		doctor.load(mail);
		
		if(doctor.isLogged(mail, session)) {
			String query = "SELECT * FROM medicine";
			
			BBDD bd = new BBDD();
			bd.conectar();
			ResultSet rs = bd.loadSelect(query);
			
			try {
				while(rs.next()) {
					Medicine medicine = new Medicine();
					medicine.load(rs.getInt("id"));
					listMedicine.add(medicine);		
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Gson gson = new Gson();
			String json = gson.toJson(listMedicine);
			
			// Respuesta
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		    response.setStatus(HttpServletResponse.SC_OK);
		}
		
	}

}
