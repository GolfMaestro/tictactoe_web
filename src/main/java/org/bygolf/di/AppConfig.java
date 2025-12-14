package org.bygolf.di;

import org.bygolf.domain.ai.BotAi;
import org.bygolf.domain.ai.BotAiImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BotAi botAi() {
        return new BotAiImpl();
    }

}
