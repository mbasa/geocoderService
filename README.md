GeocoderService
===============

Web Service front-end for pgGeocoder

![Alt text](img/screenshot.png?raw=true)


### Build
Use Maven to create a WAR file by:

```
  mvn clean install
```

### Test
Run the following command to test the application before deploying to Tomcat

```
mvn spring-boot:run
```

### View Swagger
To view the Swagger API List page

```
http://localhost:8080/geocoderService/
```

### Deploy

* **With an Application Server**

  Copy the created WAR file into the [TomcatDir]/webapps directory and start Tomcat. 
  Then edit the properties file for the correct database connection settings.

* **Stand-alone**

  Run the application as a stand-alone program by: 

```
  java -jar geocoderService.war
```

**NOTE:** 

For Stand-alone deployments, the following parameters can be modified via the 
command line arguments:

- Changing the default Port Number of `8080` to another port number:

```
java -jar -Dserver.port=8888 geocoderService.war
```

- Changing the default PostgreSQL Database URL:

```
java -jar -Dspring.datasource.url=jdbc:postgresql://localhost:5432/addresses2020 geocoderService.war
```

### Usage

* For Geocoder JSON output

```
http://localhost:8080/geocoderService/service/geocode/json/<Address or PlaceName>
```

* For Geocoder GeoJSON output

```
http://localhost:8080/geocoderService/service/geocode/geojson/<Address or PlaceName>
```

* For Reverse Geocoder JSON output

```
http://localhost:8080/geocoderService/service/reversegeocode/json/<Lon>,<Lat>
```

```
http://localhost:8080/geocoderService/service/reversegeocode/json/<Lon>,<Lat>,<Distance>
```


  