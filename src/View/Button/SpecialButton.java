package View.Button;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

public class SpecialButton extends JButton {
    String imagePath;
    Image image;
    Color color;

    public SpecialButton(String imagePath) {
        this.imagePath = imagePath;
        createButton();
    }

    public SpecialButton(Image image) {
        this.image = image;
        createButton();
    }

    public SpecialButton(Color color){
        this.color = color;
        createColorButton();
    }
    private void createButton() {
        try {
            Image img = null;
            if (image != null) {
                img = image;
            } else {
                img = ImageIO.read(new File(imagePath));
            }
            this.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.setBackground(new Color(50, 100, 50));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        this.setBorder(emptyBorder);
    }
    private void createColorButton() {
        this.setBackground(color);

        Border emptyBorder = BorderFactory.createLineBorder(new Color(44, 16, 2));
        this.setBorder(emptyBorder);
    }
    public void setBackColor(int r, int b, int g) {
        this.setBackground(new Color(r, b, g));
    }

}
