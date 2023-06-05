import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServXips")
public class ServXips extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServXips() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("mail");
		int session = Integer.parseInt(request.getParameter("session"));
		
		System.out.println("mail: " + mail);
		System.out.println("session: " + session);
		
		Doctor doctor = new Doctor();
		doctor.load(mail);
		if (doctor.isLogged(mail, session)) {
			doctor.loadReleaseList();
			response.getWriter().write(doctor.getTable());
		}
		return;
	}
}