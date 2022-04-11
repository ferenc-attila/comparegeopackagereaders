package bothsolutionssameresulttest;

import geopackagereaderbygeotools.ReadGeoPackageByGeoTools;
import geopackagereaderbynga.ReadGeoPackageByNga;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BothSolutionsSameResultTest {

    ReadGeoPackageByNga readGeoPackageByNga = new ReadGeoPackageByNga("src/test/resources/ne_10m.gpkg");
    ReadGeoPackageByGeoTools readGeoPackageByGeoTools = new ReadGeoPackageByGeoTools("src/test/resources/ne_10m.gpkg");

    @RepeatedTest(1)
    void sameResultTest() {
        assertEquals(readGeoPackageByNga.getAllFeatures("ne_10m_populated_places", "NAME", "POP_MAX").toString(),
                readGeoPackageByGeoTools.getAllFeatures("ne_10m_populated_places", "NAME", "POP_MAX").toString());
//        assertEquals(readGeoPackageByNga.getAllFeatures("ne_10m_roads", "type", "length_km").toString(),
//                readGeoPackageByGeoTools.getAllFeatures("ne_10m_roads", "type", "length_km").toString());
//        assertEquals(readGeoPackageByNga.getAllFeatures("ne_10m_countries", "NAME_LONG", "POP_EST").toString(),
//                readGeoPackageByGeoTools.getAllFeatures("ne_10m_countries", "NAME_LONG", "POP_EST").toString());
    }
}
