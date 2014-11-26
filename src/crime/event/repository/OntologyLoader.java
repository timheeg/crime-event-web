package crime.event.repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.rio.RDFFormat;

public class OntologyLoader {

	public static final String CrimeEventOntologyFile = "C:/Users/heegt/Desktop/Personal/CS7900 Web 3.0/Project/Ontology/crime-event-ontology.owl";

	/**
	 * Load the Crime Event Ontology into the repository instance from the file
	 * location.
	 * 
	 * @param repo
	 *            The repository / knowledge base to be loaded.
	 * @param ontologyFile
	 *            The file path and name to the Crime Event Ontology to load.
	 *            The ontology must be stored as RDF+XML.
	 * 
	 * @throws OpenRDFException
	 *             Upon connection failure with the repo instance.
	 * @throws IOException
	 *             Upon failure loading the repository.
	 */
	public static void loadCrimeEventOntology(Repository repo,
			String ontologyFile) throws OpenRDFException, IOException {
		RepositoryConnection conn = repo.getConnection();
		try {
			File file = new File(ontologyFile);
			conn.add(file, null, RDFFormat.RDFXML);
		} finally {
			conn.close();
		}
	}

	/**
	 * Load the Crime Event Ontology into the repository instance from the URL.
	 * 
	 * @param repo
	 *            The repository / knowledge base to the loaded.
	 * @param ontology
	 *            The URL to the Crime Event Ontology to load. The ontology must
	 *            be stored as RDF+XML.
	 * 
	 * @throws OpenRDFException
	 *             Upon connection failure with the repo instance.
	 * @throws IOException
	 *             Upon failure loading the repository.
	 */
	public static void loadCrimeEventOntology(Repository repo, URL ontology)
			throws OpenRDFException, IOException {
		RepositoryConnection conn = repo.getConnection();
		try {
			conn.add(ontology, null, RDFFormat.RDFXML);
		} finally {
			conn.close();
		}
	}
}
