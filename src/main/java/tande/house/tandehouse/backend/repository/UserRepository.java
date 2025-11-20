package tande.house.tandehouse.backend.repository;

import tande.house.tandehouse.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    // Buscar usuario por email (para login, etc.)
    Optional<User> findByEmail(String email);

    // Verificar si ya existe un usuario con ese email
    boolean existsByEmail(String email);
}
