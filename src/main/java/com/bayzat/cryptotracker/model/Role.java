package com.bayzat.cryptotracker.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseNamedEntity{
    @ManyToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(String name) {
        super(name);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    //todo equals&&hashcode
}
