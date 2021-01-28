package br.com.caminha.mvc.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caminha.mvc.mudi.model.User;

public interface UserRepository extends JpaRepository<User, String>{

	User findByUsername(String username);
}
