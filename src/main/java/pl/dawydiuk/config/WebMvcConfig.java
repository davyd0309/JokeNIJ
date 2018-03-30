package pl.dawydiuk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer{

    @Bean
    public BCryptPasswordEncoder pwdEnc(){
        return new BCryptPasswordEncoder();
    }


}
