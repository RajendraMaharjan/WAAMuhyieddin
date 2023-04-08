package edu.miu.waalab.aspects;

import edu.miu.waalab.domain.logging.LoggerEntity;
import edu.miu.waalab.service.logging.LoggerEntityService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

import static edu.miu.waalab.constant.StaticConstants.TAB;
import static edu.miu.waalab.constant.StaticConstants.USER;

@Aspect
@Configuration
public class ExecutionTimeAspect {

    @Autowired
    private LoggerEntityService loggerEntityService;

    @Autowired
    private HttpServletRequest request;

    @Pointcut(value = "@annotation(edu.miu.waalab.aspects.annotation.ExecutionTime)")
    public void executionTimeAnnotation() {
    }

    @Around(value = "executionTimeAnnotation()")
    public void calculateExecutionTimetoRetrieveAUser(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        var obj = proceedingJoinPoint.proceed();
        proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        Long endTime = System.currentTimeMillis();

        LoggerEntity logger = buildLoggerEntity(proceedingJoinPoint, endTime - startTime);
        loggerEntityService.saveLoggerEntity(logger);
    }

    private LoggerEntity buildLoggerEntity(JoinPoint joinPoint, Long totalTime) {

        StringBuilder builder = new StringBuilder();
        builder.append("Total time in milisec to Perform: ").append(totalTime)
                .append(", Operation performed in class: ").append(joinPoint.getSignature().getDeclaringType().getSimpleName()).append(TAB)
                .append(" as: ").append(joinPoint.getSignature().getName()).append(TAB)
                .append(" Method Type: ").append(request.getMethod()).append(TAB)
                .append(" Request URI: ").append(request.getRequestURL());

        LoggerEntity logger = new LoggerEntity.LoggerEntityBuilder()
                .setDate(new Date())
                .setTime(new Date())
                .setPrinciple(USER)
                .setOperation(builder.toString())
                .build();

        return logger;
    }

}
