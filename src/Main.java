import Model.Board;
import Model.Game;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello pirates!");
        Game game = new Game(new Board(6,6),4,180,6);
    }
}
