package demo;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;

import cime.event.Creator;
import cime.event.query.QueryProcessor;
import cime.event.query.SparqlQuery;
import crime.event.repository.OntologyLoader;
import crime.event.repository.RepoWriter;

public class RepoTest {

	public static void main(String[] args) throws OpenRDFException, IOException {

		testRepo();
	}

	public static void testRepo() throws OpenRDFException, IOException {
		Repository repo = new SailRepository(new MemoryStore());
		repo.initialize();

		System.out.println("Load ontology");
		OntologyLoader.loadCrimeEventOntology(repo);

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
