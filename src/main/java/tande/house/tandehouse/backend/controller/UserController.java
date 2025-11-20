package tande.house.tandehouse.backend.controller;

import tande.house.tandehouse.backend.model.User;
import tande.house.tandehouse.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // cambia al dominio de tu React en producción
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public User getById(@PathVariable String id) {
        return userService.getById(id);
    }

    // GET /api/users/email/{email}
    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    // POST /api/users   -> registro
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = userService.create(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        return userService.update(id, user);
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
