package edu.miu.waalab.aspects;

import edu.miu.waalab.domain.logging.LoggerEntity;
import edu.miu.waalab.service.logging.LoggerEntityService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

import static edu.miu.waalab.constant.StaticConstants.TAB;
import static edu.miu.waalab.constant.StaticConstants.USER;

@Aspect
@Configuration
public class LogginAspect {

    private HttpServletRequest request;
    private LoggerEntityService loggerEntityService;

    public LogginAspect(HttpServletRequest request, LoggerEntityService loggerEntityService) {
        this.request = request;
        this.loggerEntityService = loggerEntityService;
    }

    @Pointcut("execution(* edu.miu.waalab.service.user.UserService.*(..))")
    public void loggerUserOperations() {
    }

    @Before("loggerUserOperations()")
    public void loggingUserOperation(JoinPoint joinPoint) {
        LoggerEntity logger = buildLoggerEntity(joinPoint);
        loggerEntityService.saveLoggerEntity(logger);
    }

    @Before("execution(* *.*.*.service.comment.CommentService.*(..))")
    public void loggingCommentOperation(JoinPoint joinPoint) {
        LoggerEntity logger = buildLoggerEntity(joinPoint);
        loggerEntityService.saveLoggerEntity(logger);

    }

    @Before("execution(* edu.miu.waalab.service.post.PostService.*(..))")
    public void loggingPostOperation(JoinPoint joinPoint) {
        LoggerEntity logger = buildLoggerEntity(joinPoint);
        loggerEntityService.saveLoggerEntity(logger);
    }

    private LoggerEntity buildLoggerEntity(JoinPoint joinPoint) {

        StringBuilder builder = new StringBuilder();
        builder.append("Execution type: ").append(joinPoint.getKind()).append(TAB)
                .append(" Operation performed in class: ").append(joinPoint.getSignature().getDeclaringType().getSimpleName()).append(TAB)
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
