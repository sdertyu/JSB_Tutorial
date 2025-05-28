package com.pngo.crudProj.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdate {
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;


}
