
  package com.spb02.dao;
  
  import org.springframework.data.repository.CrudRepository;
  
  import com.spb02.entities.User;
  
  public interface UserRepository extends CrudRepository<User, Integer>{
  
  }
 