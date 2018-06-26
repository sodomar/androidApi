package androidApi.model;

import javax.persistence.*;

@Entity
public class View_Cities {

    @Id
    private int id;

    @Column(name="city_name")
    private String city_name;

    @Column(name="country_code")
    private String country_code;


    public View_Cities(String city_name, String country_code) {
        this.city_name =  city_name;
        this.country_code = country_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}