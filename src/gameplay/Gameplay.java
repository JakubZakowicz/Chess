package gameplay;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gameplay extends MouseAdapter {

    private final Board b;
    private final FigureMovement fm;

    public Gameplay(Board board) {
        b = board;
        fm = new FigureMovement(b);
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                if(e.getSource() == b.squares[i][j])
                    b.squares[i][j].setBorder(new LineBorder(Color.GREEN, 5));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                if(e.getSource() == b.squares[i][j])
                    b.squares[i][j].setBorder(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {

                fm.whitePawnMovement(e, i, j);
                fm.whiteRookMovement(e, i, j);
                fm.whiteBishopMovement(e, i, j);
                fm.whiteQueenMovement(e, i, j);
                fm.whiteKnightMovement(e, i, j);
                fm.whiteKingMovement(e, i, j);
                fm.whiteCastling(e);
                fm.blackPawnMovement(e, i, j);
                fm.blackRookMovement(e, i, j);
                fm.blackBishopMovement(e, i, j);
                fm.blackQueenMovement(e, i, j);
                fm.blackKnightMovement(e, i, j);
                fm.blackKingMovement(e, i, j);
                fm.blackCastling(e);


            }
        }

    }


}
