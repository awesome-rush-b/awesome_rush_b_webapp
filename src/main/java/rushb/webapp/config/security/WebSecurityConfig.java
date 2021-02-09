package rushb.webapp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rushb.webapp.fillter.JwtRequestFilter;

// This class extends the WebSecurityConfigurerAdapter is a convenience class that allows customization
// to both WebSecurity and HttpSecurity.
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // springboot security prohibit annotation. This will enable annotation.
// reference: https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/method/configuration/EnableGlobalMethodSecurity.html
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private WebAuthenticationEntryPoint webAuthenticationEntryPoint;

    private WebAccessDeniedHandler webAccessDeniedHandler;

    private UserDetailsService jwtUserDetailsService;

    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public WebSecurityConfig(WebAuthenticationEntryPoint webAuthenticationEntryPoint,
                             WebAccessDeniedHandler webAccessDeniedHandler,
                             UserDetailsService jwtUserDetailsService,
                             JwtRequestFilter jwtRequestFilter){
        this.webAuthenticationEntryPoint = webAuthenticationEntryPoint;
        this.webAccessDeniedHandler = webAccessDeniedHandler;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        // todo Use BCryptPasswordEncoder

        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        authenticationManagerBuilder.userDetailsService(jwtUserDetailsService);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // todo csrf
        httpSecurity.csrf().disable()
                .authorizeRequests()
                    // dont authenticate this particular request
                    .antMatchers("/api/login","/api/register").permitAll()
                    .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                        "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
                    // all other requests need to be authenticated
                    .anyRequest().authenticated()
                    // make sure we use stateless session; session won't be used to
                    // store user's state.
                    .and().exceptionHandling().authenticationEntryPoint(webAuthenticationEntryPoint) // handle authentication exception
                    .accessDeniedHandler(webAccessDeniedHandler) // handle access denied exception
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // todo password encoder
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }
}
