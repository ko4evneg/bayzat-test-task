package com.bayzat.cryptotracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseNamedEntity extends BaseEntity {
    @Column(name = "name")
    protected String name;

    public BaseNamedEntity(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseNamedEntity that = (BaseNamedEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
