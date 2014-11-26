package crime.event.repository;

import java.io.File;
import java.io.IOException;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.rio.RDFFormat;

public class OntologyLoader {

	private static String ontologyFile = "C:/Users/heegt/Desktop/Personal/CS7900 Web 3.0/Project/Ontology/crime-event-ontology.owl";

	public static void loadCrimeEventOntology(Repository repo)
			throws OpenRDFException, IOException {
		RepositoryConnection conn = repo.getConnection();
		try {
			File file = new File(ontologyFile);
			conn.add(file, null, RDFFormat.RDFXML);
		} finally {
			conn.close();
		}
	}
}
