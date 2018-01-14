package View.Constant;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by berku on 7.1.2018.
 */
public class Colors {
    private ArrayList<Color> colors;

    public Colors() {
        this.colors = new ArrayList<>();
        setColors();
    }

    private void setColors() {
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
    }

    public ArrayList<Color> getColors() {
        return colors;
    }
}
