package crime.event.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.manager.LocalRepositoryManager;

import crime.event.repository.OntologyLoader;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/load")
public class OntologyLoaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OntologyLoaderServlet() {
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

			// final String ontologyFile = getServletContext().getResource(
			// "/ontoloty/crime-event-ontology.owl").toString();

			final String ontologyFile = getServletContext().getRealPath(
					"/ontology/crime-event-ontology.owl");

			OntologyLoader.loadCrimeEventOntology(repo, ontologyFile);

		} catch (OpenRDFException e) {
			e.printStackTrace();
		}

		response.sendRedirect("view");
	}
}
