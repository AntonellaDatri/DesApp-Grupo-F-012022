package ar.edu.unq.desapp.grupof.backenddesappapi.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.time.Instant


@Aspect
@Component
@Order(0)
class LogExecutionTimeAspectAnnotation {

    var logger: Logger = LoggerFactory.getLogger(LogExecutionTimeAspectAnnotation::class.java)

    @Around("@annotation(LogExecutionTime)")
    fun log(joinPoint: ProceedingJoinPoint): Any? {
        logger.info("-------------------> AROUND START  logExecutionTime annotation <-------------------")
        logger.info("timestamp: " + Instant.now())
        logger.info("user: " + "adatri") //TODO
        logger.info("Method: " + joinPoint.signature.toShortString())
        logger.info("Args: " + joinPoint.args)
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