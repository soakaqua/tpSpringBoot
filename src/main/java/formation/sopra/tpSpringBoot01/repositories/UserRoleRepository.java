package formation.sopra.tpSpringBoot01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.tpSpringBoot01.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
