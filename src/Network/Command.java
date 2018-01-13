package Network;

import Model.Game;

import java.io.Serializable;

/**
 * Created by berku on 12.1.2018.
 */
public interface Command extends Serializable{
    void executeOn(Game game);
}

