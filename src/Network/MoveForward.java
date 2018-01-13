package Network;

import Model.Card;
import Model.Game;
import Model.Pirate;
import Model.Player;

/**
 * Created by berku on 12.1.2018.
 */
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
        System.out.println("gelen kart id: " + this.card.getIndex());
        Card card = null;
        for(Card c : player.getHand()){
            System.out.println("karsilastirdigim kart id: " + c.getIndex());
            if(c.getIndex() == this.card.getIndex())
                card = c;
        }
        game.moveForward(pirate, card);
    }
}
