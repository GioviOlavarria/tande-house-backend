package tande.house.tandehouse.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tande.house.tandehouse.backend.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {


    Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);
}
