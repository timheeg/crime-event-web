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

	public static void createInstance(Repository repo, String eventId,
			String type, String startTime, String endTime, String lat,
			String longitude, String geohash, String impact)
			throws RepositoryException {
		RepositoryConnection conn = repo.getConnection();

		try {
			// Model model = new LinkedHashModel();
			// model.setNamespace("foaf", FOAF.NAMESPACE);
			// model.setNamespace("owl", OWL.NAMESPACE);
			// model.setNamespace("dc", DC.NAMESPACE);
			// model.setNamespace("xsd", XMLSchema.NAMESPACE);
			// model.setNamespace("rdfs", RDFS.NAMESPACE);
			// model.setNamespace("rdf", RDF.NAMESPACE);
			// model.setNamespace(CRIME_EVENT.NS_NAME, CRIME_EVENT.NAMESPACE);
			// model.setNamespace(GEO.NS_NAME, GEO.NAMESPACE);
			// model.setNamespace(EVENT.NS_NAME, EVENT.NAMESPACE);
			// model.setNamespace(TIME.NS_NAME, TIME.NAMESPACE);

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
			conn.add(location, GEO.LAT, factory.createLiteral(lat));
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
