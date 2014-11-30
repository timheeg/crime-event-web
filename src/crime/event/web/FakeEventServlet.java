package crime.event.web;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.manager.LocalRepositoryManager;

import cime.event.Creator;

/**
 * Servlet implementation class FakeEventServlet
 */
@WebServlet("/fake-create")
public class FakeEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FakeEventServlet() {
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

			int i = 1;
			String eventId = "event" + i++;
			ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
			String datetime = now.format(DateTimeFormatter.ISO_INSTANT);
			Creator.createInstance(repo, eventId, "Theft", datetime, datetime,
					"39.779270", "-84.065762", "123456", "4");

			eventId = "event" + i++;
			Creator.createInstance(repo, eventId, "Trespass", datetime,
					datetime, "39.779906", "-84.065387", "123456", "1");

			eventId = "event" + i++;
			Creator.createInstance(repo, eventId, "Vandalism", datetime,
					datetime, "39.783112", "-84.063713", "123456", "1");

			eventId = "event" + i++;
			Creator.createInstance(repo, eventId, "Vandalism", datetime,
					datetime, "39.788514", "-84.065278", "123456", "1");

			eventId = "event" + i++;
			Creator.createInstance(repo, eventId, "Drunkenness", datetime,
					datetime, "39.785729", "-84.069167", "123456", "8");

		} catch (OpenRDFException e) {
			e.printStackTrace();
		}

		response.sendRedirect("view");
	}
}
