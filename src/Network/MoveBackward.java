package Network;

import Model.Game;
import Model.Pirate;

/**
 * Created by berku on 13.1.2018.
 */
public class MoveBackward implements Command {
    private Pirate pirate;

    public MoveBackward(Pirate pirate) {
        this.pirate = pirate;
    }

    @Override
    public void executeOn(Game game) {
        Pirate pirate = game.getPlayers().get(this.pirate.getPlayerIndex()).getPirates().get(this.pirate.getIndex());
        game.moveBackward(pirate);
    }
}
