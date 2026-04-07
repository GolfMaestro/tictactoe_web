package org.bygolf.web.model;

import org.bygolf.domain.model.GameStatus;

import java.util.UUID;

public class WebCurrentGame {

    private UUID id;
    private int[][] gameField;
    private Integer playerIcon;
    private UUID hostId;
    private UUID memberId;
    private GameStatus gameStatus;

    public WebCurrentGame(UUID id, int[][] gameField, Integer playerIcon) {
        this.id = id;
        this.gameField = gameField;
        this.playerIcon = playerIcon;
    }

    public WebCurrentGame(UUID id, int[][] gameField) {
        this.id = id;
        this.gameField = gameField;
        this.playerIcon = null;
    }

    public WebCurrentGame(UUID id, int[][] gameField, UUID hostId, UUID memberId, GameStatus gameStatus) {
        this.id = id;
        this.gameField = gameField;
        this.hostId = hostId;
        this.memberId = memberId;
        this.gameStatus = gameStatus;
    }

    public WebCurrentGame() {
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

    public UUID getHostId() {
        return hostId;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setPlayerIcon(Integer playerIcon) {
        this.playerIcon = playerIcon;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
