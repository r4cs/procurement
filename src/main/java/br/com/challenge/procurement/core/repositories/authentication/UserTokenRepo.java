package br.com.challenge.procurement.core.repositories.authentication;

import br.com.challenge.procurement.core.model.authentication.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserTokenRepo extends JpaRepository<UserToken, Long> {

    @Query("select t from UserToken t where t.user.id = :id and (t.expired = false or t.revoked = false)")
    List<UserToken> findAllValidTokenByUser(Long id);

    Optional<UserToken> findByToken(String token);
}
