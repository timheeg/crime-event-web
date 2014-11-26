package crime.event.vocabulary;

import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class TIME {

	public static final String NS_NAME = "time";

	public static final String NAMESPACE = "http://www.w3.org/2006/time#";

	public static final URI INSTANT;

	public static final URI IN_XSD_DATETIME;

	public static final URI HAS_BEGINNING;

	public static final URI HAS_END;

	static {
		ValueFactory factory = ValueFactoryImpl.getInstance();

		INSTANT = factory.createURI(NAMESPACE, "Instant");

		IN_XSD_DATETIME = factory.createURI(NAMESPACE, "inXSDDateTime");

		HAS_BEGINNING = factory.createURI(NAMESPACE, "hasBeginning");

		HAS_END = factory.createURI(NAMESPACE, "hasEnd");
	}

}
