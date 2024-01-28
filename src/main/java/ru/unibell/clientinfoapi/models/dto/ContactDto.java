package ru.unibell.clientinfoapi.models.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.unibell.clientinfoapi.models.entity.ContactType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    private Long contactId;

    @Enumerated(value = EnumType.STRING)
    private ContactType contactType;

    private String value;
}
