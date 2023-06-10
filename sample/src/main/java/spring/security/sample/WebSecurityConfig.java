package spring.security.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			User.withDefaultPasswordEncoder()
			//パスワードエンコーダーをUserBuilderを建てるとと同時に自動生成。
			//このパスワードエンコーダーは、本来は別のBeanとして定義されるべきもの。
			//このパスワードエンコーダーはdeprecatedだが、SpringSecurityの動きを確かめるために残されている。
		 	.username("user")
		 	.password("password")
		 	.roles("USER")
		 	.build();

		return new InMemoryUserDetailsManager(user);
		//InMemoryUserDetailsManagerは、UserDetailsServiceをを継承したUserDetailsManagerの実装
		//メモリ上にあるものをUserDetailsServiceとして渡して、入力値として検証する。
		//本来の実装には向かない。
	}
}
