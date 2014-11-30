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

import cime.event.query.QueryAllThefts;

/**
 * Servlet implementation class QueryAllTheftServlet
 */
@WebServlet("/query-thefts")
public class AllTheftsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllTheftsServlet() {
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
			out.println("<h2>Query Results</h2>");
			out.println("<div>Query:</div>");
			out.println("<textarea rows=\"6\" cols=\"100\" readonly=\"true\">");
			out.println(QueryAllThefts.SelectAllThefts);
			out.println("</textarea>");
			out.println("<div style=\"padding-top:5px;\">Results:</div>");
			out.println("<textarea rows=\"10\" cols=\"100\">");

			List<String> results = QueryAllThefts.executeQuery(repo);
			for (String instance : results) {
				out.println(instance);
			}

			out.println("</textarea>");

		} catch (OpenRDFException e) {
			e.printStackTrace();
		}
	}
}
