package spring.security.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig implements UserDetailsService {
  	@Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home","/register","/ok","/user/register").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
      .csrf(csrf -> csrf
              .ignoringRequestMatchers("/user/register"))
			.logout((logout) -> logout.permitAll());
		return http.build();
	}
  @Autowired
  private TestService service;

/*   @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  } */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserDetails user = User.withDefaultPasswordEncoder()
      .username(email)
      .password(service.find(email).orElse(null).getPass())
      .roles("USER")
      .build();
    return user;
  }
/*   @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserDetails user = User.withUsername(email)
      .password(service.find(email).orElse(null).getPass())
      .roles("USER")
      .build();
    return user;
  } */
}
