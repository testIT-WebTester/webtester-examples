package webtester.showcases.core.pageobjects;

import java.util.List;
import java.util.Random;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


public class RandomInteractionsPage extends PageObject {

    private static final Random RANDOM = new Random();

    @IdentifyUsing ( method = Method.ID_STARTS_WITH, value = "button", elementname = "Button" )
    List<Button> buttons;

    @IdentifyUsing ( method = Method.ID_STARTS_WITH, value = "textfield" )
    List<TextField> textfields;

    public RandomInteractionsPage clickRandomButton () {
        buttons.get(RANDOM.nextInt(buttons.size())).click();
        return this;
    }

    public RandomInteractionsPage setTextOfRandomTextField () {
        TextField textField1 = textfields.get(RANDOM.nextInt(textfields.size()));
        TextField textField2 = textfields.get(RANDOM.nextInt(textfields.size()));
        textField1.setText(textField2.getText());
        return this;
    }

}
