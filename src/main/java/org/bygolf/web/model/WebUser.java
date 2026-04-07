package org.bygolf.web.model;

import java.util.UUID;

public class WebUser {

    private UUID id;

    public WebUser(UUID id) {
        this.id = id;
    }

    public WebUser() {
    }

    public UUID getId() {
        return id;
    }

}
