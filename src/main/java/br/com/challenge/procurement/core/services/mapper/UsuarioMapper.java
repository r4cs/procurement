package br.com.challenge.procurement.core.services.mapper;

import br.com.challenge.procurement.core.models.DTO.FuncionarioDTO;
import br.com.challenge.procurement.core.models.entities.Funcionario;

public class UsuarioMapper {

    public Funcionario toEntity(FuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.id());
        funcionario.setNome(dto.nome());
        funcionario.setSenha(dto.senha());
        funcionario.setEmail(dto.email());
        return funcionario;
    }

    public static FuncionarioDTO toDTO(Funcionario funcionario) {
        return new FuncionarioDTO(funcionario);
    }
}
