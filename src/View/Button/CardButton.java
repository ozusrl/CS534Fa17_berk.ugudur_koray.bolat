package View.Button;

import Model.Card;

import java.awt.*;

/**
 * Created by berku on 6.1.2018.
 */
public class CardButton extends SpecialButton {
    private Card card;
    public CardButton(Card card, Image image) {
        super(image);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

}
