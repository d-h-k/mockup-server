package BanpoXi.Dong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        System.out.println("hewllwe");
        model.addAttribute("data", "hello!!");
        return "hello";
    }


    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-temeplete";
    }


    @GetMapping("hello-string")
    @ResponseBody//존나중요!! 의미 : http프로토콜 바디부에 직접 이 데이터를 넣어 주겠다!!!
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;//hello + [name에 들어온 문자열]
    }


    //진짜는 지금부터야!!
    //문서가 아니라 데이터를 달라고 하면말이
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;//문자가 아닌 객체를 넘긴다!
        // http://localhost:8080/hello-api?name=muyaho 로 접속하면 JSON이라는 형태로 전달되는데
        // JSON 이라는 형식으로 이루어져 있는데, 이건 키-벨류 방식이라 가볍고 빠르다
        // 과거에는 XML방식이였는데 무겁다, 최근에는 거의 JSON 으로 통일됨.

        /*
        이게 어떻게 동작하는지에 대한 설명

        1.Hello 에서 게터&세터가 있는데 이게 바로 : 자바빈 규약임
          - 이게 프라이빗이라 바로 접근이 안되고 꼭 메서드를 통해 주고받는데
          - 다른용어로 프로퍼티 접근 방식 이라고도 함
        2. 당신이 http://localhost:8080/hello-api 로 접근하면
        3. 스프링 컨트롤러가 @ResponseBody 라는 이름의 에노테이션이 붙어있는걸 확인한다
          - 이전 템플릿(HTML문서)을리턴하는 방식이라면 문서의 이름을 리턴하면 됬지만
        4. 리턴이 문자열(String)이 아닌 객체라면?!
          - 기본 정책(셋팅)으로 설정되어있는데, 객체가 리턴된다면
          - 객체가 리턴된다면 이를 JSON으로 변환해 리턴하는게 기본 정책
        5. HttpMessageConverter(메세지 컨버터)가 동작는데
          - 문자라면 : 스트링 컨버터가 동작 (StringMttpMessageConverter)
          - 객체라면 : JSON 컨버터가 동작 (MappingJakson2HttpMessageConverter)
            - 객체를 제이슨으로 바꿔주는 라이브러리가 Jakson이라는 라이브러리고, 이건 버전2임 (구글에서 만든 GSON도 있긴 함)
        6. 위에서  JSON 으로 변경한걸 리턴해준다.

        */
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
/*

이번 강의 정리
: 이번강좌에서는 크게 3가를 배웠는데

1.정적 컨텐츠
- 파일 그대로 던짐

2.MVC와 템플릿엔진
- 템플릿 엔진이라는걸(여기서는 tymeleaf, 코쿼에서는 머스태취,핸들바) 쓰는데
- 템플릿엔진을 M V C 방식으로 쪼개서(모델 뷰 컨트롤러)
- 뷰를 템플릿엔진으로 HTML을 좀더 프로그래밍하게 랜더링해서
- 랜더링 된 문서를 클라이언트(고객)에게 전달 해 준다

3.API 방
- 보통 일반적으로 스프링개발 방식은 객체를 개발하는거고
- 제이슨으로 바꿔서 변환을 해주는거
- 뷰 없이 HTTP 바디에다가 박아주는 방식임

>> 이 세가지 방식이 크게 있고 나머지 더 디테일하게 배워나가자!!


 */



