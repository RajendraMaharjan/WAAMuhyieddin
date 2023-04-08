package edu.miu.waalab.service.logging.impl;

import edu.miu.waalab.domain.logging.LoggerEntity;
import edu.miu.waalab.repository.logging.LoggerEntityRepo;
import edu.miu.waalab.service.logging.LoggerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerEntityServiceImpl implements LoggerEntityService {

    @Autowired
    private LoggerEntityRepo loggerEntityRepo;

    @Override
    public void saveLoggerEntity(LoggerEntity loggerEntity) {
        loggerEntityRepo.save(loggerEntity);
    }
}
