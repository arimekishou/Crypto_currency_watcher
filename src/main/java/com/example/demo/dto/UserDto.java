package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.demo.entities.Crypto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends RepresentationModel<UserDto> {
    @Null
    private Long id;
    @NotNull
    private String userName;
    @NotNull
    private Crypto crypto;
}
