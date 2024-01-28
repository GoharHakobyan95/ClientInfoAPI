package ru.unibell.clientinfoapi.models.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("PHONE")
@Getter
@Setter
public class PhoneContact extends Contact {

    @Override
    public String getContactType() {
        return ContactType.PHONE.name();
    }
}
