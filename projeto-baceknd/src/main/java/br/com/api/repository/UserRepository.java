package br.com.api.repository;


import br.com.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String finalidade);

    User findByLogin(String login);

    boolean existsByLogin(String login);

    @Query(value = "SELECT u FROM User u")
    List<User> findAllUsers();
}
