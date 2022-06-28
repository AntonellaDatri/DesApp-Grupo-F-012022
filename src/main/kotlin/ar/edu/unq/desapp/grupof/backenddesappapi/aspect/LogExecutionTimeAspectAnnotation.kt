package ar.edu.unq.desapp.grupof.backenddesappapi.aspect

import ar.edu.unq.desapp.grupof.backenddesappapi.security.JWTAuthorizationFilter
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest


@Aspect
@Component
@Order(0)
class LogExecutionTimeAspectAnnotation(@Autowired var request: HttpServletRequest) {

    var logger: Logger = LoggerFactory.getLogger(LogExecutionTimeAspectAnnotation::class.java)

    @Around("@annotation(LogExecutionTime)")
    fun log(joinPoint: ProceedingJoinPoint): Any? {
        val user = JWTAuthorizationFilter().getUserNameWithRequestIfExist(request)
        logger.info("-------------------> AROUND START  logExecutionTime annotation <-------------------")
        logger.info("timestamp: " + LocalDateTime.now())
        if ( user != null ){
            logger.info("user: $user")
        }
        logger.info("Method: " + joinPoint.signature.toShortString())
        logger.info("Args: " + Arrays.toString((joinPoint.signature as MethodSignature).parameterNames))
        val start = System.currentTimeMillis()
        val result = joinPoint.proceed()
        val executionTime = System.currentTimeMillis() - start
        logger.info("------>" + joinPoint.signature.toShortString() + " executed in " + executionTime + "ms <------")
        logger.info("-------------------> AROUND FINISH  logExecutionTime annotation <-------------------")
        return result
    }

}


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LogExecutionTime