package com.learningSpringSecurity.LearnSpringSecurity.repository;

import com.learningSpringSecurity.LearnSpringSecurity.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
