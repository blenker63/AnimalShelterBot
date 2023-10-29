package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.sky.telegrambot.service.ButtonService;
import pro.sky.telegrambot.service.UserService;
import pro.sky.telegrambot.standard.Commands;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TelegramBotUpdatesTest {

    @Mock
    private TelegramLongPollingBot bot;
    @Mock
    ButtonService buttonService;
    @Mock
    UserService userService;
    @Captor
    ArgumentCaptor<SendMessage> captor;
    @InjectMocks
    private TelegramBotUpdates botUpdates;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void getBotUsername() {
    }

    @Test
    void onUpdateReceivedTest() throws TelegramApiException {
        var upd = mock(Update.class);
        var callback = mock(CallbackQuery.class);
        var message = mock(Message.class);
        var chat = mock(Chat.class);
        when(upd.getCallbackQuery()).thenReturn(callback);
        when(callback.getData()).thenReturn(Commands.START.getCommand());
        when(callback.getMessage()).thenReturn(message);
        when(message.getChatId()).thenReturn(1L);
        botUpdates.onUpdateReceived(upd);
        //verify(bot).execute(captor.capture());
       // verify(bot, times(1)).execute(botUpdates);
       // var value = captor.getValue();
      //  assertEquals(1L, value.getChatId());
        //assertEquals(Commands.START.getCommand(), value.getText());
    }

    @Test
    void sendMessage() {
    }

    @Test
    void outInformForAnimal() {
    }

    @Test
    void choiceAnimal() {
    }

    @Test
    void checkingReports() {
    }
}