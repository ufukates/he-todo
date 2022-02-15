package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.domain.model.AppUser;
import com.hepsiemlak.todo.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.hepsiemlak.todo.util.AuthenticationServiceUtil.getAppUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AppUserServiceTest {

    private AppUserService appUserService;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private SecurityContextHolder securityContextHolder;

    @BeforeEach
    public void setupMock() {
        appUserService = new AppUserService(appUserRepository);
    }

    @Test
    public void should_returnUserEmail(){
        //Given
        final String userMail = "test@test.com";

        //When
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        securityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(userMail);

        String actualResponse = appUserService.getUserEmail();

        //Then
        assertEquals(userMail, actualResponse);
    }

    @Test
    public void should_returnUserId(){
        //Given
        final String userMail = "test@test.com";
        final AppUser appUser = getAppUser();

        //When
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        securityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(userMail);
        Mockito.when(appUserRepository.getAppUserByEmail(appUser.getEmail())).thenReturn(appUser);

        String actualResponse = appUserService.getUserId();

        //Then
        assertEquals(appUser.getId(), actualResponse);
    }
}
