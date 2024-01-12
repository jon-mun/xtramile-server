package com.jonmun.xtramile_server.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStudentDto {

    @Valid

    @NotNull(message = "`id` cannot be null")
    @NotBlank(message = "`id` cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "`id` must be alphanumeric")
    private String id;

    @NotNull(message = "`firstName` cannot be null")
    @NotBlank(message = "`firstName` cannot be blank")
    private String firstName;

    private String lastName;

    @NotNull(message = "`dob` cannot be null")
    @NotBlank(message = "`dob` cannot be blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "`dob` must be in the format yyyy-MM-dd")
    private String dob;

}
