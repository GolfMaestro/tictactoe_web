package org.bygolf.di;

import org.bygolf.datasource.mapper.DSToDomain;
import org.bygolf.datasource.mapper.DSToDomainImpl;
import org.bygolf.datasource.mapper.DomainToDS;
import org.bygolf.datasource.mapper.DomainToDSImpl;
import org.bygolf.datasource.repository.TicTacToeRepository;
import org.bygolf.datasource.repository.UserRepository;
import org.bygolf.domain.service.*;
import org.bygolf.security.filter.AuthFilter;
import org.bygolf.domain.ai.BotAi;
import org.bygolf.domain.ai.BotAiImpl;
import org.bygolf.web.mapper.DomainToWeb;
import org.bygolf.web.mapper.DomainToWebImpl;
import org.bygolf.web.mapper.WebToDomain;
import org.bygolf.web.mapper.WebToDomainImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public BotAi botAi() {
        return new BotAiImpl();
    }

    @Bean
    public GameService gameService(
            BotAi botAi,
            TicTacToeRepository repository,
            DSToDomain dsToDomain,
            DomainToDS domainToDS,
            DomainToWeb domainToWeb,
            UserService userService) {
        return new GameServiceImpl(botAi, repository, dsToDomain, domainToDS, domainToWeb, userService);
    }

    @Bean
    public UserServiceImpl userService(
            UserRepository userRepository,
            DSToDomain dsToDomain,
            PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(userRepository, dsToDomain, passwordEncoder);
    }

    @Bean
    public AuthFilter authFilter(
            UserServiceImpl userServiceImpl
    ) {
        return new AuthFilter(userServiceImpl);
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
