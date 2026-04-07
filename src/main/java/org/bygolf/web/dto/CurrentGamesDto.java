package org.bygolf.web.dto;

import java.util.UUID;

public class CurrentGamesDto {

    private UUID id;

    public CurrentGamesDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
