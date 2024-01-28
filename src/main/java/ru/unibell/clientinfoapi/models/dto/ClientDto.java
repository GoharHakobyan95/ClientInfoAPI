package ru.unibell.clientinfoapi.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Long clientId;

    @NotBlank(message = "Name can't be empty.")
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank(message = "Surname can't be empty.")
    @Size(min = 2, max = 20)
    private String lastName;
}
