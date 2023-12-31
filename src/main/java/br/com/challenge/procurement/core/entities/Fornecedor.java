package br.com.challenge.procurement.core.entities;

import br.com.challenge.procurement.core.entities.DTO.EnderecoDTO;
import br.com.challenge.procurement.core.entities.DTO.FornecedorDTO;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@Entity(name = "fornecedor")
@Table(name = "fornecedor_procurement")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "fornecedor_procurement_seq")
    Long id;
    private String razao_social;
    private String cnpj;
    private String nome_contato;
    private String telefone;
    private String email;
    @Embedded
    private EnderecoDTO endereco;

    public Fornecedor(FornecedorDTO dto) {
        this.razao_social = dto.razao_social();
        this.cnpj = dto.cnpj();
        this.nome_contato = dto.nome_contato();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.endereco = dto.endereco();
    }

    public Fornecedor() {};
}
