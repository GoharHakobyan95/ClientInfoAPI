package ru.unibell.clientinfoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.unibell.clientinfoapi.entity.EmailContact;
import ru.unibell.clientinfoapi.entity.PhoneContact;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientContactsDto {

    private Long clientId;

    private String firstName;

    private String lastName;

    private List<PhoneContact> phones;

    private List<EmailContact> emails;

}
