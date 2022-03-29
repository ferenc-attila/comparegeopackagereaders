package geopackagereaderbynga;

import org.junit.jupiter.api.Test;
import pojo.Location;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadGeoPackageByNgaTest {

    ReadGeoPackageByNga readGeoPackageByNga = new ReadGeoPackageByNga("src/test/resources/locations.gpkg");

    @Test
    void getLayersTest() {
        List<String> layers = List.of("locations");
        assertEquals(layers, readGeoPackageByNga.getLayers());
    }

    @Test
    void getFeatureTableTest() {
        List<Location> result = readGeoPackageByNga.getAllFeatures("locations");
        assertEquals(15, result.size());
        assertEquals("Trento", result.get(0).getName());
        assertEquals("POINT (-93.0852 44.9441)", result.get(1).getWktGeometry());
        assertEquals(4326, result.get(2).getSrsId());
        assertEquals(15L, result.get(14).getId());
    }
}