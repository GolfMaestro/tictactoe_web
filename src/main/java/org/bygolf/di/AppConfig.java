package org.bygolf.di;

import org.bygolf.datasource.mapper.DSToDomain;
import org.bygolf.datasource.mapper.DSToDomainImpl;
import org.bygolf.datasource.mapper.DomainToDS;
import org.bygolf.datasource.mapper.DomainToDSImpl;
import org.bygolf.datasource.repository.Storage;
import org.bygolf.datasource.repository.TicTacToeRepository;
import org.bygolf.datasource.repository.TicTacToeRepositoryImpl;
import org.bygolf.domain.ai.BotAi;
import org.bygolf.domain.ai.BotAiImpl;
import org.bygolf.domain.service.GameService;
import org.bygolf.domain.service.GameServiceImpl;
import org.bygolf.web.mapper.DomainToWeb;
import org.bygolf.web.mapper.DomainToWebImpl;
import org.bygolf.web.mapper.WebToDomain;
import org.bygolf.web.mapper.WebToDomainImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public BotAi botAi() {
        return new BotAiImpl();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Storage storage() {
        return new Storage();
    }

    @Bean
    public GameService gameService() {
        return new GameServiceImpl(botAi());
    }

    @Bean
    public TicTacToeRepository ticTacToeRepository() {
        return new TicTacToeRepositoryImpl(storage());
    }

    @Bean
    public DSToDomain dsToDomainMapper() {
        return new DSToDomainImpl();
    }

    @Bean
    public DomainToDS domainToDSMapper() {
        return new DomainToDSImpl();
    }

    @Bean
    public WebToDomain webToDomainMapper() {
        return new WebToDomainImpl();
    }

    @Bean
    public DomainToWeb domainToWebMapper() {
        return new DomainToWebImpl();
    }

}
