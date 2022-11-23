package com.barnackles.StringJobApp.repository;

import com.barnackles.StringJobApp.model.GeneratedString;
import com.barnackles.StringJobApp.model.StringJob;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface StringJobRepository extends JpaRepository<StringJob, Long> {


}
