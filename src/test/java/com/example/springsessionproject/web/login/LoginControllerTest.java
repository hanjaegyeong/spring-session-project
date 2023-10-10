package com.example.springsessionproject.web.login;

import com.example.springsessionproject.SessionConst;
import com.example.springsessionproject.domain.login.LoginService;
import com.example.springsessionproject.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    private LoginController loginController;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        loginService = Mockito.mock(LoginService.class);
        loginController = new LoginController(loginService);
        session = new MockHttpSession();
    }

    @Test
    public void testSessionSearchTime() {
        Member fakeMember = new Member("fakeUserId", "fakePassword");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(session);

        // Create a LoginForm object
        LoginForm loginForm = new LoginForm("fakeUserId", "fakePassword");

        // Create a BindingResult object
        BindingResult bindingResult = new BeanPropertyBindingResult(loginForm, "loginForm");

        when(loginService.login("fakeUserId", "fakePassword")).thenReturn(fakeMember);

        long startTime = System.currentTimeMillis();
        String viewName = loginController.login(loginForm, bindingResult, request);
        long endTime = System.currentTimeMillis();

        assertNotNull(session.getAttribute(SessionConst.LOGIN_MEMBER));
        assertEquals("redirect:/home", viewName);

        long searchTime = endTime - startTime;
        System.out.println("Session search time: " + searchTime + " milliseconds");
    }


    @RepeatedTest(50)
    @Test
    public void iterTestSessionSearchTime() {
        Member fakeMember = new Member("fakeUserId", "fakePassword");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(session);

        // Create a LoginForm object
        LoginForm loginForm = new LoginForm("fakeUserId", "fakePassword");

        // Create a BindingResult object
        BindingResult bindingResult = new BeanPropertyBindingResult(loginForm, "loginForm");

        when(loginService.login("fakeUserId", "fakePassword")).thenReturn(fakeMember);

        long startTime = System.currentTimeMillis();
        String viewName = loginController.login(loginForm, bindingResult, request);
        long endTime = System.currentTimeMillis();

        assertNotNull(session.getAttribute(SessionConst.LOGIN_MEMBER));
        assertEquals("redirect:/home", viewName);

        long searchTime = endTime - startTime;
        System.out.println("Session search time: " + searchTime + " milliseconds");
    }

    @Test
    public void iterAvgTestSessionSearchTime() {
        int numberOfTests = 200; // 테스트를 20번 반복 실행
        long totalSearchTime = 0; // 검색 시간 누적을 위한 변수

        for (int i = 0; i < numberOfTests; i++) {
            Member fakeMember = new Member("fakeUserId", "fakePassword");

            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setSession(session);

            // Create a LoginForm object
            LoginForm loginForm = new LoginForm("fakeUserId", "fakePassword");

            // Create a BindingResult object
            BindingResult bindingResult = new BeanPropertyBindingResult(loginForm, "loginForm");

            when(loginService.login("fakeUserId", "fakePassword")).thenReturn(fakeMember);

            long startTime = System.currentTimeMillis();
            String viewName = loginController.login(loginForm, bindingResult, request);
            long endTime = System.currentTimeMillis();

            assertNotNull(session.getAttribute(SessionConst.LOGIN_MEMBER));
            assertEquals("redirect:/home", viewName);

            long searchTime = endTime - startTime;
            totalSearchTime += searchTime;
            System.out.println("Session search time: " + searchTime + " milliseconds");
        }
        // 평균 검색 시간 계산 및 출력
        long averageSearchTime = totalSearchTime / numberOfTests;
        System.out.println("Average session search time: " + averageSearchTime + " milliseconds");

    }
}
