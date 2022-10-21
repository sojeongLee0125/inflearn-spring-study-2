package spring.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.core.common.MyLogger;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    // 프록시 모드로 가짜 프록시 클래스를 만들어서 미리 주입

    // private final ObjectProvider<MyLogger> myLoggerProvider;
    // 즉 스프링 실행 시점에 request 스코프 빈은 아직 생성되지 않는다.
    // requestScope 이므로 웹 요청이 없으면 의존관계 주입이 안된다.


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestUrl = request.getRequestURL().toString();

        // CGLIB 라이브러리로 가짜 프록시 객체가 주입되어 있음
        // 가짜 프록시 객체는 요청이 오면 진짜 빈을 요청한다.
        System.out.println("myLogger = " + myLogger.getClass());

        myLogger.setRequestURL(requestUrl);

        // 컨트롤러에 요청이와서 request가 살아있는 시점에 호출
        // MyLogger myLogger = myLoggerProvider.getObject();

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
