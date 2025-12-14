package org.bygolf.di;

import org.bygolf.datasource.repository.Storage;
import org.bygolf.domain.ai.BotAi;
import org.bygolf.domain.ai.BotAiImpl;
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

}
