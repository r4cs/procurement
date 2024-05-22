package br.com.challenge.procurement.core.model.entities;

import br.com.challenge.procurement.core.model.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.model.authentication.UserToken;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
public class Usuario extends BaseUser {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserToken> tokens = new ArrayList<>();

    public Usuario(UsuarioDTO dto) {
        super(dto.id(), dto.nome(), dto.sobrenome(), dto.email(), dto.senha(), dto.role());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "tokens=" + tokens +
                '}';
    }
}
