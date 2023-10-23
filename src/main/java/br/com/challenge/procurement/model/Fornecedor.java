package br.com.challenge.procurement.model;

import br.com.challenge.procurement.model.DTO.FornecedorDTO;
import br.com.challenge.procurement.model.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@Getter
@Setter
@Entity(name = "fornecedor")
@Table(name = "tb_fornecedor_procurement")
public class Fornecedor extends BaseEntity {
    private String razao_social;
    private String cnpj;
    private String nome_contato;
    private String telefone;
    private String email;
    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public Fornecedor(FornecedorDTO dto) {
        this.razao_social = dto.razao_social();
        this.cnpj = dto.cnpj();
        this.nome_contato = dto.nome_contato();
        this.telefone = dto.telefone();
        this.email = dto.email();
    }

    public Fornecedor() {};
}
