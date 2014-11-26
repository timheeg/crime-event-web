package cime.event.query;

import java.io.OutputStream;

import org.openrdf.OpenRDFException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

public class QueryProcessor {

	// TODO Create a query method handler to parse the result set manually
	// instead of outputting to stdout.

	public static void executeQuery(Repository repo, String query,
			OutputStream out) throws OpenRDFException {
		RepositoryConnection conn = repo.getConnection();

		try {
			TupleQuery tupleQuery = conn.prepareTupleQuery(
					QueryLanguage.SPARQL, query);

			SPARQLResultsXMLWriter writer = new SPARQLResultsXMLWriter(out);

			tupleQuery.evaluate(writer);
		} finally {
			conn.close();
		}
	}
}
