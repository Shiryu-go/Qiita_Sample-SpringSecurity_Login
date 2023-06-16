package spring.security.sample;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AUTH_USER  {
  @Id
  String email;
  String pass;
}
