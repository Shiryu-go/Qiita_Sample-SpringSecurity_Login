package spring.security.sample;

import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public class main {
  public static void main(String args[]){
    System.out.println(new MyUsernamePasswordAuthenticationFilter(new ProviderManager(new DaoAuthenticationProvider())).getAuthenticationManager().getClass());
  }
}
