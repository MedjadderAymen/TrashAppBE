package dz.trash.TrashBackend.Model;

import java.util.Date;

public class ChallengeForm {
    private int id_challenge;
    private Date creation_date ;
    private int state;
    private Date starting_date;
    private Date ending_date ;
    private float latitude;
    private float longitude;
    private String street;
    private String city ;
    private String zip_code ;
    private String country;
    private Client owner;

    public int getId_challenge() {
        return id_challenge;
    }

    public void setId_challenge(int id_challenge) {
        this.id_challenge = id_challenge;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(Date starting_date) {
        this.starting_date = starting_date;
    }

    public Date getEnding_date() {
        return ending_date;
    }

    public void setEnding_date(Date ending_date) {
        this.ending_date = ending_date;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
}
