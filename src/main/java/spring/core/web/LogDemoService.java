package spring.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import spring.core.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
        // 같은 http 요청이면 같은 스프링 빈을 반환해준다.
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id =" + id);

    }
}
