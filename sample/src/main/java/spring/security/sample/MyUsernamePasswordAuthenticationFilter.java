package spring.security.sample;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        // "/api/login" の場合に認証を行うよう設定
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
            // リクエストのデータを LoginForm として取り出す
            Record principal = null;
            try {
                principal = new ObjectMapper().readValue(request.getInputStream(), Record.class);
            } catch (StreamReadException e) {
                e.printStackTrace();
            } catch (DatabindException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 認証処理を実行する
            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(principal.getUsername(),
				principal.getPassword());
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
        System.out.println("ちゃんとFilter動いてるで");
		return this.getAuthenticationManager().authenticate(authRequest);
    }
    @Override
    	protected AuthenticationManager getAuthenticationManager() {
		return this.authenticationManager;
	}
}
