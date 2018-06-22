GeocoderService
===============

Web Service front-end for pgGeocoder

### Build
Use Maven to create a WAR file by:

```
  mvn clean install
```

### Test
Run the following command to test the application before deploying to Tomcat

```
mvn jstty:run
```

or to test the created WAR file

```
mvn jetty:run-war
```

### Deploy
Copy the created WAR file into the [TomcatDir]/webapps directory and start Tomcat. 
Then edit the properties file for the correct database connection settings.

### Usage
For Geocoder JSON output

```
http://localhost:8080/geocoderService/service/geocode/json/<Address>
```

For Geocoder GeoJSON output

```
http://localhost:8080/geocoderService/service/geocode/geojson/<Address>
```

For Reverse Geocoder JSON output

```
http://localhost:8080/geocoderService/service/reversegeocode/json/<Lon>,<Lat>
```

```
http://localhost:8080/geocoderService/service/reversegeocode/json/<Lon>,<Lat>,<Distance>
```
  