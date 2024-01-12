package com.jonmun.xtramile_server.domain.dto;

import jakarta.validation.Valid;
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

    private String dob;

}
