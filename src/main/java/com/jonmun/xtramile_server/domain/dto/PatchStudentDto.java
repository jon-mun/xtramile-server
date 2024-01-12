package com.jonmun.xtramile_server.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchStudentDto {

    @Valid

    private String firstName;

    private String lastName;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "`dob` must be in the format yyyy-MM-dd")
    private String dob;

}
