package spring.security.sample;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController("/user")
public class Resister {
  @Autowired
  private TestService service;
  @PostMapping("/resister")
  public String UserResist(@RequestBody AUTH_USER user){
    System.out.println(user.getEmail() + " " + user.getPass());
    service.insertTestData(user.getEmail() , user.getPass());
    return "ok";
  }
    @GetMapping("/findall")
  public List<AUTH_USER> all(){
    return service.getall();
  }
}
