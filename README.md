# Experiences in reading geospatial data from GeoPackage with Java
## Comparison of two Java geospatial tools

## Introduction

[GeoPackage](http://www.geopackage.org/) is a popular format in open source GIS, especially since the
worthily widespread QGIS selected it as the default vector format in version 3.8. GeoPackage is a relatively
young format, as it was published in 2014. It is a platform-independent database, implemented as an SQLite
container, which can store
- vector features
- tile matrix sets of imagery and raster maps
- attributes (non-spatial data)
- extensions.

There are many reasons why GIS users like storing their data in GeoPackage, and there are lots of use cases
when this isn't the best. However, discussing it is not the goal now.

As a QGIS user, I like to use GeoPackages. As a junior Java developer, I  spend much time elaborating to use
it simply in my codes. Java is not the best software language when you want to develop GIS-based
applications. All major GIS software (e.g. QGIS, ESRI ArcGIS) use Python scripts. GIS experts nowadays use
Python to manipulate their datasets. Nevertheless, Java is a great language to develop server-side, backend
applications, where, sometimes, you have to work with GIS data. Sometimes it is worth it to handle
GeoPackage effectively in your server. In my case, I have to import data from GeoPackage into a PostGIS
database regularly.

## GeoTools GeoPackage toolkit

A mature open source project. The first version was released in 1996. In 2007 the
[GeoTools](https://geotools.org/) Project Management Committee joined the
[Open Source Geospatial Foundation](https://www.osgeo.org/) (OSGeo). As they are defined themselves, the
GeoTools Java code library provides standards compliant methods for manipulating geospatial data. The
library's core features are:
- Definition of interfaces for key spatial concepts and data structures
- A clean data access API supporting feature access, transaction support
- A stateless, low memory renderer
- Schema assisted parsing technology

The library supports a wide range of raster (arcgrid, GeoTIFF, jpeg, MatLab, etc.) and vector (GeoPackage, 
PostGIS, shape, MySQL, geojson, csv, etc.) formats. It provides support for numerous
[Open Geospatial Consortium](https://www.ogc.org/) standards.

The library was released under the LGPL license.

## NGA GeoPackage artefact

The [National Geospatial-Intelligence Agency](https://www.nga.mil/) (NGA) is a governmental institute in the
United States which provides, among others, maritime safety products, historical maps, small business
resources.

It provides the ability to read, create and modify GeoPackage files. As you can see, it's specifically
written for GeoPackage manipulation. Your possibilities are narrower using this artefact, as it cannot
handle other GIS databases or files, didn't implement more functions.

It was released under MIT license.

## Method

I implemented three classes of each library to read a point, a line and a polygon layer from the same
gpkg. These classes create POJO instances. I created repeated test cases with Junit5 to measure the running
time. I didn't make a deep statistical analysis nor monitored the computer resources.

### Environment

I run test cases on my desktop computer:
- Intel(R) Core(TM) i7-7740X CPU
- 16,0 GB RAM
- SSD

I used the software below: 
- Windows 10 21H2 64 bit
- Oracle OpenJDK 17.0.2
- Apache Maven 3.8.4
- IntelliJ Idea Community Edition 2021.3.3
- QGIS 3.22.3 (1628765ec7) for data preparation

### Dataset

I used three layers from the 1:10M [Natural Earth Data](http://www.naturalearthdata.com/downloads/),
downloaded on 30.03.2022. The point layer is the cities, the line layer is the roads, the polygon layer
is the countries. I kept only a text and an integer field for each layer. I exported the prepared data with
QGIS to another 

![](img/ne_10m_map.jpeg "Map of dataset")

## Experiences

The documentation was quite enough for me to understand the basics in both cases. Each tool has Javadoc.
The GeoTools library has a long and detailed tutorial, the NGA's one has a short but clear readme markdown
in their git repository.
The logic of the implementation is the same in case of both libraries, which is similar to Java JDBC. You
have to create a connection, a statement contains a query, than you get a result set.
The NGA's library has much lesser dependency.

__Konkrét számadatok kellenek__


None of them used the AutoClosable interface, therefore you can't implement the methods in try-with-resources
statement. The GeoTools implementation a little more complicated. You will meet a lot of checked exceptions.
That's why reader class implemented with the NGA artefact is much shorter and the code is more readable.
In the code I prepared POJO for the three layers. These have integer attributes for the coordinate reference
system, storing the [EPSG](https://epsg.org/home.html) identifier. The geometry stored as
[WKT string](http://wiki.gis.com/wiki/index.php/Well-known_text), and certainly all of them have a long id
and also a String and an integer attributes for the common fields in the layers.

The repeated test cases based on NGA implementation's classes run much faster on my computer as you can see 
on the image below:

## Summary

Both of the libraries are great, well documented and easy to understand the usage. Often the less is more.
The NGA's simple tool is easier to use, faster, and has less dependency. If I need to read GeoPackage layer
I will use the NGA's library. If I have to use other tools (I have to handle other formats, databases etc.),
I will use the slower, more complicated GeoTools library in my code. 
