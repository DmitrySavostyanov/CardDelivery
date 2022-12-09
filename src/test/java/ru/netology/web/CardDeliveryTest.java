package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    private int daysToMeeting;

    public String dayMeeting(int daysToMeeting) {
        LocalDate dateOrder = LocalDate.now().plusDays(daysToMeeting);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String newDate = dtf.format(dateOrder);
        return newDate;
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:7080");
    }

    @Test
    void shouldFormCardDeliveryTest() {
        $("[data-test-id=city] input").setValue("Томск").pressEnter();
        $("[data-test-id=date] input").doubleClick().sendKeys(dayMeeting(3));
        $("[data-test-id=name] input").setValue("Савостьянов Дмитрий");
        $("[data-test-id=phone] input").setValue("+79131041698");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $(".notification").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + dayMeeting(3)));
    }
}