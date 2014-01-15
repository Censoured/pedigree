package mpr.proj.pedigree;

public class Breeder {

    private long id;
    private String name;
    private Country country;

    public Breeder(long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
    
    @Override
    public String toString() {
            return String.format("%4d %60s %30s", this.id, this.name, this.country.getName());
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

}
