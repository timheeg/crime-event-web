package crime.event.web.query;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.manager.LocalRepositoryManager;

import cime.event.query.WorstCrimeQuery;

/**
 * Servlet implementation class WosrtCrimeServlet
 */
@WebServlet("/query-worst")
public class WosrtCrimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WosrtCrimeServlet() {
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
			out.println("<h2>What type of crime event has the most occurrences?</h2>");
			out.println("<h3>Query Results</h3>");
			out.println("<div>Query:</div>");
			out.println("<textarea rows=\"10\" cols=\"100\" readonly=\"true\">");
			out.println(WorstCrimeQuery.SelectWorstCrime);
			out.println("</textarea>");
			out.println("<div style=\"padding-top:5px;\">Results:</div>");
			out.println("<textarea rows=\"4\" cols=\"100\">");

			List<String> results = WorstCrimeQuery.executeQuery(repo);
			for (String instance : results) {
				out.println(instance);
			}

			out.println("</textarea>");

		} catch (OpenRDFException e) {
			e.printStackTrace();
		}
	}
}
