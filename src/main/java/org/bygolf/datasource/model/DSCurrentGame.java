package org.bygolf.datasource.model;

import jakarta.persistence.*;
import org.bygolf.domain.model.GameStatus;
import org.bygolf.domain.model.GameType;

import java.util.UUID;

@Entity
@Table(name = "current_games")
public class DSCurrentGame {

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_field_id")
    private DSGameField DSgameField;

    @Column
    private GameStatus gameStatus;

    @Column
    private GameType gameType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    private DSUser host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = true)
    private DSUser member;

    public DSCurrentGame() {
    }

    public DSCurrentGame(UUID id, DSGameField DSgameField, GameStatus gameStatus, GameType gameType, DSUser host, DSUser member) {
        this.id = id;
        this.DSgameField = DSgameField;
        this.gameStatus = gameStatus;
        this.gameType = gameType;
        this.host = host;
        this.member = member;
    }

    public UUID getId() {
        return id;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public DSUser getHost() {
        return host;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setHost(DSUser host) {
        this.host = host;
    }

    public DSUser getMember() {
        return member;
    }

    public void setMember(DSUser member) {
        this.member = member;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setDSGameField(DSGameField DSgameField) {
        this.DSgameField = DSgameField;
    }

    public DSGameField getDSgameField() {
        return DSgameField;
    }
}
