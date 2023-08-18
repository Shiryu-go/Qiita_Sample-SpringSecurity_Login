package spring.security.sample;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }
}
