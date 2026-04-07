package org.bygolf.domain.service;

import org.bygolf.datasource.mapper.DSToDomain;
import org.bygolf.datasource.mapper.DomainToDS;
import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.datasource.repository.TicTacToeRepository;
import org.bygolf.domain.ai.BotAi;
import org.bygolf.domain.exceptions.*;
import org.bygolf.domain.model.*;
import org.bygolf.web.dto.CurrentGamesDto;
import org.bygolf.web.dto.GetGameDto;
import org.bygolf.web.mapper.DomainToWeb;
import org.bygolf.web.model.WebCurrentGame;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public class GameServiceImpl implements GameService {

    private final BotAi botAi;
    private final TicTacToeRepository ticTacToeRepository;
    private final DSToDomain dsToDomain;
    private final DomainToDS domainToDS;
    private final DomainToWeb domainToWeb;
    private final UserService userService;

    public GameServiceImpl(BotAi botAi,
                           TicTacToeRepository ticTacToeRepository,
                           DSToDomain dsToDomain,
                           DomainToDS domainToDS,
                           DomainToWeb domainToWeb,
                           UserService userService) {
        this.botAi = botAi;
        this.ticTacToeRepository = ticTacToeRepository;
        this.dsToDomain = dsToDomain;
        this.domainToDS = domainToDS;
        this.domainToWeb = domainToWeb;
        this.userService = userService;
    }

    @Override
    public int[][] nextTurn(int[][] gameField) {

//        if (!checkIsNull(currentGame)) {

        int[] next = botAi.selectBestMove(gameField);
            gameField[next[0]][next[1]] = 2;
            return gameField;

    }

    @Override
    public boolean isCorrectGame(int[][] sourceGameField, int[][] newGameField, GameStatus whichTurn) {

        int difAmount = 0;

        int playerOneCounter = 0;
        int playerTwoCounter = 0;
        int sizeN = sourceGameField.length;

        int placedIcon = -1;

        for (int i = 0; i < sizeN; i++) {
            for (int j = 0; j < sizeN; j++) {

                if(sourceGameField[i][j] == 1) {
                    playerOneCounter++;
                }
                else if (sourceGameField[i][j] == 2){
                    playerTwoCounter++;
                }

                if (sourceGameField[i][j] != 0 && sourceGameField[i][j] != newGameField[i][j]) {
                    return false;
                }

                if(sourceGameField[i][j] != newGameField[i][j]) {
                    placedIcon = newGameField[i][j];
                    difAmount++;
                }

            }
        }

        if (difAmount > 1) {
            return false;
        }

        if (playerOneCounter == playerTwoCounter && placedIcon == 1 && whichTurn.equals(GameStatus.HOST_TURN)) {
            return true;
        }
        else if (playerOneCounter - playerTwoCounter == 1 && placedIcon == 2 && whichTurn.equals(GameStatus.MEMBER_TURN)) {
            return true;
        }
        else {
            return false;
        }


    }

    @Override
    public Integer isGameEnds(GameField gameField) {

        if (gameField != null) {
            return botAi.whoWin(gameField.getField());
        }
        else {
            throw new IllegalArgumentException("Expecting not null");
        }

    }

    public boolean checkIsNull(CurrentGame currentGame) {
        if (currentGame == null) {
            return true;
        }
        if (currentGame.getGameField() == null) {
            return true;
        }
        if (currentGame.getId() == null) {
            return true;
        }
        return false;
    }

    @Override
    public void saveGame(CurrentGame currentGame) {

        DSCurrentGame dsGame = domainToDS.currentGameToDS(currentGame);

        ticTacToeRepository.save(dsGame);

    }

    @Override
    public CurrentGame getGameById(UUID id) {

        DSCurrentGame DSGame = ticTacToeRepository.findById(id);

        if (DSGame != null) {
            return dsToDomain.dsToCurrentGame(DSGame);
        }

        return null;

    }

    @Override
    public List<CurrentGame> getByGameStatus(GameStatus gameStatus) {

        List<DSCurrentGame> DSGames = ticTacToeRepository.findByGameStatus(gameStatus);

        List<CurrentGame> games = DSGames.stream()
                .map(game -> dsToDomain.dsToCurrentGame(game))
                .toList();

        return games;

    }

    @Override
    public int getUserInfo(UUID id) {
        return ticTacToeRepository.countUserGames(id);
    }

    @Override
    public WebCurrentGame createNewGame(Principal principal, GameType gameType) {

        CurrentGame newGame = new CurrentGame(gameType);

        String login = principal.getName();
        User host = userService.getUserByLogin(login);

        newGame.getGameField().setFieldText();
        newGame.setHost(host);

        if (gameType == GameType.AI) {
            newGame.setGameStatus(GameStatus.HOST_TURN);
        }
        else {
            newGame.setGameStatus(GameStatus.OPEN);
        }

        saveGame(newGame);
        WebCurrentGame webCurrentGame = domainToWeb.domainToCurrentGame(newGame);

        int playerIcon = 0;
        if (newGame.getHost().getId().equals(host.getId())) {
            playerIcon = 1;
        }
        else {
            playerIcon = 2;
        }

        webCurrentGame.setPlayerIcon(playerIcon);

        return webCurrentGame;

    }

    @Override
    public GetGameDto getGameDtoById(Principal principal, UUID id) {

        CurrentGame currentGame = getGameById(id);

        GetGameDto getGameDto = null;

        String login = principal.getName();
        User user = userService.getUserByLogin(login);

        if (currentGame != null) {
            currentGame.getGameField().convertTextToArray();

            Integer playerIcon = 0;
            if (currentGame.getHost().getId().equals(user.getId())) {
                playerIcon = 1;
            }
            else if (currentGame.getMember() == null){
                playerIcon = null;
            }
            else {
                playerIcon = 2;
            }

            getGameDto = new GetGameDto(currentGame.getId(),
                    currentGame.getGameField().getField(),
                    playerIcon,
                    domainToWeb.domainToUser(currentGame.getHost()),
                    domainToWeb.domainToUser(currentGame.getMember()));

            return getGameDto;

        }
        else {
            throw new GameNotFoundException();
        }

    }

    @Override
    public List<CurrentGamesDto> getOpenGames() {

        List<CurrentGame> gamesList = getByGameStatus(GameStatus.OPEN);

        List<CurrentGamesDto> games = gamesList.stream()
                .map(game -> new CurrentGamesDto(game.getId()))
                .toList();

        return games;

    }

    @Override
    public WebCurrentGame joinGame(Principal principal, UUID gameId) {

        CurrentGame curGame = getGameById(gameId);

        String login = principal.getName();
        User user = userService.getUserByLogin(login);

        if (curGame.getMember() == null && !curGame.getGameType().equals(GameType.AI)) {
            curGame.getGameField().convertTextToArray();
            if (!curGame.getHost().getId().equals(user.getId())) {
                curGame.setMember(user);

                curGame.setGameStatus(GameStatus.HOST_TURN);

                saveGame(curGame);

                WebCurrentGame webCurrentGame = domainToWeb.domainToCurrentGame(curGame);
                webCurrentGame.setPlayerIcon(2);

                return webCurrentGame;

            }
            else {
                throw new PlayWithYourselfException();
            }
        }
        else {
            throw new GameIsFullException();
        }

    }

    @Override
    public WebCurrentGame turn(UUID gameId, WebCurrentGame webCurrentGameInput, Principal principal) {

        CurrentGame DBGame = getGameById(gameId);

        String login = principal.getName();
        User user = userService.getUserByLogin(login);

        if (DBGame == null) {
            throw new GameNotFoundException();
        }
        if (isGameFieldEmpty(webCurrentGameInput.getGameField())) {
            throw new IncorrectMoveException();
        }

        if ((DBGame.getHost() != null && user.getId().equals(DBGame.getHost().getId())) ||
                (DBGame.getMember() != null && user.getId().equals(DBGame.getMember().getId()))) {

            DBGame.getGameField().convertTextToArray();

            Integer winner = isGameEnds(DBGame.getGameField());

            if (winner != null) {
                switch (winner) {
                    case 0:
                        DBGame.setGameStatus(GameStatus.TIE);
                    case 1:
                        DBGame.setGameStatus(GameStatus.HOST_WIN);
                    case 2:
                        DBGame.setGameStatus(GameStatus.MEMBER_WIN);
                }
                return domainToWeb.domainToCurrentGame(DBGame);
            }

            GameStatus whichTurn = user.getId().equals(DBGame.getHost().getId()) ? GameStatus.HOST_TURN : GameStatus.MEMBER_TURN;

            if (isCorrectGame(DBGame.getGameField().getField(),
                    webCurrentGameInput.getGameField(),
                    whichTurn)) {

                if (DBGame.getGameType().equals(GameType.AI)) {
                    // calculate next turn and set new field to DBGame field
                    DBGame.getGameField().setField(nextTurn(webCurrentGameInput.getGameField()));
                    DBGame.setGameStatus(GameStatus.HOST_TURN);
                }
                else {
                    DBGame.getGameField().setField(webCurrentGameInput.getGameField());
                    if (whichTurn.equals(GameStatus.HOST_TURN)) {
                        DBGame.setGameStatus(GameStatus.MEMBER_TURN);
                    }
                    else {
                        DBGame.setGameStatus(GameStatus.HOST_TURN);
                    }
                }
                DBGame.getGameField().setFieldText();

                // save updated game to DB
                saveGame(DBGame);

                return domainToWeb.domainToCurrentGame(DBGame);

            } else {
                throw new IncorrectMoveException();
            }

        }
        else {
            throw new AccessDeniedException();
        }
    }

    private boolean isGameFieldEmpty(int[][] field) {

        if (field.length < 3) {
            return true;
        }
        if (field[0].length < 3) {
            return true;
        }

        return false;

    }
}
