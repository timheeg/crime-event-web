package crime.event.repository;

import info.aduna.iteration.Iterations;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openrdf.OpenRDFException;
import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.vocabulary.DC;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.model.vocabulary.XMLSchema;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.Rio;

import crime.event.vocabulary.CC;
import crime.event.vocabulary.CRIME_EVENT;
import crime.event.vocabulary.EVENT;
import crime.event.vocabulary.GEO;
import crime.event.vocabulary.TIME;

public class RepoWriter {

	/**
	 * Write the repo to out in the given format.
	 * 
	 * @param out
	 * @param repo
	 * @param format
	 * @throws OpenRDFException
	 */
	public static void output(Writer out, Repository repo, RDFFormat format)
			throws OpenRDFException {
		RepositoryConnection conn = repo.getConnection();

		try {
			RepositoryResult<Statement> statements = conn.getStatements(null,
					null, null, true);
			Model model = Iterations.addAll(statements, new LinkedHashModel());
			model.setNamespace(XMLSchema.PREFIX, XMLSchema.NAMESPACE);
			model.setNamespace(RDF.PREFIX, RDF.NAMESPACE);
			model.setNamespace(RDFS.PREFIX, RDFS.NAMESPACE);
			model.setNamespace(DC.PREFIX, DC.NAMESPACE);
			model.setNamespace(OWL.PREFIX, OWL.NAMESPACE);
			model.setNamespace(TIME.PREFIX, TIME.NAMESPACE);
			model.setNamespace(CC.PREFIX, CC.NAMESPACE);
			model.setNamespace(CRIME_EVENT.PREFIX, CRIME_EVENT.NAMESPACE);
			model.setNamespace(EVENT.PREFIX, EVENT.NAMESPACE);
			model.setNamespace(GEO.PREFIX, GEO.NAMESPACE);

			Rio.write(model, out, format);
		} finally {
			conn.close();
		}
	}

	/**
	 * Write the repo to out in RDF/XML format.
	 * 
	 * @param out
	 * @param repo
	 * @throws OpenRDFException
	 */
	public static void output(Writer out, Repository repo)
			throws OpenRDFException {
		output(out, repo, RDFFormat.RDFXML);
	}

	/**
	 * Write repo to a file named filename in the given format.
	 * 
	 * @param filename
	 * @param repo
	 * @param format
	 * @throws OpenRDFException
	 * @throws IOException
	 */
	public static void output(String filename, Repository repo, RDFFormat format)
			throws OpenRDFException, IOException {
		Path workingDir = Paths.get("");
		String outputFile = workingDir.toAbsolutePath().toString();
		FileWriter out = new FileWriter(outputFile + "/" + filename);
		output(out, repo, format);
	}

	/**
	 * Write repo to a file named filename in RDF/XML format.
	 * 
	 * @param filename
	 * @param repo
	 * @throws OpenRDFException
	 * @throws IOException
	 */
	public static void output(String filename, Repository repo)
			throws OpenRDFException, IOException {
		Path workingDir = Paths.get("");
		String outputFile = workingDir.toAbsolutePath().toString();
		FileWriter out = new FileWriter(outputFile + "/" + filename);
		output(out, repo, RDFFormat.RDFXML);
	}
}
