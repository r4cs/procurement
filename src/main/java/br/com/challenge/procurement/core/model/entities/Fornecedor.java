package br.com.challenge.procurement.core.model.entities;

import br.com.challenge.procurement.core.model.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.model.authentication.UserToken;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Fornecedor extends BaseUser {

    private String razaoSocial;
    private String cnpj;
    private String telefone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserToken> tokens = new ArrayList<>();

    public Fornecedor(FornecedorDTO dto) {
        super(dto.id(), dto.nome(), dto.sobrenome(), dto.email(), dto.senha(), dto.role());
        this.razaoSocial = dto.razao_social();
        this.cnpj = dto.cnpj();
        this.telefone = dto.telefone();
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", tokens=" + tokens +
                '}';
    }
}
