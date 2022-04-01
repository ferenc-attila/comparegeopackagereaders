package geopackagereaderbynga;

import mil.nga.geopackage.GeoPackage;
import mil.nga.geopackage.GeoPackageManager;
import mil.nga.geopackage.features.user.FeatureDao;
import mil.nga.geopackage.features.user.FeatureResultSet;
import mil.nga.geopackage.features.user.FeatureRow;
import mil.nga.geopackage.geom.GeoPackageGeometryData;
import pojo.Location;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadGeoPackageByNga {

    private GeoPackage geoPackage;

    public ReadGeoPackageByNga(String path) {
        this.geoPackage = GeoPackageManager.open(new File(path));
    }

    public List<String> getLayers() {
        return geoPackage.getFeatureTables();
    }

    public List<Location> getAllFeatures(String layerName) {
        List<Location> locations = new ArrayList<>();
        FeatureDao featureDao = geoPackage.getFeatureDao(layerName);
        iterateOnResultSet(locations, featureDao);
        return locations;
    }

    private void iterateOnResultSet(List<Location> locations, FeatureDao featureDao) {
        FeatureResultSet resultSet = featureDao.queryForAll();
        Iterator<FeatureRow> iterator = resultSet.iterator();
        try {
            while (iterator.hasNext()) {
                locations.add(getLocation(resultSet, iterator.next()));
            }
        } finally {
            resultSet.close();
        }
    }

    private Location getLocation(FeatureResultSet resultSet, FeatureRow row) {
        GeoPackageGeometryData geometry = row.getGeometry();
        return new Location(
                row.getId(),
                resultSet.getGeometry().getSrsId(),
                geometry.getWkt(),
                row.getValueString("NAME"),
                Integer.parseInt(row.getValueString("POP_MAX"))
        );
    }
}
