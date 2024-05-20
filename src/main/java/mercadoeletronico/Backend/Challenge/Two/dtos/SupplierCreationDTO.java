package mercadoeletronico.Backend.Challenge.Two.dtos;

import jakarta.validation.constraints.NotNull;
import mercadoeletronico.Backend.Challenge.Two.domain.supplier.State;
import mercadoeletronico.Backend.Challenge.Two.domain.supplier.SupplierType;

import java.util.List;

public class SupplierCreationDTO {

    public String userId;
    @NotNull
    public String name;
    @NotNull
    public SupplierType type;
    @NotNull
    public String document;
    @NotNull
    public String contactName;
    @NotNull
    public String contactEmail;
    @NotNull
    public List<String> phoneNumbers;
    @NotNull
    public String activitiesDescription;
    @NotNull
    public String zipCode;
    @NotNull
    public String street;
    @NotNull
    public String number;
    public String complement;
    @NotNull
    public String neighborhood;
    @NotNull
    public String city;
    @NotNull
    public State state;

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId) {
        this.userId = userId;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull SupplierType getType() {
        return type;
    }

    public void setType(@NotNull SupplierType type) {
        this.type = type;
    }

    public @NotNull String getDocument() {
        return document;
    }

    public void setDocument(@NotNull String document) {
        this.document = document;
    }

    public @NotNull String getContactName() {
        return contactName;
    }

    public void setContactName(@NotNull String contactName) {
        this.contactName = contactName;
    }

    public @NotNull String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(@NotNull String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public @NotNull List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(@NotNull List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public @NotNull String getActivitiesDescription() {
        return activitiesDescription;
    }

    public void setActivitiesDescription(@NotNull String activitiesDescription) {
        this.activitiesDescription = activitiesDescription;
    }

    public @NotNull String getZipCode() {
        return zipCode;
    }

    public void setZipCode(@NotNull String zipCode) {
        this.zipCode = zipCode;
    }

    public @NotNull String getStreet() {
        return street;
    }

    public void setStreet(@NotNull String street) {
        this.street = street;
    }

    public @NotNull String getNumber() {
        return number;
    }

    public void setNumber(@NotNull String number) {
        this.number = number;
    }

    public @NotNull String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(@NotNull String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public @NotNull String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public @NotNull State getState() {
        return state;
    }

    public void setState(@NotNull State state) {
        this.state = state;
    }
}
