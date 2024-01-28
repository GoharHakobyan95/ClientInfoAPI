package ru.unibell.clientinfoapi.models.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("EMAIL")
@Getter
@Setter
public class EmailContact extends Contact {

    @Override
    public String getContactType() {
        return ContactType.EMAIL.name();
    }
}
