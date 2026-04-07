package org.bygolf.domain.model;

import java.util.UUID;

public class CurrentGame {

    private UUID id;

    private GameField gameField;

    private GameStatus gameStatus;

    private GameType gameType;

    private User host;

    private User member;

    public CurrentGame() {
    }

    public CurrentGame(GameType gameType) {
        this.id = UUID.randomUUID();
        this.gameField = new GameField();
        this.gameStatus = GameStatus.OPEN;
        this.gameType = gameType;
    }

    public CurrentGame(UUID id, GameField gameField) {
        this.id = id;
        this.gameField = gameField;
    }


    public CurrentGame(UUID id, GameField gameField, GameStatus gameStatus, GameType gameType, User host, User member) {
        this.id = id;
        this.gameField = gameField;
        this.gameStatus = gameStatus;
        this.gameType = gameType;
        this.host = host;
        this.member = member;
    }

    public UUID getId() {
        return id;
    }

    public GameField getGameField() {
        return gameField;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public User getHost() {
        return host;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }
}
