package bstorm.akimts.demomvcsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder encoder;

    public WebSecurityConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("pass")).authorities("ADMIN", "USER")
                .and()
                .withUser("user").password(encoder.encode("pass")).authorities("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Gestion du CSRF
        http.csrf()
                .disable();

        // Gestion des accès
        http.authorizeRequests()
                .antMatchers("/test/admin").hasAuthority("ADMIN")
                .antMatchers("/test/user").hasAuthority("USER")
                .antMatchers("/test/auth").authenticated()
                .anyRequest().permitAll();

        // Gestion login/logout
        http.formLogin()
                .loginPage("/session/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/test/auth")
                .failureUrl("/session/login");
        http.logout()
                .logoutUrl("/session/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler( (request, response, authentication) -> {
                    response.sendRedirect( request.getContextPath()+"/test/noauth" );
                } );

    }
}
