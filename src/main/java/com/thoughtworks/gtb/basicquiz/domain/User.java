package com.thoughtworks.gtb.basicquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    @NotNull(message = "用户名不能为空")
    private String name;
    private long age;
    @NotNull(message = "用户头像不能为空")
    private String avatar;
    private String description;
}
