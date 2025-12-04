package tande.house.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tande.house.backend.dto.AuthResponse;
import tande.house.backend.dto.LoginRequest;
import tande.house.backend.dto.RegisterRequest;
import tande.house.backend.dto.UserDTO;
import tande.house.backend.model.User;
import tande.house.backend.repository.UserRepository;
import tande.house.backend.security.JwtService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
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

        UserDTO dto = UserDTO.from(u);
        String token = jwtService.generateToken(u);

        return new AuthResponse(dto, token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        User u = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        if (!u.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        UserDTO dto = UserDTO.from(u);
        String token = jwtService.generateToken(u);

        return new AuthResponse(dto, token);
    }
}
