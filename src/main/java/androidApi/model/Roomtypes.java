package androidApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Roomtypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int room_id;
    private String type;
    private int number_of_rooms;
    private double price;
    private int air_conditioning;
    private int fridge;
    private int balcony;
    private int internet_cable;


    @JsonIgnore
    public Accomodations getAccomodations() {
        return accomodation;
    }

    public void setAccomodation(Accomodations accomodation) {
        this.accomodation = accomodation;
    }

    @JsonIgnore
    @ManyToOne
    private Accomodations accomodation;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber_of_rooms() {
        return number_of_rooms;
    }

    public void setNumber_of_rooms(int number_of_rooms) {
        this.number_of_rooms = number_of_rooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAir_conditioning() {
        return air_conditioning;
    }

    public void setAir_conditioning(int air_conditioning) {
        this.air_conditioning = air_conditioning;
    }

    public int getFridge() {
        return fridge;
    }

    public void setFridge(int fridge) {
        this.fridge = fridge;
    }

    public int getBalcony() {
        return balcony;
    }

    public void setBalcony(int balcony) {
        this.balcony = balcony;
    }

    public int getInternet_cable() {
        return internet_cable;
    }

    public void setInternet_cable(int internet_cable) {
        this.internet_cable = internet_cable;
    }

}
