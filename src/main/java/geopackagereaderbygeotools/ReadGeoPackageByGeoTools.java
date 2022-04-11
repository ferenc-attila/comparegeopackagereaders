package geopackagereaderbygeotools;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.simple.SimpleFeature;
import pojo.Location;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ReadGeoPackageByGeoTools {

    private Map<String, String> parametersOfGeoPackage = new HashMap<>();

    public ReadGeoPackageByGeoTools(String path) {
        this.parametersOfGeoPackage.put("dbtype", "geopkg");
        this.parametersOfGeoPackage.put("database", path);
    }

    public List<String> getLayers() {
        DataStore dataStore = getDataStore();
        try {
            return List.of(dataStore.getTypeNames());
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot get layernames!", ioe);
        } finally {
            dataStore.dispose();
        }
    }

    public List<Location> getAllFeatures(String layerName, String textFieldName, String integerFieldName) {
        DataStore dataStore = getDataStore();
        SimpleFeatureSource featureSource = getFeatureSource(dataStore, layerName);
        SimpleFeatureCollection featureCollection = getFeatureCollection(featureSource);
        SimpleFeatureIterator iterator = featureCollection.features();
        return getLocationFeatures(dataStore, iterator, textFieldName, integerFieldName);
    }

    private List<Location> getLocationFeatures(DataStore dataStore, SimpleFeatureIterator iterator, String textFieldName, String integerFieldName) {
        List<Location> locations = new ArrayList<>();
        try (iterator) {
            while (iterator.hasNext()) {
                getLocations(iterator, locations, textFieldName, integerFieldName);
            }
            dataStore.dispose();
        }
        return locations;
    }

    private void getLocations(SimpleFeatureIterator iterator, List<Location> locations, String textFieldName, String integerFieldName) {
        SimpleFeature feature = iterator.next();
        Location actual = new Location(
                Long.parseLong(feature.getID().substring(feature.getID().indexOf('.') + 1)),
                getSrsId(feature),
                feature.getDefaultGeometry().toString(),
                feature.getAttribute(textFieldName).toString(),
                new BigDecimal(feature.getAttribute(integerFieldName).toString()).intValue());
        locations.add(actual);
    }

    private int getSrsId(SimpleFeature feature) {
        String srsString = feature.getType().getCoordinateReferenceSystem().getIdentifiers().toString()
                .replace("[", "")
                .replace("]", "");
        String[] srs = srsString.split(":");
        return Integer.parseInt(srs[1]);
    }

    private SimpleFeatureCollection getFeatureCollection(SimpleFeatureSource featureSource) {
        try {
            return featureSource.getFeatures();
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot create feature collection!", ioe);
        }
    }

    private SimpleFeatureSource getFeatureSource(DataStore dataStore, String layerName) {
        try {
            return dataStore.getFeatureSource(layerName);
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot get feature source!", ioe);
        }
    }

    private DataStore getDataStore() {
        try {
            return DataStoreFinder.getDataStore(parametersOfGeoPackage);
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot create datastore!", ioe);
        }
    }
}
