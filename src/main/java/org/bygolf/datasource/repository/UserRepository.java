package org.bygolf.datasource.repository;

import org.bygolf.datasource.model.DSUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<DSUser, Long> {

    DSUser findByLogin(String login);

}
