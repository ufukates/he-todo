package com.hepsiemlak.todo.repository;

import com.hepsiemlak.todo.domain.model.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.hepsiemlak.todo.util.AppUserRepositoryUtil.getAppUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class AppUserRepositoryTest extends TestcontainerBase {

    @Autowired
    private AppUserRepository appUserRepository;

    @BeforeEach
    public void init() {
        appUserRepository = appUserRepository.withCollection("_default");
    }

    @Test
    public void should_save_user() {
        System.out.println(couchbaseContainer.getContainerId() + " - " + couchbaseContainer.getConnectionString());

        //Given
        final AppUser saveAppUser = getAppUser();
        final AppUser appUser = getAppUser();
        appUserRepository.save(saveAppUser);

        //When
        AppUser actualResponse = appUserRepository.getAppUserByEmail(appUser.getEmail());

        // Then
        assertEquals(appUser.getEmail(), actualResponse.getEmail());
    }
}
