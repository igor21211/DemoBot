package com.example.demobot.Service;

import com.example.demobot.Logic.*;
import com.example.demobot.Repository.JavaAnswerAndQuestionRepository;
import com.example.demobot.Repository.JavaSubtopicsRepository;
import com.example.demobot.Repository.QaAnswerAndQuestionRepository;
import com.example.demobot.config.BotConfig;

import com.example.demobot.model.Java.JavaAnswerAndQuestion;
import com.example.demobot.model.QA.QaAnswerAndQuestion;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Slf4j
@Component
public class DemoBot extends TelegramLongPollingBot {

    final BotConfig config;

    private Long result;

    final String JAVA_CALLBACK = "JAVA_ANSWER";
    final String JAVA_CALLBACK_MORE = "JAVA_MORE";
    final String QA_CALLBACK = "QA_ANSWER";
    final String QA_CALLBACK_MORE = "QA_MORE";
    Python python = new Python();
    Devops devops = new Devops();
    Qa qa = new Qa();
    JavaScript javaScript = new JavaScript();
    Java java = new Java();
    @Autowired
    private QaAnswerAndQuestionRepository qaAnswerAndQuestionRepository;
    @Autowired
    private JavaAnswerAndQuestionRepository javaAnswerAndQuestionRepository;

    static final String HELP_TEXT = "Этот бот создавался в обучающих целях по сфере IT\n\n" +
            "Здесь вы можете проверить ваши знания в языках таких как\n\n" +
            "Java, Python, JavaScript, Devops and QA" +
            "Type /start - to see Welcome page and study menu\n\n" +
            "Type /help to see this message again";

