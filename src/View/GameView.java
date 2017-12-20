package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameView extends JPanel {
    private Image seg_0;
    private Image seg_1;
    private Image seg_start;

    public GameView() throws IOException {
        seg_0 = ImageIO.read(new File("img/seg_014.png"));
        seg_1 = ImageIO.read(new File("img/seg_113.png"));
        seg_start = ImageIO.read(new File("img/seg_start2.png"));

        this.setBackground(new Color(73, 204, 212));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int segmentSize = 8;
        g.drawImage(seg_start, 0, 60, 96, 288, this);
        for (int i = 0; i < segmentSize; i++) {
            int x = i * 96;
            int y = ((((i % 2) + 1) % 2) * 288)+60;
            Image img = (i % 2 == 0) ? seg_0 : seg_1;
            g.drawImage(img, x, y, 192, 288, this);
        }

    }
}
