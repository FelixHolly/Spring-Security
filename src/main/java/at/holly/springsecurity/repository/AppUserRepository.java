package at.holly.springsecurity.repository;

import at.holly.springsecurity.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    AppUser findByUsername(String username);

}
