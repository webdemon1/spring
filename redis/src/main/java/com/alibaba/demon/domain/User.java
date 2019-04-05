package com.alibaba.demon.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author: Demon
 * @create: 2019-04-05
 **/
@Builder
@AllArgsConstructor
@Getter
public class User implements Serializable{

    private static final long serialVersionUID = -2850548580296663142L;

    private Long id;

    private String name;

    private String gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender);
    }
}
