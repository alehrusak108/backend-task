package com.idea.api.repository;

import com.idea.api.model.UserEntity;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    default UserEntity findByIdThrows(Long ideaId) {
        return findById(ideaId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find User for ID: " + ideaId));
    }

    Optional<UserEntity> findByLogin(String login);
}
