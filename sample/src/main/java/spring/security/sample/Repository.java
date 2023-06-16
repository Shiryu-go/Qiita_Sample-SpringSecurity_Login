package spring.security.sample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<AUTH_USER,String> {
  
}