    public DemoBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Say hello"));
        listOfCommands.add(new BotCommand("/help", "how to use this bot"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
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
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText) {
                case "/start@UKDemoBot":
                    startCommandRecived(chatId);
                    break;
                case "/help@UKDemoBot":
                    sendMessage(chatId, HELP_TEXT);
                    break;
                case "/start":
                    startCommandRecived(chatId);
                    break;
                case "/help":
                    sendMessage(chatId, HELP_TEXT);
                    break;
                case "Java":
                    extracted(java.javaSub(chatId));
                    break;
                case "QA":
                    extracted(qa.qaSub(chatId));
                    break;
                case "Python":
                    extracted(python.pythonSub(chatId));
                    break;
                case "Devops":
                    extracted(devops.devopsSub(chatId));
                    break;
                case "JavaScript":
                    extracted(javaScript.javascriptSub(chatId));
                    break;
                case "ООП":
                    result = Long.valueOf(getJavaDatabyId(1L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "JVM":
                    result = Long.valueOf(getJavaDatabyId(2L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Java Core":
                    result = Long.valueOf(getJavaDatabyId(3L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Java Collections Framework":
                    result = Long.valueOf(getJavaDatabyId(4L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Java 8":
                    result = Long.valueOf(getJavaDatabyId(5L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Потоки ввода-вывода в Java":
                    result = Long.valueOf(getJavaDatabyId(6L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Сериализация":
                    result = Long.valueOf(getJavaDatabyId(7L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Многопоточность":
                    result = Long.valueOf(getJavaDatabyId(8L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Servlets, JSP, JSTL":
                    result = Long.valueOf(getJavaDatabyId(9L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Базы данных":
                    result = Long.valueOf(getJavaDatabyId(10L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "SQL":
                    result = Long.valueOf(getJavaDatabyId(11L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "JDBC":
                    result = Long.valueOf(getJavaDatabyId(12L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Тестирование":
                    result = Long.valueOf(getJavaDatabyId(13L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Журналирование":
                    result = Long.valueOf(getJavaDatabyId(14L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "UML":
                    result = Long.valueOf(getJavaDatabyId(15L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "XML":
                    result = Long.valueOf(getJavaDatabyId(16L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Шаблоны проектирования":
                    result = Long.valueOf(getJavaDatabyId(17L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Основы HTML":
                    result = Long.valueOf(getJavaDatabyId(18L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Основы CSS":
                    result = Long.valueOf(getJavaDatabyId(19L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Основы Web":
                    result = Long.valueOf(getJavaDatabyId(20L));
                    getJavaQuestingAndAnswer(chatId, result,JAVA_CALLBACK);
                    break;
                case "Теория тестирования":
                    result = Long.valueOf(getQaDataById(1L));
                    getQAQuestingAndAnswer(chatId, result,QA_CALLBACK);
                    break;
                case "Тестовая документация":
                    result = Long.valueOf(getQaDataById(2L));
                    getQAQuestingAndAnswer(chatId, result,QA_CALLBACK);
                    break;
                case "Методологии разработки ПО":
                    result = Long.valueOf(getQaDataById(3L));
                    getQAQuestingAndAnswer(chatId, result,QA_CALLBACK);
                    break;
                case "AQA (Automation QA)":
                    result = Long.valueOf(getQaDataById(4L));
                    getQAQuestingAndAnswer(chatId, result,QA_CALLBACK);
                    break;
                case "WEB":
                    result = Long.valueOf(getQaDataById(5L));
                    getQAQuestingAndAnswer(chatId, result,QA_CALLBACK);
                    break;
                case "Mobile":
                    result = Long.valueOf(getQaDataById(6L));
                    getQAQuestingAndAnswer(chatId, result,QA_CALLBACK);
                    break;
                default:
                    sendMessage(chatId, "Sorry i dont know how do another command");
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callBackData.equals(JAVA_CALLBACK)) {
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                JavaAnswerAndQuestion javaanQ = javaAnswerAndQuestionRepository.findById(result).orElse(null);
                message.setText(javaanQ.getAnswer());
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                var answer = new InlineKeyboardButton();
                answer.setText(EmojiParser.parseToUnicode("Продолжаем?" + ":fire:"));
                answer.setCallbackData(JAVA_CALLBACK_MORE);
                rowInline.add(answer);
                rowsInLine.add(rowInline);
                markupInline.setKeyboard(rowsInLine);
                message.setReplyMarkup(markupInline);
                extracted(message);
            } else if (callBackData.equals(JAVA_CALLBACK_MORE)) {
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                JavaAnswerAndQuestion javaanQ = javaAnswerAndQuestionRepository.findById(result).orElse(null);
                result = getJavaDatabyId(javaanQ.getJavaSubtopics().getId());
                getJavaQuestingAndAnswer(chatId,result,JAVA_CALLBACK);
                extracted(message);
            }else if(callBackData.equals(QA_CALLBACK)){
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                QaAnswerAndQuestion QaAns = qaAnswerAndQuestionRepository.findById(result).orElse(null);
                message.setText(QaAns.getAnswer());
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                var answer = new InlineKeyboardButton();
                answer.setText(EmojiParser.parseToUnicode("Продолжаем?" + ":fire:"));
                answer.setCallbackData(QA_CALLBACK_MORE);
                rowInline.add(answer);
                rowsInLine.add(rowInline);
                markupInline.setKeyboard(rowsInLine);
                message.setReplyMarkup(markupInline);
                extracted(message);
            } else if (callBackData.equals(QA_CALLBACK_MORE)) {
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                QaAnswerAndQuestion QaAns = qaAnswerAndQuestionRepository.findById(result).orElse(null);
                result = getQaDataById(QaAns.getQaSubtopics().getId());
                getQAQuestingAndAnswer(chatId,result,QA_CALLBACK);
                extracted(message);
            }
        }
    }
    private void extracted(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occured: " + e.getMessage());
        }
    }
    private void startCommandRecived(long chatId) {
        //https://emojipedia.org/
        String answer = EmojiParser.parseToUnicode("Hi, nice to meet you!" + ":heart:\n\n"+
                                                "So choose subtopics, and good luck\n\n" +
                                                ":fire:"+":fire:"+":fire:"
        );
        //    String answer = "Hi, " + name + ", nice to meet you!";
        log.info("Replied to user");
        sendMessage(chatId, answer);
    }
    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Java");
        row.add("QA");
        keyboardRows.add(row);
        row = new KeyboardRow();
        row.add("Python");
        row.add("Devops");
        row.add("JavaScript");
        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);
        extracted(message);
    }
    private void getJavaQuestingAndAnswer(Long chatId, Long id, String callBack) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        JavaAnswerAndQuestion question = javaAnswerAndQuestionRepository.findById(id).orElse(null);
        message.setText(question.getQuestion());
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        var answer = new InlineKeyboardButton();
        answer.setText(EmojiParser.parseToUnicode("Подсмотреть ответ" + ":white_check_mark:"));
        answer.setCallbackData(callBack);
        rowInline.add(answer);
        rowsInLine.add(rowInline);
        markupInline.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInline);
        extracted(message);
    }

    public Long getJavaDatabyId(Long id) {
        List<JavaAnswerAndQuestion> questionList = javaAnswerAndQuestionRepository.findBySubId(id);
        Random random = new Random();
        return questionList.get(random.nextInt(questionList.size())).getId();
    }
    public Long getQaDataById(Long id) {
        List<QaAnswerAndQuestion> questionList = qaAnswerAndQuestionRepository.findBySubId(id);
        Random random = new Random();
        return questionList.get(random.nextInt(questionList.size())).getId();
    }

    private void getQAQuestingAndAnswer(Long chatId, Long id, String callBack) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        QaAnswerAndQuestion question = qaAnswerAndQuestionRepository.findById(id).orElse(null);
        message.setText(question.getQuestion());
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        var answer = new InlineKeyboardButton();
        answer.setText(EmojiParser.parseToUnicode("Подсмотреть ответ" + ":white_check_mark:"));
        answer.setCallbackData(callBack);
        rowInline.add(answer);
        rowsInLine.add(rowInline);
        markupInline.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInline);
        extracted(message);
    }
}
