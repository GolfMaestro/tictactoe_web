package org.bygolf.datasource.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class DSUser {

    @Id
    private UUID id;

    @Column
    private String login;

    @Column
    private String password;

    public DSUser() {
    }

    public DSUser(String login, String password) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
    }

    public DSUser(UUID id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
