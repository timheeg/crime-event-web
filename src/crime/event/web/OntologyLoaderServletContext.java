package crime.event.web;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.config.RepositoryConfig;
import org.openrdf.repository.config.RepositoryImplConfig;
import org.openrdf.repository.manager.LocalRepositoryManager;
import org.openrdf.repository.sail.config.SailRepositoryConfig;
import org.openrdf.sail.config.SailImplConfig;
import org.openrdf.sail.memory.config.MemoryStoreConfig;

@WebListener
public class OntologyLoaderServletContext implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();

		// Get the path to store the repository configuration.
		final String kbPathValue = context.getContextPath();

		// Create the LocalRepositoryManager instance to be shared for the
		// entire web context.
		File kbPath = new File(kbPathValue);
		LocalRepositoryManager manager = new LocalRepositoryManager(kbPath);

		try {
			manager.initialize();
		} catch (RepositoryException e) {
			// @TODO Doesn't handle initialization failures.
			e.printStackTrace();
		}

		// Create the crime-event repository configuration.
		SailImplConfig backendConfig = new MemoryStoreConfig();
		RepositoryImplConfig repositoryTypeSpec = new SailRepositoryConfig(
				backendConfig);
		RepositoryConfig repConfig = new RepositoryConfig("crime-event-kb",
				repositoryTypeSpec);

		// Add the crime-event repository configuration to the manager.
		try {
			manager.addRepositoryConfig(repConfig);
		} catch (OpenRDFException e) {
			// @TODO Doesn't handle initialization failures.
			e.printStackTrace();
		}

		// Add the repository manager to the web context.
		context.setAttribute("RepoManager", manager);
	}

}
