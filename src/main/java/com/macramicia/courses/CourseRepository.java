package com.macramicia.courses;

/*
 Reference: https://spring.io/guides/gs/accessing-data-jpa/
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
