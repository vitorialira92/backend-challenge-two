package mercadoeletronico.Backend.Challenge.Two.dtos;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.State;
import mercadoeletronico.Backend.Challenge.Two.domain.supplier.SupplierType;

import java.util.List;

public class SupplierDTO {
    public String name;
    public SupplierType type;
    public String document;
    public String contactName;
    public String contactEmail;
    public List<String> phoneNumbers;
    public String activitiesDescription;
    public String zipCode;
    public String street;
    public String number;
    public String complement;
    public String neighborhood;
    public String city;
    public State state;

    public SupplierDTO(String name, SupplierType type, String document,
                       String contactName, String contactEmail,
                       List<String> phoneNumbers, String activitiesDescription,
                       String zipCode, String street, String number, String complement,
                       String neighborhood, String city, State state) {
        this.name = name;
        this.type = type;
        this.document = document;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.phoneNumbers = phoneNumbers;
        this.activitiesDescription = activitiesDescription;
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SupplierType getType() {
        return type;
    }

    public void setType(SupplierType type) {
        this.type = type;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getActivitiesDescription() {
        return activitiesDescription;
    }

    public void setActivitiesDescription(String activitiesDescription) {
        this.activitiesDescription = activitiesDescription;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
