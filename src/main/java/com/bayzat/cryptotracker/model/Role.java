package com.bayzat.cryptotracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseNamedEntity{
    @ManyToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public Role(String name) {
        super(name);
    }

    //todo equals&&hashcode
}
