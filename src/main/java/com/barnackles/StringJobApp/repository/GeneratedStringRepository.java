package com.barnackles.StringJobApp.repository;
import com.barnackles.StringJobApp.model.GeneratedString;
import com.barnackles.StringJobApp.model.StringJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface GeneratedStringRepository extends JpaRepository<GeneratedString, Long> {


    public List<GeneratedString> findAllByStringJobEquals(StringJob stringJob);

    public void deleteAllByStringJobEquals(StringJob stringJob);

}
