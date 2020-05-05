package gameplay;

import figures.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;



public class Board extends JPanel {

    public final JButton[][] squares = new JButton[8][8];
    public final BBishopB bBishopB = new BBishopB("Figures\\BlackBishopBlackBoard.png");
    public final BBishopW bBishopW = new BBishopW("Figures\\BlackBishopWhiteBoard.png");
    public final BKingB bKingB = new BKingB("Figures\\BlackKingBlackBoard.png");
    public final BKingW bKingW = new BKingW("Figures\\BlackKingWhiteBoard.png");
    public final BKnightB bKnightB = new BKnightB("Figures\\BlackKnightBlackBoard.png");
    public final BKnightW bKnightW = new BKnightW("Figures\\BlackKnightWhiteBoard.png");
    public final BPawnB bPawnB = new BPawnB("Figures\\BlackPawnBlackBoard.png");
    public final BPawnW bPawnW = new BPawnW("Figures\\BlackPawnWhiteBoard.png");
    public final BQueenB bQueenB = new BQueenB("Figures\\BlackQueenBlackBoard.png");
    public final BQueenW bQueenW = new BQueenW("Figures\\BlackQueenWhiteBoard.png");
    public final BRookB bRookB = new BRookB("Figures\\BlackRookBlackBoard.png");
    public final BRookW bRookW = new BRookW("Figures\\BlackRookWhiteBoard.png");
    public final WBishopB wBishopB = new WBishopB("Figures\\WhiteBishopBlackBoard.png");
    public final WBishopW wBishopW = new WBishopW("Figures\\WhiteBishopWhiteBoard.png");
    public final WKingB wKingB = new WKingB("Figures\\WhiteKingBlackBoard.png");
    public final WKingW wKingW = new WKingW("Figures\\WhiteKingWhiteBoard.png");
    public final WKnightB wKnightB = new WKnightB("Figures\\WhiteKnightBlackBoard.png");
    public final WKnightW wKnightW = new WKnightW("Figures\\WhiteKnightWhiteBoard.png");
    public final WPawnB wPawnB = new WPawnB("Figures\\WhitePawnBlackBoard.png");
    public final WPawnW wPawnW = new WPawnW("Figures\\WhitePawnWhiteBoard.png");
    public final WQueenB wQueenB = new WQueenB("Figures\\WhiteQueenBlackBoard.png");
    public final WQueenW wQueenW = new WQueenW("Figures\\WhiteQueenWhiteBoard.png");
    public final WRookB wRookB = new WRookB("Figures\\WhiteRookBlackBoard.png");
    public final WRookW wRookW = new WRookW("Figures\\WhiteRookWhiteBoard.png");
    public final BlackBoard blackBoard = new BlackBoard("Figures\\BlackBoard.png");
    public final WhiteBoard whiteBoard = new WhiteBoard("Figures\\WhiteBoard.png");
    public final Gameplay gameplay = new Gameplay(this);
    public final JButton[] whiteCaptureSlots = new JButton[16];
    public final JButton[] blackCaptureSlots = new JButton[16];


    public Board() {

        setPreferredSize(new Dimension(1500, 1080));
        setBackground(Color.BLACK);
        setLayout(null);
        initBoard();
        initWhiteCaptureSlots();
        initBlackCaptureSlots();
        addingListeners();
    }

    private void initBoard() {

        int x = 350, y = 75, size = 100;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                squares[i][j] = new JButton();
                squares[i][j].setBounds(x, y, size, size);

                add(squares[i][j]);
                x += size;
            }
            y += size;
            x = 350;
        }

        squares[0][0].setIcon(bRookW.getFigure());
        squares[0][1].setIcon(bKnightB.getFigure());
        squares[0][2].setIcon(bBishopW.getFigure());
        squares[0][3].setIcon(bQueenB.getFigure());
        squares[0][4].setIcon(bKingW.getFigure());
        squares[0][5].setIcon(bBishopB.getFigure());
        squares[0][6].setIcon(bKnightW.getFigure());
        squares[0][7].setIcon(bRookB.getFigure());
        for(int j = 0; j < 8; j++)
            if((1 + j) % 2 == 0) squares[1][j].setIcon(bPawnW.getFigure());
            else squares[1][j].setIcon(bPawnB.getFigure());

        for(int i = 2; i < 6; i++)
            for(int j = 0; j < 8; j++)
                if((i + j) % 2 == 0) squares[i][j].setIcon(whiteBoard.getFigure());
                else squares[i][j].setIcon(blackBoard.getFigure());

        for(int j = 0; j < 8; j++)
            if((6 + j) % 2 == 0) squares[6][j].setIcon(wPawnW.getFigure());
            else squares[6][j].setIcon(wPawnB.getFigure());

        squares[7][0].setIcon(wRookB.getFigure());
        squares[7][1].setIcon(wKnightW.getFigure());
        squares[7][2].setIcon(wBishopB.getFigure());
        squares[7][3].setIcon(wQueenW.getFigure());
        squares[7][4].setIcon(wKingB.getFigure());
        squares[7][5].setIcon(wBishopW.getFigure());
        squares[7][6].setIcon(wKnightB.getFigure());
        squares[7][7].setIcon(wRookW.getFigure());
    }



    private void addingListeners() {

        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++) squares[i][j].addMouseListener(gameplay);

    }

    private void initWhiteCaptureSlots() {

        int x = 100, y = 75;

        int j = 0;
        for(int i = 0; i < 16; i++) {

            whiteCaptureSlots[i] = new JButton();
            whiteCaptureSlots[i].setBounds(x, y, 100, 100);
            whiteCaptureSlots[i].setBackground(Color.BLACK);
            whiteCaptureSlots[i].setBorder(new LineBorder(Color.BLACK));
            whiteCaptureSlots[i].setEnabled(false);
            add(whiteCaptureSlots[i]);
            x += 100;
            j++;
            if(j == 2) {
                j = 0;
                x = 100;
                y += 100;
            }

        }
    }

    private void initBlackCaptureSlots() {
        int x = 1200, y = 75;

        int j = 0;
        for(int i = 0; i < 16; i++) {

            blackCaptureSlots[i] = new JButton();
            blackCaptureSlots[i].setBounds(x, y, 100, 100);
            blackCaptureSlots[i].setBackground(Color.BLACK);
            blackCaptureSlots[i].setBorder(new LineBorder(Color.BLACK));
            blackCaptureSlots[i].setEnabled(false);
            add(blackCaptureSlots[i]);
            x += 100;
            j++;
            if(j == 2) {
                j = 0;
                x = 1200;
                y += 100;
            }
        }
    }

}
