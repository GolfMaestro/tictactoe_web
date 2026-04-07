package org.bygolf.web.dto;

import org.bygolf.web.model.WebUser;

import java.util.UUID;

public class GetGameDto {

    private UUID id;
    private int[][] gameField;
    private Integer playerIcon;
    private WebUser host;
    private WebUser member;

    public GetGameDto(UUID id, int[][] gameField, Integer playerIcon, WebUser host, WebUser member) {
        this.id = id;
        this.gameField = gameField;
        this.playerIcon = playerIcon;
        this.host = host;
        this.member = member;
    }

    public GetGameDto(UUID id, int[][] gameField) {
        this.id = id;
        this.gameField = gameField;
        this.playerIcon = null;
    }

    public GetGameDto() {
    }

    public UUID getId() {
        return id;
    }

    public int[][] getGameField() {
        return gameField;
    }

    public Integer getPlayerIcon() {
        return playerIcon;
    }

    public WebUser getHost() {
        return host;
    }

    public WebUser getMember() {
        return member;
    }

}