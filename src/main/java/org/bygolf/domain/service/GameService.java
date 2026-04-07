package org.bygolf.domain.service;

import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.domain.model.GameStatus;
import org.bygolf.domain.model.GameType;
import org.bygolf.web.dto.CurrentGamesDto;
import org.bygolf.web.dto.GetGameDto;
import org.bygolf.web.model.WebCurrentGame;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface GameService {

    int[][] nextTurn(int[][] gameField);
    boolean isCorrectGame(int[][] sourceGameField, int[][] newGameField, GameStatus whichTurn);
    Integer isGameEnds(GameField gameField);
    void saveGame(CurrentGame currentGame);
    CurrentGame getGameById(UUID id);
    List<CurrentGame> getByGameStatus(GameStatus gameStatus);
    int getUserInfo(UUID id);
    WebCurrentGame createNewGame(Principal principal, GameType gameType);
    GetGameDto getGameDtoById(Principal principal, UUID id);
    List<CurrentGamesDto> getOpenGames();
    WebCurrentGame joinGame(Principal principal, UUID gameId);
    WebCurrentGame turn(UUID gameId, WebCurrentGame webCurrentGameInput, Principal principal);

}
