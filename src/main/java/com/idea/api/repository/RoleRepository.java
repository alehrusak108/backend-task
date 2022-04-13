package com.idea.api.repository;

import com.idea.api.model.Role;
import com.idea.api.model.RoleName;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	default Role findByNameThrows(RoleName roleName) {
		return findByName(roleName).orElseThrow(() -> new EntityNotFoundException("Could not find Role by name: " + roleName));
	}

	Optional<Role> findByName(RoleName roleName);
}