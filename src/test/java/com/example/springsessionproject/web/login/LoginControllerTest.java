package com.example.springsessionproject.web.login;

import com.example.springsessionproject.SessionConst;
import com.example.springsessionproject.domain.login.LoginService;
import com.example.springsessionproject.domain.member.Member;
import com.example.springsessionproject.domain.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.hibernate.query.sqm.tree.SqmNode.log;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @Autowired
    private LoginController loginController;

    private MockHttpSession session;

    @MockBean
    private MemberRepository memberRepository; // @MockBean을 사용하여 Mock MemberRepository 주입


    @BeforeEach
    public void setUp() {
        loginService = Mockito.mock(LoginService.class);
        loginController = new LoginController(loginService);
        session = new MockHttpSession();
    }

    @Test
    public void testSessionSearchTime() {
        String userId = "fakeUserId";
        String password = "fakePassword";

        Member fakeMember = new Member(userId, password);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(session);

        // Create a LoginForm object
        LoginForm loginForm = new LoginForm(userId, password);

        // Create a BindingResult object
        BindingResult bindingResult = new BeanPropertyBindingResult(loginForm, "loginForm");

        when(loginService.login(userId, password)).thenReturn(fakeMember);

        long startTime = System.currentTimeMillis();
        String viewName = loginController.login(loginForm, bindingResult, request);
        long endTime = System.currentTimeMillis();

        assertNotNull(session.getAttribute(SessionConst.LOGIN_MEMBER));
        assertEquals("redirect:/home", viewName);

        long searchTime = endTime - startTime;
        log.info("Session search time: " + searchTime + " milliseconds");
    }

    @Test
    public void iterAvgTestSessionSearchTime() {
        int numberOfTests = 1000; // 테스트 반복실행 횟수
        long totalSearchTime = 0; // 검색 시간 누적을 위한 변수
        String userId = "fakeUserId";
        String password = "fakePassword";

        // 회원 가입
        Member fakeMember = new Member(userId, password);
        memberRepository.save(fakeMember);

        for (int i = 0; i < numberOfTests; i++) {

            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setSession(session);

            // LoginForm 객체 생성
            LoginForm loginForm = new LoginForm(userId, password);

            // BindingResult 객체 생성
            BindingResult bindingResult = new BeanPropertyBindingResult(loginForm, "loginForm");

            when(loginService.login(userId, password)).thenReturn(fakeMember);

            long startTime = System.currentTimeMillis();
            String viewName = loginController.login(loginForm, bindingResult, request);
            long endTime = System.currentTimeMillis();

            assertNotNull(session.getAttribute(SessionConst.LOGIN_MEMBER));
            assertEquals("redirect:/home", viewName);

            long searchTime = endTime - startTime;
            totalSearchTime += searchTime;
            log.info("Session search time: " + searchTime + " milliseconds");

            // 세션 클리어
            session.clearAttributes();

        }
        // 평균 검색 시간 계산 및 출력
        long averageSearchTime = totalSearchTime / numberOfTests;
        log.info("Average session search time: " + averageSearchTime + " milliseconds");

    }
}
