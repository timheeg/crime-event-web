package cime.event.query;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.openrdf.OpenRDFException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

public class QueryProcessor {

	/**
	 * Execute query against repo and dump the results to out.
	 * 
	 * @param repo
	 * @param query
	 * @param out
	 * @throws OpenRDFException
	 */
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

	/**
	 * Execute query against repo and dump results to a file with the specified
	 * filename.
	 * 
	 * @param repo
	 * @param query
	 * @param filename
	 * @throws OpenRDFException
	 * @throws IOException
	 */
	public static void executeQuery(Repository repo, String query,
			String filename) throws OpenRDFException, IOException {
		executeQuery(repo, query, new FileOutputStream(filename));
	}
}
