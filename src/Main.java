import Model.Board;

/**
 * Created by berku on 17.12.2017.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Hello pirates!");
        Board board = new Board(6,6);
        System.out.println(board.toString());
    }
}
