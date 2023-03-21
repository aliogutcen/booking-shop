package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

@Repository
public interface ISubjectRepository extends JpaRepository<Subject,Long> {

    Optional<Subject> findOptionalBySubject(String subject);
}
