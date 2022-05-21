package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
public class ExeTimeAspect {
    @Pointcut(value = "execution(public * spring..*(..))")
    private void publicTarget(){

    }
    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        Object result = joinPoint.proceed();
        long finish = System.nanoTime();
        Signature sig = joinPoint.getSignature();
        LocalDateTime now = LocalDateTime.now();
        String formatNow = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        String methodName1 = joinPoint.getSignature().getName();
        String methodName2 = String.valueOf(joinPoint.getTarget());

        System.out.printf("[ %s ] [ %s([%s]) ] [ %d ns ]\n", formatNow, methodName1, methodName2, (finish - start));

        return result;
    }
}
