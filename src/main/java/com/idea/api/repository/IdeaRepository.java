package com.idea.api.repository;

import com.idea.api.model.IdeaEntity;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IdeaRepository extends JpaRepository<IdeaEntity, Long> {

    default IdeaEntity findByIdThrows(Long ideaId) {
        return findById(ideaId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Idea for ID: " + ideaId));
    }

    Page<IdeaEntity> findByCategoryLikeOrderByUpdatedAtDesc(String category, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update IdeaEntity set imageUrl = :imageUrl where id = :ideaId")
    void updateIdeaImage(@Param("ideaId") Long ideaId, @Param("imageUrl") String imageUrl);
}
