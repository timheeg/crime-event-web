package crime.event.repository;

import info.aduna.iteration.Iterations;

import java.io.FileOutputStream;
import java.io.IOException;
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

public class RepoWriter {

	public static void output(String filename, Repository repo)
			throws OpenRDFException, IOException {
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
			model.setNamespace(CC.PREFIX, CC.NAMESPACE);
			model.setNamespace(CRIME_EVENT.PREFIX, CRIME_EVENT.NAMESPACE);
			model.setNamespace(EVENT.PREFIX, EVENT.NAMESPACE);

			Path workingDir = Paths.get("");
			String outputFile = workingDir.toAbsolutePath().toString();
			FileOutputStream out = new FileOutputStream(outputFile + "/"
					+ filename);

			Rio.write(model, out, RDFFormat.RDFXML);
		} finally {
			conn.close();
		}
	}
}
