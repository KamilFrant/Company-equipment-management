package pl.kfrant.personelmanagement.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //@Query("Select u from User u where lower(u.lastName) like lower(concat('%', :lastName,'%'))")
    List<User> findByLastNameContainsIgnoreCase(String lastName);
    Optional<User> findByPesel(String pesel);
}
