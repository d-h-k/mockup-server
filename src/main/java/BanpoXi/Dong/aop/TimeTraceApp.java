package BanpoXi.Dong.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class TimeTraceApp {

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start;
        System.out.println("START: "+joinPoint.toLongString());
        try {
            Object result = joinPoint.proceed();
        }
    }
}
