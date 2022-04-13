package com.idea.api.repository;

import com.idea.api.model.IdeaLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaLikeRepository extends JpaRepository<IdeaLikeEntity, Long> {

}
