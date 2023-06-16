package spring.security.sample;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TestService {
  @Autowired
  private Repository repository;
 
  public Optional<AUTH_USER> find(String id){
    Optional<AUTH_USER> A =repository.findById(id);
    //AUTH_USER B = A.orElse(null);
    return A;
  }

  @Transactional
  public void insertTestData(final String email, final String Hashpass) {
      AUTH_USER a = new AUTH_USER();
      a.setEmail(email);
      a.setPass(Hashpass);
      repository.save(a);
  }
    public List<AUTH_USER> getall(){
    List<AUTH_USER> A =repository.findAll();
    //AUTH_USER B = A.orElse(null);
    return A;
  }

}
