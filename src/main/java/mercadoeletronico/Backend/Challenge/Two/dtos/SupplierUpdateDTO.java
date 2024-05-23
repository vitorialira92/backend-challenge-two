package mercadoeletronico.Backend.Challenge.Two.dtos;

import jakarta.validation.constraints.NotNull;
import mercadoeletronico.Backend.Challenge.Two.domain.supplier.State;

import java.util.List;

public class SupplierUpdateDTO {
    @NotNull
    public String activitiesDescription;
    @NotNull
    public String contactName;
    @NotNull
    public String contactEmail;
    @NotNull
    public List<String> phoneNumbers;
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

    public @NotNull String getActivitiesDescription() {
        return activitiesDescription;
    }

    public void setActivitiesDescription(@NotNull String activitiesDescription) {
        this.activitiesDescription = activitiesDescription;
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

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public @NotNull String getNeighborhood() {
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
