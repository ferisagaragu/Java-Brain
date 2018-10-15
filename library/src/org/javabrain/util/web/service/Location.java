package org.javabrain.util.web.service;

import org.javabrain.util.data.Json;

public class Location {

    private Json completeGeolocation;
    private double latitude;
    private double longitude;
    private String state;
    private String municipality;
    private String country;
    private String streetNumber;
    private String street;
    private String colony;
    private String postalCode;
    private String formatAddress;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFormatAddress() {
        return formatAddress;
    }

    public void setFormatAddress(String formatAddress) {
        this.formatAddress = formatAddress;
    }

    public Json getCompleteGeolocation() {
        return completeGeolocation;
    }

    public void setCompleteGeolocation(Json completeGeolocation) {
        this.completeGeolocation = completeGeolocation;
    }

    @Override
    public String toString() {
        return formatAddress;
    }
}
