package com.example.demobot.Service;

import com.example.demobot.config.BotConfig;
import com.example.demobot.model.User;
import com.example.demobot.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DemoBot extends TelegramLongPollingBot {

   final BotConfig config;
   @Autowired
   private  UserRepository repository;

   static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n"+
           "You can execute commands from the main menu on the left or by taping command: \n\n"+
           "Type /start to see welcome message\n\n"+
           "Type /mydata to see data stored about yourself\n\n"+
           "Type /help to see this message again";

    public DemoBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start","Say hello"));
        listOfCommands.add(new BotCommand("/mydata","get your datasource"));
        listOfCommands.add(new BotCommand("/deletedata","delete your datasource"));
        listOfCommands.add(new BotCommand("/help","how to use this bot"));
        listOfCommands.add(new BotCommand("/settings","set your preferences"));
        try{
            this.execute(new SetMyCommands(listOfCommands,new BotCommandScopeDefault(), null));
        }
        catch (TelegramApiException e){
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText){
                case "/start":
                    registerUser(update.getMessage());
                    startCommandRecived(chatId,update.getMessage().getChat().getFirstName());
                    break;
                case "/help":
                    sendMessage(chatId, HELP_TEXT);
                    break;
                default: sendMessage(chatId,"Sorry i dont know how do another command");
            }
        }
    }

    private void registerUser(Message message) {
        if(repository.findById(message.getChatId()).isEmpty()){
            var chatId = message.getChatId();
            var chat = message.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            repository.save(user);
            log.info("User save: " + user);

        }
    }

    private void startCommandRecived(long chatId, String name){

        String answer = "Hi, " + name + ", nice to meet you!";
        log.info("Replied to user "+ name);
        sendMessage(chatId,answer);

    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try{
            execute(message);
        }
        catch (TelegramApiException e){
            log.error("Error occured: " + e.getMessage());
        }
    }
}
