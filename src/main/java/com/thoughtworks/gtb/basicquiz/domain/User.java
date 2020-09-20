package com.thoughtworks.gtb.basicquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private long id;
    @NotNull(message = "用户名不能为空")
    private String name;
    private long age;
    @NotNull(message = "用户头像不能为空")
    private String avatar;
    private String description;
}
