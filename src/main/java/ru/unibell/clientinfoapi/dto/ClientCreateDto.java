package ru.unibell.clientinfoapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateDto {


    @NotBlank(message = "Name can't be empty.")
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank(message = "Surname can't be empty.")
    @Size(min = 2, max = 20)
    private String lastName;
}
