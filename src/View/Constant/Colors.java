package View.Constant;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by berku on 7.1.2018.
 */
public class Colors {
    private static ArrayList<Color> colors;

    private static void setColors() {
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
    }

    public static ArrayList<Color> getColors() {
        if(colors.size()==0)
            setColors();
        return colors;
    }
}
