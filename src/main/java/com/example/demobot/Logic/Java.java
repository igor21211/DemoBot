package com.example.demobot.Logic;

import com.example.demobot.Repository.JavaAnswerAndQuestionRepository;
import com.example.demobot.Repository.JavaSubtopicsRepository;
import com.example.demobot.model.Java.JavaAnswerAndQuestion;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Java {


    public SendMessage javaSub(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите направление по которому хотите потренироватся");


        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();

        row.add("ООП");
        row.add("JVM");
        row.add("Java Core");

        keyboardRows.add(row);
        row = new KeyboardRow();

        row.add("Java Collections Framework");
        row.add("Java 8");
        row.add("Потоки ввода-вывода в Java");

        keyboardRows.add(row);

        row = new KeyboardRow();

        row.add("Сериализация");
        row.add("Многопоточность");
        row.add("Servlets, JSP, JSTL");

        keyboardRows.add(row);

        row = new KeyboardRow();

        row.add("Базы данных");
        row.add("SQL");
        row.add("JDBC");

        keyboardRows.add(row);

        row = new KeyboardRow();

        row.add("Тестирование");
        row.add("Журналирование");
        row.add("UML");

        keyboardRows.add(row);

        row = new KeyboardRow();

        row.add("XML");
        row.add("Шаблоны проектирования");
        row.add("Основы HTML");

        keyboardRows.add(row);
        row = new KeyboardRow();

        row.add("Основы CSS");
        row.add("Основы Web");

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);

        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

}
