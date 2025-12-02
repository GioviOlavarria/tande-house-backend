package tande.house.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tande.house.backend.dto.LoginRequest;
import tande.house.backend.dto.RegisterRequest;
import tande.house.backend.dto.UserDTO;
import tande.house.backend.model.User;
import tande.house.backend.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ya registrado");
        }

        User u = new User();
        u.setNombre(request.getNombre());
        u.setEmail(request.getEmail());
        u.setPassword(request.getPassword());

        String adminCodeEnv = System.getenv("ADMIN_CODE");
        boolean isAdmin = Boolean.TRUE.equals(request.getAdmin())
                && adminCodeEnv != null
                && !adminCodeEnv.isBlank()
                && adminCodeEnv.equals(request.getAdminCode());

        u.setAdmin(isAdmin);
        u = userRepository.save(u);
        return UserDTO.from(u);
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginRequest request) {
        User u = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        if (!u.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }
        return UserDTO.from(u);
    }
}
