package pojo;

public class Location {

    private Long id;
    private int srsId;
    private String wktGeometry;
    private String name;
    private int population;

    public Location(int srsId, String wktGeometry, String name, int population) {
        this.srsId = srsId;
        this.wktGeometry = wktGeometry;
        this.name = name;
        this.population = population;
    }

    public Location(Long id, int srsId, String wktGeometry, String name, int population) {
        this(srsId, wktGeometry, name, population);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", srsId=" + srsId +
                ", wktGeometry='" + wktGeometry + '\'' +
                ", name='" + name + '\'' +
                ", population=" + population +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSrsId() {
        return srsId;
    }

    public void setSrsId(int srsId) {
        this.srsId = srsId;
    }

    public String getWktGeometry() {
        return wktGeometry;
    }

    public void setWktGeometry(String wktGeometry) {
        this.wktGeometry = wktGeometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
