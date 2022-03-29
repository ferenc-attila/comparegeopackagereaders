package bothsolutionssameresulttest;

import geopackagereaderbygeotools.ReadGeoPackageByGeoTools;
import geopackagereaderbynga.ReadGeoPackageByNga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BothSolutionsSameResultTest {

    ReadGeoPackageByNga readGeoPackageByNga = new ReadGeoPackageByNga("src/test/resources/locations.gpkg");
    ReadGeoPackageByGeoTools readGeoPackageByGeoTools = new ReadGeoPackageByGeoTools("src/test/resources/locations.gpkg");

    @Test
    void sameResultTest() {
        assertEquals(readGeoPackageByNga.getAllFeatures("locations").toString(), readGeoPackageByGeoTools.getAllFeatures("locations").toString());
    }
}
