package crime.event.vocabulary;

import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class EVENT {

	public static final String NS_NAME = "event";

	public static final String NAMESPACE = "http://purl.org/NET/c4dm/event.owl#";

	public static final URI PLACE;

	static {
		ValueFactory factory = ValueFactoryImpl.getInstance();

		PLACE = factory.createURI(NAMESPACE, "place");
	}
}
