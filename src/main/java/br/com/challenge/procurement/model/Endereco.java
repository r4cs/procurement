package br.com.challenge.procurement.model;

import br.com.challenge.procurement.model.DTO.EnderecoDTO;
import br.com.challenge.procurement.model.baseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@Getter
@Setter
@Entity(name="endereco")
@Table(name = "tb_endereco_procurement")
public class Endereco extends BaseEntity {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(EnderecoDTO dto) {
        this.logradouro = dto.logradouro();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.cep = dto.cep();
    }

    public Endereco() {}
}
