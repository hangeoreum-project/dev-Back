package com.hangeoreum.hanback.domain.postcard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostcardRepository extends JpaRepository<Postcard, Long> {
    List<Postcard> findByUserId(Long userId);
}
