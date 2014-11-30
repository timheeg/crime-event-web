package demo;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;

import cime.event.Creator;
import cime.event.FakeEventCreator;
import cime.event.query.QueryAllThefts;
import cime.event.query.QueryProcessor;
import cime.event.query.SparqlQuery;
import cime.event.query.WorstCrimeQuery;
import crime.event.repository.OntologyLoader;
import crime.event.repository.RepoWriter;

public class RepoTest {

	public static void main(String[] args) throws OpenRDFException, IOException {

		testFakeQuery();
	}

	public static void testFakeQuery() throws OpenRDFException, IOException {
		Repository repo = new SailRepository(new MemoryStore());
		repo.initialize();

		System.out.println("Load ontology");
		URL ontology = new URL(
				"file:///C:/Users/heegt/Desktop/Personal/CS7900 Web 3.0/Project/Ontology/crime-event-ontology.owl");
		OntologyLoader.loadCrimeEventOntology(repo, ontology);

		System.out.println("Create fake events");
		FakeEventCreator.createFakeEvents(repo);

		System.out.println("Query all thefts...");
		List<String> results = QueryAllThefts.executeQuery(repo);
		for (String instance : results) {
			System.out.println(instance);
		}

		System.out.println("Query worst crime...");
		System.out.println("Query:");
		System.out.println(WorstCrimeQuery.SelectWorstCrime);
		System.out.println("Result:");
		results = WorstCrimeQuery.executeQuery(repo);
		for (String instance : results) {
			System.out.println(instance);
		}
	}

	/**
	 * Test basic repo. Load ontology, dump to file, perform select all.
	 * 
	 * @throws OpenRDFException
	 * @throws IOException
	 */
	public static void testRepo() throws OpenRDFException, IOException {
		Repository repo = new SailRepository(new MemoryStore());
		repo.initialize();

		System.out.println("Load ontology");
		URL ontology = new URL(
				"file:///C:/Users/heegt/Desktop/Personal/CS7900 Web 3.0/Project/Ontology/crime-event-ontology.owl");
		OntologyLoader.loadCrimeEventOntology(repo, ontology);

		System.out.println("Write repo");
		String outputFilename = "CrimeEventRepo.rdf";
		RepoWriter.output(outputFilename, repo);

		System.out.println("Populate instances");
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
		String datetime = now.format(DateTimeFormatter.ISO_INSTANT);
		Creator.createInstance(repo, "event1", "Arson", datetime, datetime,
				"-35.0", "-90.0", "1234356", "4");

		System.out.println("Write repo");
		outputFilename = "CrimeEventRepo2.rdf";
		RepoWriter.output(outputFilename, repo);

		System.out.println("Query repo");
		QueryProcessor.executeQuery(repo, SparqlQuery.SelectAll, System.out);
	}
}
