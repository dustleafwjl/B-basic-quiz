package com.thoughtworks.gtb.basicquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private long id;
    private long userId;
    private long year;
    private String title;
    private String description;
}
