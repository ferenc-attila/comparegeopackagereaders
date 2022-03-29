# Compare two GeoPackage Java implementations

## Introduction

GeoPackage is a popular format in open source GIS, especially since when the worthily widespread QGIS
selected it as default vector format in version 3.8. GeoPackage is relatively young format, as it was
published in 2014. It is a platform independent database, implemented as an SQLite container, which can store
- vector features
- tile matrix sets of imagery and raster maps
- attributes (non-spatial data)
- extensions.

There are many reasons why GIS users like storing their data in GeoPackage, and there are lots of use cases
when this isn't the best. However, discussing it is not the goal now.

As a QGIS user, I like use GeoPackages. As a junior Java developer, I had to spent some time to figure out,
how to use it simply in my codes. Java is not the best software language when you want to develop GIS based
applications. All the major GIS software (e.g. QGIS, ESRI ArcGIS) use python scripts. If you want to use GIS,
you have to learn Python. Nevertheless, Java is a really great language to develop server-side, backend
applications, where, sometimes, you have to work with GIS data. So, sometimes it is worth to handle GeoPackage
effectively in your server. In my case I have to import data from GeoPackage into a PostGIS database regularly.

## GeoTools GeoPackage toolkit

## NGA GeoPackage artifact

## Method

## Experiences

## Summary