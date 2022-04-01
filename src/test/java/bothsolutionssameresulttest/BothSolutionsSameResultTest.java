package bothsolutionssameresulttest;

import geopackagereaderbygeotools.ReadGeoPackageByGeoTools;
import geopackagereaderbynga.ReadGeoPackageByNga;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BothSolutionsSameResultTest {

    ReadGeoPackageByNga readGeoPackageByNga = new ReadGeoPackageByNga("src/test/resources/ne_10m.gpkg");
    ReadGeoPackageByGeoTools readGeoPackageByGeoTools = new ReadGeoPackageByGeoTools("src/test/resources/ne_10m.gpkg");

    @RepeatedTest(1000)
    void sameResultTest() {
        assertEquals(readGeoPackageByNga.getAllFeatures("ne_10m_populated_places").toString(), readGeoPackageByGeoTools.getAllFeatures("ne_10m_populated_places").toString());
    }
}
