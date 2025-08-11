package com.algovis.backend.repository;

import com.algovis.backend.model.entity.UserProgress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProgressRepository extends CrudRepository<UserProgress, Long> {
    @Query("select up from UserProgress up where up.user.id = :userId")
    List<UserProgress> findAllByUserId(@Param("userId") Long userId);
}
