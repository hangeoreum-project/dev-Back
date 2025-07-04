package com.hangeoreum.hanback.course.history;

import com.hangeoreum.hanback.course.history.dto.CourseSearchHistoryResponseDto;
import com.hangeoreum.hanback.domain.course.CourseSearchHistory;
import com.hangeoreum.hanback.domain.course.CourseSearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseSearchHistoryService {

    private final CourseSearchHistoryRepository courseSearchHistoryRepository;

    public List<CourseSearchHistoryResponseDto> getUserCourseSearchHistory(Long userId) {
        List<CourseSearchHistory> history = courseSearchHistoryRepository.findByUserId(userId);
        return history.stream()
                .map(CourseSearchHistoryResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCourseSearchHistoryEntry(Long historyId) {
        if (!courseSearchHistoryRepository.existsById(historyId)) {
            throw new IllegalArgumentException("Course search history entry not found with ID: " + historyId);
        }
        courseSearchHistoryRepository.deleteById(historyId);
    }
}
