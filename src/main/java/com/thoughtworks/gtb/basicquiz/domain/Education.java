package com.thoughtworks.gtb.basicquiz.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private long id;
    private long userId;
    private long year;
    @NotNull
    private String title;
    @NotNull
    private String description;
}
