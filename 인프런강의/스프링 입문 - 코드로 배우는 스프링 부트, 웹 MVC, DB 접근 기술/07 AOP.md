# AOP
- AOP가 필요한 상황
    - 모든 메소드의 호출 시간을 측정하고 싶다면?
        - controller, service, repository에 전부 코드를 넣기에는 무리
        - 저렇게 한다해도 유지보수가 답이 없음
    - 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)
        - 시간측정은 공통 관심 사항
    - 회원 가입 시간, 회원 조회 시간을 측정하고 싶다면?

- AOP적용
    - Aspect Oriented Programming
    - 공통 관심 사항과 핵심 관심 사항을 분리한다
        - 시간을 측정하는 로직을 별도의 공통 로직으로 작성
        - 핵심 관심 사항을 변경없이 유지 가능

        - TimeTraceAop(시간 측정 로직) 원하는 곳에 공통 관심 사항을 적용
    - 보통 패키지 단위로 적용
    
~~~
@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring.service..*(..))") 
    // hello.hellospring.service 하위에 모두 적용하겠다
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
~~~

- AOP 동작 방식
    - 스프링 컨테이너에 등록시 앞단에 프록시라는 가짜단계를 추가로 등록한다
    - 스프링은 프록시 방식의 AOP
    - 자바코드를 박아주던게 BCI 방식의 AOP
