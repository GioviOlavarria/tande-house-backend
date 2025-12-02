package tande.house.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tande.house.backend.model.User;

@Getter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String nombre;
    private String email;
    private Boolean admin;

    public static UserDTO from(User u) {
        return new UserDTO(
                u.getId(),
                u.getNombre(),
                u.getEmail(),
                u.getAdmin() != null && u.getAdmin()
        );
    }
}
