package org.georepublic.services.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import org.georepublic.properties.*;

import io.swagger.jaxrs.config.BeanConfig;

public class GeocoderApplication extends Application {

    public GeocoderApplication() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        //beanConfig.setHost("localhost:8002");
        beanConfig.setBasePath("/geocoderService/service/api");
        beanConfig.setResourcePackage("io.swagger.resources");
        beanConfig.setScan(true);
    }
    
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add( GeocoderRestAdminService.class );

		//::::::::::::::::::::::::::
        //: Setting Swagger classes
        //::::::::::::::::::::::::::
		classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        
		//::::::::::::::::::::::::::
		//: Setting the properties
		//::::::::::::::::::::::::::
		DBProperties.setProperties();
		
		return classes;
	}

}
