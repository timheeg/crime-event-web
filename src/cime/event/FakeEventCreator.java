package cime.event;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;

public class FakeEventCreator {

	/**
	 * Create several fake events in the repo.
	 * 
	 * @param repo
	 * @throws OpenRDFException
	 */
	public static void createFakeEvents(Repository repo)
			throws OpenRDFException {
		int i = 1;
		String eventId = "event" + i++;
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
		String datetime = now.format(DateTimeFormatter.ISO_INSTANT);
		Creator.createInstance(repo, eventId, "Theft", datetime, datetime,
				"39.779270", "-84.065762", "123456", "4");

		eventId = "event" + i++;
		Creator.createInstance(repo, eventId, "Trespass", datetime, datetime,
				"39.779906", "-84.065387", "123456", "1");

		eventId = "event" + i++;
		Creator.createInstance(repo, eventId, "Vandalism", datetime, datetime,
				"39.783112", "-84.063713", "123456", "1");

		eventId = "event" + i++;
		Creator.createInstance(repo, eventId, "Vandalism", datetime, datetime,
				"39.788514", "-84.065278", "123456", "1");

		eventId = "event" + i++;
		Creator.createInstance(repo, eventId, "Drunkenness", datetime,
				datetime, "39.785729", "-84.069167", "123456", "8");
	}
}
