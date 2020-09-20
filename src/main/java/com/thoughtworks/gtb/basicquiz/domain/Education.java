package com.thoughtworks.gtb.basicquiz.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @JsonIgnore
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    private long year;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "描述不能为空")
    private String description;
}
