package geopackagereaderbygeotools;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pojo.Location;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadGeoPackageByGeoToolsTest {

    ReadGeoPackageByGeoTools readGeoPackageByGeoTools = new ReadGeoPackageByGeoTools("src/test/resources/ne_10m.gpkg");

    @RepeatedTest(1000)
    void getLayersTest() {
        List<String> layers = List.of("ne_10m_countries", "ne_10m_populated_places", "ne_10m_roads");
        assertEquals(layers, readGeoPackageByGeoTools.getLayers());
    }

    @RepeatedTest(1000)
    void getAllFeaturesTest() {
        List<Location> result = readGeoPackageByGeoTools.getAllFeatures("ne_10m_populated_places");
        assertEquals(7342, result.size());
        assertEquals("Colonia del Sacramento", result.get(0).getName());
        assertEquals("POINT (-56.9009966 -33.5439989)", result.get(1).getWktGeometry());
        assertEquals(4326, result.get(2).getSrsId());
        assertEquals(15L, result.get(14).getId());
    }
}