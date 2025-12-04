package pe.edu.upc.viviestu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;


//@Profile(value = {"development", "production"})
//Clase S7
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    private static void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry req) {
        req
                .requestMatchers("/login").permitAll()

                .requestMatchers("/api/v1/authentication/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-resources/**").permitAll()
                .requestMatchers("/webjars/**").permitAll()

                .requestMatchers("/usuarios/registro").permitAll()

                /*
                //USUARIO
                .requestMatchers("/usuarios").permitAll()
                .requestMatchers("/usuarios/listas").permitAll()
                .requestMatchers("/usuarios/{id}").permitAll()

                //ROLE
                .requestMatchers("/roles/{id}").permitAll()
                .requestMatchers("/roles/listas").permitAll()
                .requestMatchers("/roles/nuevo").permitAll()
                .requestMatchers("/roles").permitAll()

                //ZONA
                .requestMatchers("/zonas").permitAll()
                .requestMatchers("/zonas/nuevo").permitAll()
                .requestMatchers("/zonas/editar").permitAll()
                //.requestMatchers("/zonas/lista").permitAll()
                .requestMatchers("/zonas/{id}").permitAll()

                //ZONA UNIVERSIDAD
                .requestMatchers("/zona-universidad").permitAll()
                .requestMatchers("/zona-universidad/lista").permitAll()
                .requestMatchers("/zona-universidad/editar").permitAll()
                .requestMatchers("/zona-universidad/nuevo").permitAll()
                .requestMatchers("/zona-universidad/{id}").permitAll()

                //VALORACION
                .requestMatchers("/valoraciones").permitAll()
                .requestMatchers("/valoraciones/nuevo").permitAll()
                .requestMatchers("/valoraciones/editar").permitAll()
                .requestMatchers("/valoraciones/lista").permitAll()
                .requestMatchers("/valoraciones/{id}").permitAll()

                //SIMULADOR
                .requestMatchers("/simulador").permitAll()
                .requestMatchers("/simulador/lista").permitAll()
                .requestMatchers("/simulador/editar").permitAll()
                .requestMatchers("/simulador/nuevo").permitAll()
                .requestMatchers("/simulador/{id}").permitAll()

                //REPORTE
                .requestMatchers("/reportes").permitAll()
                .requestMatchers("/reportes/lista").permitAll()
                .requestMatchers("/reportes/nuevo").permitAll()
                .requestMatchers("/reportes/editar").permitAll()
                .requestMatchers("/reportes/{id}").permitAll()

                //COMPARACION
                .requestMatchers("/comparaciones").permitAll()
                .requestMatchers("/comparaciones/editar").permitAll()
                .requestMatchers("/comparaciones/nuevo").permitAll()
                .requestMatchers("/comparaciones/lista").permitAll()
                .requestMatchers("/comparaciones/{id}").permitAll()

                //COMPARACION DETALLE
                .requestMatchers("/comparacion-detalle").permitAll()
                .requestMatchers("/comparacion-detalle/lista").permitAll()
                .requestMatchers("/comparacion-detalle/nuevo").permitAll()
                .requestMatchers("/comparacion-detalle/editar").permitAll()
                .requestMatchers("/comparacion-detalle/{id}").permitAll()

                //FAVORITO
                .requestMatchers("/favoritos").permitAll()
                .requestMatchers("/favoritos/nuevo").permitAll()
                .requestMatchers("/favoritos/lista").permitAll()
                .requestMatchers("/favoritos/{id}").permitAll()

                 */

                .anyRequest().authenticated();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //Desde Spring Boot 3.1+
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(WebSecurityConfig::customize
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(Customizer.withDefaults());
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
