package com.hangeoreum.hanback.domain.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSearchHistoryRepository extends JpaRepository<CourseSearchHistory, Long> {
    List<CourseSearchHistory> findByUserId(Long userId);
}
