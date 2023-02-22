import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }
    @Test
    void fillFormTest() {
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("alena");
        $("#lastName").setValue("ivanova");
        $("#userEmail").setValue("ivanova@gmail.com");
        $("label[for=gender-radio-2]").click();
        $("#userNumber").setValue("8882222220");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("8");
        $(".react-datepicker__year-select").selectOptionByValue("1991");
        $(By.cssSelector("div.react-datepicker__day[aria-label='Choose Monday, September 2nd, 1991']")).click();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("label[for=hobbies-checkbox-1]").shouldHave(text("Sport")).click();
        File fileToUpload = new File("src/test/resources/images/photo_2023-02-13 16.34.46.jpeg");
        $("#uploadPicture").uploadFile(fileToUpload);
        $("#currentAddress").setValue("Moscow");
        $("#state").click();
        $("#state").$(byText("Haryana")).click();
        $("#city").click();
        $("#city").$(byText("Karnal")).click();
        $("#submit").click();


        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(text("alena ivanova"));
        $(".modal-body").shouldHave(text("ivanova@gmail.com"));
        $(".modal-body").shouldHave(text("Female"));
        $(".modal-body").shouldHave(text("8882222220"));
        $(".modal-body").shouldHave(text("2 September,1991"));
        $(".modal-body").shouldHave(text("Economics"));
        $(".modal-body").shouldHave(text("Sport"));
        $(".modal-body").shouldHave(text("photo_2023-02-13 16.34.46.jpeg"));
        $(".modal-body").shouldHave(text("Moscow"));
        $(".modal-body").shouldHave(text("Haryana Karnal"));
    }
}
