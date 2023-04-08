package edu.miu.waalab.repository.logging;

import edu.miu.waalab.domain.logging.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionEntityRepo extends JpaRepository<ExceptionEntity, Long> {
}
