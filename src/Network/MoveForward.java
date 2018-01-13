package Network;

import Model.Card;
import Model.Game;
import Model.Pirate;
import Model.Player;

public class MoveForward implements Command {
    private Pirate pirate;
    private Card card;

    public MoveForward(Pirate pirate, Card card) {
        this.pirate = pirate;
        this.card = card;
    }

    @Override
    public void executeOn(Game game) {
        Player player = game.getPlayers().get(this.pirate.getPlayerIndex());
        Pirate pirate = player.getPirates().get(this.pirate.getIndex());
        Card card = null;
        for (Card c : player.getHand()) {
            if (c.getIndex() == this.card.getIndex())
                card = c;
        }
        game.moveForward(pirate, card);
    }
}
