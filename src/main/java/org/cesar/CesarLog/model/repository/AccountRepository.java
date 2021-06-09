package org.cesar.CesarLog.model.repository;

import java.util.Optional;

import org.cesar.CesarLog.model.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	@Query("select a from Account a where a.login = ?1")
	Optional<Account> findBylogin(String login);
	
}
