package edu.miu.waalab.service.logging.impl;

import edu.miu.waalab.domain.logging.ExceptionEntity;
import edu.miu.waalab.repository.logging.ExceptionEntityRepo;
import edu.miu.waalab.service.logging.ExceptionEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionEntityServiceImpl implements ExceptionEntityService {

    @Autowired
    private ExceptionEntityRepo exceptionEntityRepo;

    @Override
    public void saveExceptionEntity(ExceptionEntity exception) {
        exceptionEntityRepo.save(exception);
    }
}
