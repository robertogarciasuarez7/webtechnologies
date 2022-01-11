package es.uvt.lbd.wt.enlacesrest.model.repository;

import java.util.List;

import es.uvt.lbd.wt.enlacesrest.model.domain.User;

public interface UserDao {
 
 List<User> findAll();

  User findById(Long id);

  User findByLogin(String login);
  
  User findByEmail (String email);

  void create(User user);
  
  void update(User user);

  void delete(Long id);
}
