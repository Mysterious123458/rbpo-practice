package ru.mtuci.coursemanagement.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mtuci.coursemanagement.model.Course;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    // Регрессионный тест CWE-89: до исправления конкатенация строк раскрывала
    // инъекцию "' OR '1'='1" и возвращала все записи таблицы.
    @Test
    void searchByTitleDoesNotTreatInjectionAsCondition() {
        List<Course> injected = courseService.searchByTitle("' OR '1'='1");
        assertTrue(injected.isEmpty(), "SQL-инъекция всё ещё раскрывает условие WHERE");
    }

    @Test
    void searchByTitleFindsSeededCourse() {
        List<Course> found = courseService.searchByTitle("RBPO");
        assertEquals(1, found.size());
        assertEquals("RBPO", found.get(0).getTitle());
    }
}
