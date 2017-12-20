package Main;

import View.MainFrame;

import javax.swing.*;

public class Main {
    /*public static void main(String[] args){
        System.out.println("Hello pirates!");
        MainFrame mainFrame = new MainFrame();
        Game game = new Game(new Board(6,6),4,30,6);
*/


    private static void createAndShowGUI(){
        System.out.println("Hello pirates!");
        Main m1 = new Main();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}


