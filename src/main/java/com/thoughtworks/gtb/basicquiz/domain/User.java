package com.thoughtworks.gtb.basicquiz.domain;

import com.thoughtworks.gtb.basicquiz.utils.BtyesSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
    @BtyesSize(min = 1, max = 128)
    private String name;
    @Min(17)
    private long age;

    @BtyesSize(min = 0, max = 512)
    @NotNull(message = "用户头像不能为空")
    private String avatar;

    @BtyesSize(min = 8, max = 1024, message = "描述字段格式不正确")
    private String description;

    @OneToMany
    private List<Education> educations;
}
