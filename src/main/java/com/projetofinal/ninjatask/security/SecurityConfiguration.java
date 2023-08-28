package com.projetofinal.ninjatask.security;

import com.projetofinal.ninjatask.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UsuarioService usuarioService;

    @Bean //chama as requisições
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //permissões e filtros
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());
        http.authorizeHttpRequests((authz) ->
                authz.requestMatchers("/autenticacao/**").permitAll()
                //authz.anyRequest().permitAll()); //Todos EndPoints permitidos
                .anyRequest().authenticated()); //Só Acessa se tiver autenticado
        http.addFilterBefore(new TokenAuthenticationFilter(usuarioService), UsernamePasswordAuthenticationFilter.class);

       return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/");
    }
}