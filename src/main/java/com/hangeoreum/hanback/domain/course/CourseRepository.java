package com.hangeoreum.hanback.domain.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameContainingOrDescriptionContaining(String nameKeyword, String descriptionKeyword);
    List<Course> findByRegion(String region);
    List<Course> findByDifficulty(String difficulty);
    List<Course> findByType(String type);
    List<Course> findByNameContainingOrDescriptionContainingAndRegion(String nameKeyword, String descriptionKeyword, String region);
    List<Course> findByNameContainingOrDescriptionContainingAndDifficulty(String nameKeyword, String descriptionKeyword, String difficulty);
    List<Course> findByNameContainingOrDescriptionContainingAndType(String nameKeyword, String descriptionKeyword, String type);
    List<Course> findByRegionAndDifficulty(String region, String difficulty);
    List<Course> findByRegionAndType(String region, String type);
    List<Course> findByDifficultyAndType(String difficulty, String type);
    List<Course> findByNameContainingOrDescriptionContainingAndRegionAndDifficulty(String nameKeyword, String descriptionKeyword, String region, String difficulty);
    List<Course> findByNameContainingOrDescriptionContainingAndRegionAndType(String nameKeyword, String descriptionKeyword, String region, String type);
    List<Course> findByNameContainingOrDescriptionContainingAndDifficultyAndType(String nameKeyword, String descriptionKeyword, String difficulty, String type);
    List<Course> findByRegionAndDifficultyAndType(String region, String difficulty, String type);
    List<Course> findByNameContainingOrDescriptionContainingAndRegionAndDifficultyAndType(String nameKeyword, String descriptionKeyword, String region, String difficulty, String type);
}
