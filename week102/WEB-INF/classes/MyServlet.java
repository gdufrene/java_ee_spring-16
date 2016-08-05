import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/bonjour")
public class MyServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname = req.getParameter("prenom");
		String lastname  = req.getParameter("name");

		Writer out = resp.getWriter();
		out.write("<p>Bonjour <b>" + firstname + " " + lastname + "</b> !</p>");
	}

}