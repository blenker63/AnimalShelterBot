package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.repository.R_BotUser;
import pro.sky.telegrambot.repository.R_User;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private R_BotUser botUser;
    @Mock
    private R_User user;
    UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(botUser, user);
    }

    @Test
    void saveBotUser() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void savePhoneUser() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void findUserAndAnimal() {
    }
}