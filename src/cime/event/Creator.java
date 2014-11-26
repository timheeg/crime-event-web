package cime.event;

import javax.xml.datatype.DatatypeConstants;

import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

import crime.event.vocabulary.CRIME_EVENT;
import crime.event.vocabulary.EVENT;
import crime.event.vocabulary.GEO;
import crime.event.vocabulary.TIME;

public class Creator {

	/**
	 * Create a CrimeEvent triple instance.
	 * 
	 * @param repo
	 *            The RDF repository or knowledge base instance to insert.
	 * @param eventId
	 *            The event instance identifier, e.g. "event12"
	 * @param type
	 *            The type of CrimeEvent, maps to the Crime Event Ontology.
	 * @param startTime
	 *            The start date time.
	 * @param endTime
	 *            The end date time
	 * @param latitude
	 *            In decimal degrees.
	 * @param longitude
	 *            In decimal degrees.
	 * @param geohash
	 *            The geohash value as a string.
	 * @param impact
	 *            The event impact value as a string.
	 * 
	 * @throws RepositoryException
	 *             If a connection cannot be created for the repo.
	 */
	public static void createInstance(Repository repo, String eventId,
			String type, String startTime, String endTime, String latitude,
			String longitude, String geohash, String impact)
			throws RepositoryException {
		RepositoryConnection conn = repo.getConnection();

		try {
			ValueFactory factory = repo.getValueFactory();

			// Create event instance
			URI event = factory.createURI(CRIME_EVENT.NAMESPACE, eventId);
			conn.add(event, RDF.TYPE,
					factory.createURI(CRIME_EVENT.NAMESPACE, type));

			// Add start time
			Literal startTimeLiteral = factory.createLiteral(
					DatatypeConstants.DATETIME.toString(), startTime);

			String startId = eventId + "startTime";
			URI startTimeURI = factory
					.createURI(CRIME_EVENT.NAMESPACE, startId);
			conn.add(startTimeURI, RDF.TYPE, TIME.INSTANT);
			conn.add(startTimeURI, TIME.IN_XSD_DATETIME, startTimeLiteral);
			conn.add(event, TIME.HAS_BEGINNING, startTimeURI);

			// Add end time
			Literal endTimeLiteral = factory.createLiteral(
					DatatypeConstants.DATETIME.toString(), endTime);

			String endId = eventId + "endTime";
			URI endTimeURI = factory.createURI(CRIME_EVENT.NAMESPACE, endId);
			conn.add(endTimeURI, RDF.TYPE, TIME.INSTANT);
			conn.add(endTimeURI, TIME.IN_XSD_DATETIME, endTimeLiteral);
			conn.add(event, TIME.HAS_END, endTimeURI);

			// Add location
			String locationId = eventId + "location";
			URI location = factory.createURI(CRIME_EVENT.NAMESPACE, locationId);
			conn.add(location, RDF.TYPE, GEO.POINT);
			conn.add(location, GEO.LAT, factory.createLiteral(latitude));
			conn.add(location, GEO.LONG, factory.createLiteral(longitude));
			conn.add(event, EVENT.PLACE, location);

			// Add geohash and impact data properties
			conn.add(event, CRIME_EVENT.GEOHASH, factory.createLiteral(geohash));
			conn.add(event, CRIME_EVENT.IMPACT, factory.createLiteral(impact));

		} finally {
			conn.close();
		}
	}
}
