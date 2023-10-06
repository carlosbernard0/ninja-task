package com.projetofinal.ninjatask.entity.security;

import com.projetofinal.ninjatask.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                        //regras especificas acima das regras gerais
                        .requestMatchers(HttpMethod.DELETE, "/usuario/**").hasRole("DEV")// apenas o cargo DEV podera fazer DELETE no usuario
                        .requestMatchers(HttpMethod.POST, "/usuario/**").hasRole("DEV")// apenas o cargo DEV podera fazer DELETE no usuario
                        .requestMatchers("/usuario/**").hasAnyRole("DEV","ADM") //ROLE_DEV
                        .requestMatchers("/caderno/**").hasAnyRole("DEV","ADM", "USUARIO")
                        .requestMatchers("/tarefa/**").hasAnyRole("DEV","ADM","USUARIO")
                        .requestMatchers("/projeto/**").hasAnyRole("DEV", "ADM")
                        .requestMatchers("/Controle de Logins/**").hasRole("DEV")
                .anyRequest().authenticated()); //Só Acessa se tiver autenticado
        //authz.anyRequest().permitAll()); //Todos EndPoints permitidos

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

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}