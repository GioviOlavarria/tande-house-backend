package tande.house.tandehouse.backend.service;

import tande.house.tandehouse.backend.model.User;
import tande.house.tandehouse.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Obtener todos los usuarios
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Buscar por id
    public User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    // Buscar por email
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    // Crear usuario (registro)
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con el email: " + user.getEmail());
        }
        user.setId(null); // Dejar que Mongo genere el id
        // Aquí más adelante: encriptar contraseña
        return userRepository.save(user);
    }

    // Actualizar usuario
    public User update(String id, User user) {
        User existing = getById(id);

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword()); // futuro: guardar hash
        existing.setRole(user.getRole());

        return userRepository.save(existing);
    }

    // Eliminar usuario
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
