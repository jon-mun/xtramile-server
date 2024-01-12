package com.jonmun.xtramile_server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStudentDto {

    private String NIM;

    private String fullName;

    private int age;

}
