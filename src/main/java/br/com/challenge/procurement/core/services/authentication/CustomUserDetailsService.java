package br.com.challenge.procurement.core.services.authentication;

import br.com.challenge.procurement.core.models.authentication.RoleEnum;
import br.com.challenge.procurement.core.models.entities.Fornecedor;
import br.com.challenge.procurement.core.models.entities.Funcionario;
import br.com.challenge.procurement.core.repositories.FornecedorRepo;
import br.com.challenge.procurement.core.repositories.FuncionarioRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final FuncionarioRepo funcionarioRepo;
    private final FornecedorRepo fornecedorRepo;

    public CustomUserDetailsService(FuncionarioRepo funcionarioRepo, FornecedorRepo fornecedorRepo) {
        this.funcionarioRepo = funcionarioRepo;
        this.fornecedorRepo = fornecedorRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Verifica primeiro se é um funcionário
        Optional<Funcionario> funcionarioOptional = funcionarioRepo.findByEmail(username);
        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            return new User(funcionario.getEmail(), funcionario.getPassword(), getAuthorities(funcionario.getRole()));
        }

        // Se não for um funcionário, verifica se é um fornecedor
        Optional<Fornecedor> fornecedorOptional = fornecedorRepo.findByEmail(username);
        if (fornecedorOptional.isPresent()) {
            Fornecedor fornecedor = fornecedorOptional.get();
            return new User(fornecedor.getEmail(), fornecedor.getPassword(), getAuthorities(fornecedor.getRole()));
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(RoleEnum role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
}