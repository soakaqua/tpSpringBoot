package formation.sopra.tpSpringBoot01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import formation.sopra.tpSpringBoot01.model.Login;

public interface LoginRepository extends JpaRepository<Login, String> {

	@Query("select distinct l from Login l left join fetch l.roles where l.login=:login")
	Optional<Login> findByIdWithRoles(String login);
}
