package ru.unibell.clientinfoapi.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import ru.unibell.clientinfoapi.models.dto.ClientDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private List<ClientDto> clients;
    public ClientResponse(Page<ClientDto> page) {
        this.clients = page.getContent();
    }

}
