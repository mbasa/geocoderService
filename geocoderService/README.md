GeocoderService
===============

Web Service front-end for pgGeocoder

### Build
Use Maven to create a WAR file by:

```
  mvn clean install
```

### Usage
For JSON output

```
http://localhost:8080/geocoderService/service/json/<Address>
```

For GeoJSON output

```
http://localhost:8080/geocoderService/service/geojson/<Address>
```
  