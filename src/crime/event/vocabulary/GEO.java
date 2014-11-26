package crime.event.vocabulary;

import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class GEO {

	public static final String NS_NAME = "geo";

	public static final String NAMESPACE = "http://www.w3.org/2003/01/geo/wgs84_pos#";

	public static final URI POINT;

	public static final URI LAT;

	public static final URI LONG;

	static {
		ValueFactory factory = ValueFactoryImpl.getInstance();

		POINT = factory.createURI(NAMESPACE, "Point");

		LAT = factory.createURI(NAMESPACE, "lat");

		LONG = factory.createURI(NAMESPACE, "long");
	}
}
