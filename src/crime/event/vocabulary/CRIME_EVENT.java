package crime.event.vocabulary;

import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class CRIME_EVENT {

	public static final String PREFIX = "crime";

	public static final String NAMESPACE = "http://www.knoesis.org/ontologies/Crime#";

	public static final URI CRIME_EVENT;

	public static final URI GEOHASH;

	public static final URI IMPACT;

	public static final URI ARSON;
	public static final URI ASSAULT;
	public static final URI BAD_CHECKS;
	public static final URI BRIBERY;
	public static final URI BURGLARY;
	public static final URI COUNTERFEITING;
	public static final URI DISORDERLY_CONDUCT;
	public static final URI DRIVING_UNDER_THE_INFLUENCE;
	public static final URI DRUG;
	public static final URI DRUNKENNESS;
	public static final URI DUI;
	public static final URI EMBEZZLEMENT;
	public static final URI EXTORTION;
	public static final URI FAMILY_OFFENSES;
	public static final URI FORGERY;
	public static final URI FRAUD;
	public static final URI GAMBLING;
	public static final URI KIDNAPPING;
	public static final URI LARCENY;
	public static final URI LIQUOR_LAWS;
	public static final URI LOITERING;
	public static final URI MISSING_PERSON;
	public static final URI NARCOTIC;
	public static final URI NON_CRIMINAL;
	public static final URI OBSCENE_MATERIAL;
	public static final URI OTHER_OFFENSES;
	public static final URI PORNOGRAPHY;
	public static final URI PROSTITUTION;
	public static final URI ROBBERY;
	public static final URI RUNAWAY;
	public static final URI SEXUAL_OFFENSE;
	public static final URI STOLEN_PROPERTY;
	public static final URI SUICIDE;
	public static final URI SUSPICIOUS_OCCURRENCE;
	public static final URI THEFT;
	public static final URI TRESPASS;
	public static final URI VANDALISM;
	public static final URI VEHICLE_THEFT;
	public static final URI WARRANTS;
	public static final URI WEAPON_LAWS;

	static {
		ValueFactory factory = ValueFactoryImpl.getInstance();

		CRIME_EVENT = factory.createURI(NAMESPACE, "CrimeEvent");

		GEOHASH = factory.createURI(NAMESPACE, "geohash");

		IMPACT = factory.createURI(NAMESPACE, "impact");

		ARSON = factory.createURI(NAMESPACE, "Arson");

		ASSAULT = factory.createURI(NAMESPACE, "Assault");

		BAD_CHECKS = factory.createURI(NAMESPACE, "BadChecks");

		BRIBERY = factory.createURI(NAMESPACE, "Bribery");

		BURGLARY = factory.createURI(NAMESPACE, "Burglary");

		COUNTERFEITING = factory.createURI(NAMESPACE, "Counterfeiting");

		DISORDERLY_CONDUCT = factory.createURI(NAMESPACE, "DisorderlyConduct");

		DRIVING_UNDER_THE_INFLUENCE = factory.createURI(NAMESPACE,
				"DrivingUnderTheInfluence");

		DRUG = factory.createURI(NAMESPACE, "Drug");

		DRUNKENNESS = factory.createURI(NAMESPACE, "Drunkenness");

		DUI = factory.createURI(NAMESPACE, "DUI");

		EMBEZZLEMENT = factory.createURI(NAMESPACE, "Embezzlement");

		EXTORTION = factory.createURI(NAMESPACE, "Extortion");

		FAMILY_OFFENSES = factory.createURI(NAMESPACE, "FamilyOffenses");

		FORGERY = factory.createURI(NAMESPACE, "Forgery");

		FRAUD = factory.createURI(NAMESPACE, "Fraud");

		GAMBLING = factory.createURI(NAMESPACE, "Gambling");

		KIDNAPPING = factory.createURI(NAMESPACE, "Kidnapping");

		LARCENY = factory.createURI(NAMESPACE, "Larceny");

		LIQUOR_LAWS = factory.createURI(NAMESPACE, "LiquorLaws");

		LOITERING = factory.createURI(NAMESPACE, "Loitering");

		MISSING_PERSON = factory.createURI(NAMESPACE, "MissingPerson");

		NARCOTIC = factory.createURI(NAMESPACE, "Narcotic");

		NON_CRIMINAL = factory.createURI(NAMESPACE, "NonCriminal");

		OBSCENE_MATERIAL = factory.createURI(NAMESPACE, "ObsceneMaterial");

		OTHER_OFFENSES = factory.createURI(NAMESPACE, "OtherOffenses");

		PORNOGRAPHY = factory.createURI(NAMESPACE, "Pornography");

		PROSTITUTION = factory.createURI(NAMESPACE, "Prostitution");

		ROBBERY = factory.createURI(NAMESPACE, "Robbery");

		RUNAWAY = factory.createURI(NAMESPACE, "Runaway");

		SEXUAL_OFFENSE = factory.createURI(NAMESPACE, "SexualOffence");

		STOLEN_PROPERTY = factory.createURI(NAMESPACE, "StolenProperty");

		SUICIDE = factory.createURI(NAMESPACE, "Suicide");

		SUSPICIOUS_OCCURRENCE = factory.createURI(NAMESPACE,
				"SuspiciousOccurrence");

		THEFT = factory.createURI(NAMESPACE, "Theft");

		TRESPASS = factory.createURI(NAMESPACE, "Trespass");

		VANDALISM = factory.createURI(NAMESPACE, "Vandalism");

		VEHICLE_THEFT = factory.createURI(NAMESPACE, "VehicleTheft");

		WARRANTS = factory.createURI(NAMESPACE, "Warrants");

		WEAPON_LAWS = factory.createURI(NAMESPACE, "WeaponLaws");
	}
}
