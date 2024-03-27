package br.com.challenge.procurement.core.entities.DTO;

import br.com.challenge.procurement.core.entities.Permissao;
import br.com.challenge.procurement.core.entities.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para representar um usuário")
public record UsuarioDTO(
        @Schema(description = "Nome do usuário", example = "João da Silva")
        String nome,
        @Schema(description = "Permissão do usuário, sempre em caixa alta", example = "SOLICITANTE ou APROVADOR")
        Permissao permissao,
        @Schema(description = "Senha do usuário", example = "senha@123")
        String senha,
        @Schema(description = "Email do usuário", example = "joao@example.com")
        String email
) {

    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getNome(),
                usuario.getPermissao(),
                usuario.getSenha(),
                usuario.getEmail()
        );
    }
}
