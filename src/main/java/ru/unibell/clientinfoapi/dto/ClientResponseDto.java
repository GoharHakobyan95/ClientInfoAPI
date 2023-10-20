package ru.unibell.clientinfoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClientResponseDto {

    private Long clientId;

    private String firstName;

    private String lastName;

}
