package br.com.challenge.procurement.core.repositories.authentication;

import br.com.challenge.procurement.core.models.authentication.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserTokenRepo extends JpaRepository<UserToken, Long> {


    @Query(value = """
      select t from UserToken t inner join BaseUser u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<UserToken> findAllValidTokenByUser(Long id);

    Optional<UserToken> findByToken(String token);

}
