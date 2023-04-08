package edu.miu.waalab.repository.logging;

import edu.miu.waalab.domain.logging.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerEntityRepo extends JpaRepository<LoggerEntity, Long> {
}
