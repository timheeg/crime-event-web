package cime.event.query;

import java.io.OutputStream;

import org.openrdf.OpenRDFException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

public class QueryProcessor {

	public static void executeQuery(Repository repo, String query,
			OutputStream out) throws OpenRDFException {
		RepositoryConnection conn = repo.getConnection();

		try {
			TupleQuery tupleQuery = conn.prepareTupleQuery(
					QueryLanguage.SPARQL, query);

			SPARQLResultsXMLWriter writer = new SPARQLResultsXMLWriter(out);
			// writer.handleNamespace(GEO.NS_NAME, GEO.NAMESPACE);
			// writer.handleNamespace(EVENT.NS_NAME, EVENT.NAMESPACE);
			// writer.handleNamespace(TIME.NS_NAME, TIME.NAMESPACE);
			// writer.handleNamespace(CRIME_EVENT.NS_NAME,
			// CRIME_EVENT.NAMESPACE);
			// writer.handleNamespace("foaf", FOAF.NAMESPACE);
			// writer.handleNamespace("owl", OWL.NAMESPACE);
			// writer.handleNamespace("dc", DC.NAMESPACE);
			// writer.handleNamespace("xsd", XMLSchema.NAMESPACE);
			// writer.handleNamespace("rdfs", RDFS.NAMESPACE);
			// writer.handleNamespace("rdf", RDF.NAMESPACE);

			tupleQuery.evaluate(writer);
		} finally {
			conn.close();
		}
	}
}
