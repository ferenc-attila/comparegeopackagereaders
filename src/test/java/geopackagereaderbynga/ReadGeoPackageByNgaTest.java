package geopackagereaderbynga;

import org.junit.jupiter.api.RepeatedTest;
import pojo.Location;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadGeoPackageByNgaTest {

    ReadGeoPackageByNga readGeoPackageByNga = new ReadGeoPackageByNga("src/test/resources/ne_10m.gpkg");

    @RepeatedTest(10)
    void getLayersTest() {
        List<String> layers = List.of("ne_10m_populated_places", "ne_10m_roads", "ne_10m_countries");
        assertEquals(layers, readGeoPackageByNga.getLayers());
    }

    @RepeatedTest(10)
    void getPointFeaturesTest() {
        List<Location> result = readGeoPackageByNga.getAllFeatures("ne_10m_populated_places", "NAME", "POP_MAX");
        assertEquals(7342, result.size());
        assertEquals("Colonia del Sacramento", result.get(0).getName());
        assertEquals("POINT (-56.9009966 -33.5439989)", result.get(1).getWktGeometry());
        assertEquals(4326, result.get(2).getSrsId());
        assertEquals(15L, result.get(14).getId());
    }

    @RepeatedTest(10)
    void getLineFeaturesTest() {
        List<Location> result = readGeoPackageByNga.getAllFeatures("ne_10m_roads", "type", "length_km");
        assertEquals(56600, result.size());
        assertEquals("Secondary Highway", result.get(0).getName());
        assertEquals("LINESTRING (-72.02527881837443 44.413879210545, -72.02875200735551 44.41735239952607)", result.get(1223).getWktGeometry());
        assertEquals(4326, result.get(2).getSrsId());
        assertEquals(56600L, result.get(56599).getId());
    }

    @RepeatedTest(10)
    void getPolygonFeaturesTest() {
        List<Location> result = readGeoPackageByNga.getAllFeatures("ne_10m_countries", "NAME_LONG", "POP_EST");
        assertEquals(258, result.size());
        assertEquals("Indonesia", result.get(0).getName());
        assertEquals("POLYGON ((7.43745403212602 43.74336054083465, 7.432845087000089 43.739852548000044, 7.41795688000007", result.get(120).getWktGeometry().substring(0,100));
        assertEquals(2125268, result.get(145).getPopulation());
        assertEquals(4326, result.get(2).getSrsId());
        assertEquals(258L, result.get(257).getId());
    }
}