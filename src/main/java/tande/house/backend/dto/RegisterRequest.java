package tande.house.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String nombre;
    private String email;
    private String password;
    private Boolean admin;
    private String adminCode;
}