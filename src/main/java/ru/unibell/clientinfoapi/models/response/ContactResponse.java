package ru.unibell.clientinfoapi.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.unibell.clientinfoapi.models.dto.ContactDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {

    private List<ContactDto> contacts;
}
