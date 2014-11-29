package crime.event.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.manager.LocalRepositoryManager;

import crime.event.repository.RepoWriter;

/**
 * Servlet implementation class ViewOntologyServlet
 */
@WebServlet("/view")
public class ViewOntologyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewOntologyServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		LocalRepositoryManager manager = (LocalRepositoryManager) getServletContext()
				.getAttribute("RepoManager");

		try {
			Repository repo = manager.getRepository("crime-event-kb");

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>Crime Event Knowledge Base</h1>");
			out.println("<h2>Contents</h2>");
			out.println("<textarea rows=\"20\" cols=\"100\">");

			RepoWriter.output(out, repo);

			out.println("</textarea>");

		} catch (OpenRDFException e) {
			e.printStackTrace();
		}
	}
}
