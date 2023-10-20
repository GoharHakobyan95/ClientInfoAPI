package ru.unibell.clientinfoapi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.unibell.clientinfoapi.entity.Client;
import ru.unibell.clientinfoapi.entity.ContactType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponseDto {

    private Long contactId;

    @Enumerated(value = EnumType.STRING)
    private ContactType contactType;

    private Client client;

    private String value;
}
