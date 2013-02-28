package org.georepublic.services.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import org.georepublic.properties.*;

public class GeocoderApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add( GeocoderRestAdminService.class );
		
		//::::::::::::::::::::::::::
		//: Setting the properties
		//::::::::::::::::::::::::::
		DBProperties.setProperties();
		
		return classes;
	}

}
