package gameplay;

import figures.Figure;

import java.awt.event.MouseEvent;


class FigureMovement {

    Board b;                            // Load a board
    int x, y;                           // Variables to set coordinates of the figure
    public Figures pickedFigure;        // Variable to set a picked figure on the board
    private boolean canWhiteCastle = true;
    private boolean canBlackCastle = true;
    private boolean isWhiteTurn = true;
    private int whiteIndex = 0;         // Index of the white captured slot
    private int blackIndex = 0;         // Index of the black captured slot
    private boolean isBlackCheck = false;
    private boolean isWhiteCheck = false;
    private boolean[][] availableSquares = new boolean[8][8];

    FigureMovement(Board board) {
        b = board;
    }

////////////////////////////////////////// White Figures ///////////////////////////////////////////////////////////////

    void whitePawnMovement(MouseEvent e, int i, int j) {

        // picking the Figure
        if(e.getSource() == b.squares[i][j] && isWhiteTurn) {
            if(b.squares[i][j].getIcon().equals(b.wPawnB.getFigure())) {
                pickedFigure = Figures.WhitePawn;
                x = j;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.wPawnW.getFigure())) {
                pickedFigure = Figures.WhitePawn;
                x = j;
                y = i;
            }
        }

        // placing the Figure
        if(pickedFigure == Figures.WhitePawn) {

            movingWhitePawn(e, b.wPawnB, b.wPawnW);
            check();
        }
    }

    void whiteRookMovement(MouseEvent e, int i, int j) {

        if(e.getSource() == b.squares[i][j] && isWhiteTurn) {

            if(b.squares[i][j].getIcon().equals(b.wRookW.getFigure())) {       // if white figure on the white board is picked
                pickedFigure = Figures.WhiteRook;                              // set picked figure
                x = j;                                                         // and its coordinates;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.wRookB.getFigure())) {       // if white figure on the black board is picked
                pickedFigure = Figures.WhiteRook;                              // set picked figure
                x = j;                                                         // and its coordinates;
                y = i;
            }
        }

        //////////////////// Placing Figures ////////////////////
        if(pickedFigure == Figures.WhiteRook) {

            movingUp(e, b.wRookB, b.wRookW);
            movingDown(e, b.wRookB, b.wRookW);
            movingLeft(e, b.wRookB, b.wRookW);
            movingRight(e, b.wRookB, b.wRookW);
            check();

        }
    }


    void whiteBishopMovement(MouseEvent e, int i, int j) {

        // Picking a bishop
        if(e.getSource() == b.squares[i][j] && isWhiteTurn) {

            if(b.squares[i][j].getIcon().equals(b.wBishopW.getFigure())) {       // if white figure is picked
                pickedFigure = Figures.WhiteBishop;                              // set picked figure
                x = j;                                                           // and its coordinates;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.wBishopB.getFigure())) {       // if white figure is picked
                pickedFigure = Figures.WhiteBishop;                              // set picked figure
                x = j;                                                           // and its coordinates;
                y = i;
            }
        }


        // placing a Bishop
        if(pickedFigure == Figures.WhiteBishop) {

            movingDiagonalLeftUp(e, b.wBishopB, b.wBishopW);
            movingDiagonalRightUp(e, b.wBishopB, b.wBishopW);
            movingDiagonalLeftDown(e, b.wBishopB, b.wBishopW);
            movingDiagonalRightDown(e, b.wBishopB, b.wBishopW);
        }
    }

    void whiteQueenMovement(MouseEvent e, int i, int j) {

        if(e.getSource() == b.squares[i][j] && isWhiteTurn) {

            if(b.squares[i][j].getIcon().equals(b.wQueenW.getFigure())) {       // if white figure is picked
                pickedFigure = Figures.WhiteQueen;                              // set picked figure
                x = j;                                                          // and its coordinates;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.wQueenB.getFigure())) {       // if white figure is picked
                pickedFigure = Figures.WhiteQueen;                              // set picked figure
                x = j;                                                          // and its coordinates;
                y = i;
            }
        }

        if(pickedFigure == Figures.WhiteQueen) {

            movingUp(e, b.wQueenB, b.wQueenW);
            movingDown(e, b.wQueenB, b.wQueenW);
            movingLeft(e, b.wQueenB, b.wQueenW);
            movingRight(e, b.wQueenB, b.wQueenW);
            movingDiagonalLeftUp(e, b.wQueenB, b.wQueenW);
            movingDiagonalRightUp(e, b.wQueenB, b.wQueenW);
            movingDiagonalLeftDown(e, b.wQueenB, b.wQueenW);
            movingDiagonalRightDown(e, b.wQueenB, b.wQueenW);

        }
    }


    void whiteKnightMovement(MouseEvent e, int i, int j) {

        if(e.getSource() == b.squares[i][j] && isWhiteTurn) {

            if(b.squares[i][j].getIcon().equals(b.wKnightW.getFigure())) {       // if white figure is picked
                pickedFigure = Figures.WhiteKnight;                              // set picked figure
                x = j;                                                           // and its coordinates;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.wKnightB.getFigure())) {       // if white figure is picked
                pickedFigure = Figures.WhiteKnight;                              // set picked figure
                x = j;                                                           // and its coordinates;
                y = i;
            }
        }

        if(pickedFigure == Figures.WhiteKnight) {

            knightMovement(e, b.wKnightB, b.wKnightW);

        }
    }


    void whiteKingMovement(MouseEvent e, int i, int j) {

        if(e.getSource() == b.squares[i][j] && isWhiteTurn) {

            if(b.squares[i][j].getIcon().equals(b.wKingW.getFigure())) {         // if white figure is picked
                pickedFigure = Figures.WhiteKing;                                // set picked figure
                x = j;                                                           // and its coordinates;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.wKingB.getFigure())) {         // if white figure is picked
                pickedFigure = Figures.WhiteKing;                                // set picked figure
                x = j;                                                           // and its coordinates;
                y = i;
            }
        }

        if(pickedFigure == Figures.WhiteKing) {

            kingMovement(e, b.wKingB, b.wKingW);
        }
    }


    void whiteCastling(MouseEvent e) {
        if(b.squares[7][0].getIcon() != b.wRookB.getFigure() || b.squares[7][7].getIcon() != b.wRookW.getFigure())
            canWhiteCastle = false; // if rooks are moved, so we cant do a castling

        // if king is moved, then castling is not possible
        if(b.squares[7][4].getIcon() != b.wKingB.getFigure()) canWhiteCastle = false;


        if(canWhiteCastle && e.getSource() == b.squares[7][6]
                && b.squares[7][5].getIcon() == b.whiteBoard.getFigure()
                && b.squares[7][6].getIcon() == b.blackBoard.getFigure()
                && pickedFigure == Figures.WhiteKing) {

            b.squares[7][6].setIcon(b.wKingB.getFigure());
            b.squares[7][4].setIcon(b.blackBoard.getFigure());
            b.squares[7][5].setIcon(b.wRookW.getFigure());
            b.squares[7][7].setIcon(b.whiteBoard.getFigure());
            isWhiteTurn = false;
        }

        if(canWhiteCastle && e.getSource() == b.squares[7][1]
                && b.squares[7][3].getIcon() == b.whiteBoard.getFigure()
                && b.squares[7][2].getIcon() == b.blackBoard.getFigure()
                && b.squares[7][1].getIcon() == b.whiteBoard.getFigure()
                && pickedFigure == Figures.WhiteKing) {

            b.squares[7][1].setIcon(b.wKingW.getFigure());
            b.squares[7][4].setIcon(b.blackBoard.getFigure());
            b.squares[7][2].setIcon(b.wRookB.getFigure());
            b.squares[7][0].setIcon(b.blackBoard.getFigure());
            isWhiteTurn = false;
        }
    }




///////////////////////////////////// Black Figures ////////////////////////////////////////////////////////////////////

    void blackPawnMovement(MouseEvent e, int i, int j) {

        // picking the Figure
        if(e.getSource() == b.squares[i][j] && !isWhiteTurn) {
            if(b.squares[i][j].getIcon().equals(b.bPawnB.getFigure())) {
                pickedFigure = Figures.BlackPawn;
                x = j;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.bPawnW.getFigure())) {
                pickedFigure = Figures.BlackPawn;
                x = j;
                y = i;
            }
        }

        // placing the Figure
        if(pickedFigure == Figures.BlackPawn) {

            movingBlackPawn(e, b.bPawnB, b.bPawnW);
            check();
        }
    }

    void blackRookMovement(MouseEvent e, int i, int j) {

        // picking the Figure
        if(e.getSource() == b.squares[i][j] && !isWhiteTurn) {
            if(b.squares[i][j].getIcon().equals(b.bRookB.getFigure())) {
                pickedFigure = Figures.BlackRook;
                x = j;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.bRookW.getFigure())) {
                pickedFigure = Figures.BlackRook;
                x = j;
                y = i;
            }
        }

        // placing the Figure
        if(pickedFigure == Figures.BlackRook) {

            movingUp(e, b.bRookB, b.bRookW);
            movingDown(e, b.bRookB, b.bRookW);
            movingLeft(e, b.bRookB, b.bRookW);
            movingRight(e, b.bRookB, b.bRookW);
        }
    }

    void blackBishopMovement(MouseEvent e, int i, int j) {

        // picking the Figure
        if(e.getSource() == b.squares[i][j] && !isWhiteTurn) {
            if(b.squares[i][j].getIcon().equals(b.bBishopB.getFigure())) {
                pickedFigure = Figures.BlackBishop;
                x = j;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.bBishopW.getFigure())) {
                pickedFigure = Figures.BlackBishop;
                x = j;
                y = i;
            }
        }

        // placing the Figure
        if(pickedFigure == Figures.BlackBishop) {

            movingDiagonalLeftUp(e, b.bBishopB, b.bBishopW);
            movingDiagonalRightUp(e, b.bBishopB, b.bBishopW);
            movingDiagonalLeftDown(e, b.bBishopB, b.bBishopW);
            movingDiagonalRightDown(e, b.bBishopB, b.bBishopW);
        }
    }

    void blackQueenMovement(MouseEvent e, int i, int j) {

        // picking the Figure
        if(e.getSource() == b.squares[i][j] && !isWhiteTurn) {
            if(b.squares[i][j].getIcon().equals(b.bQueenB.getFigure())) {
                pickedFigure = Figures.BlackQueen;
                x = j;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.bQueenW.getFigure())) {
                pickedFigure = Figures.BlackQueen;
                x = j;
                y = i;
            }
        }

        // placing the Figure
        if(pickedFigure == Figures.BlackQueen) {

            movingUp(e, b.bQueenB, b.bQueenW);
            movingDown(e, b.bQueenB, b.bQueenW);
            movingLeft(e, b.bQueenB, b.bQueenW);
            movingRight(e, b.bQueenB, b.bQueenW);
            movingDiagonalLeftUp(e, b.bQueenB, b.bQueenW);
            movingDiagonalRightUp(e, b.bQueenB, b.bQueenW);
            movingDiagonalLeftDown(e, b.bQueenB, b.bQueenW);
            movingDiagonalRightDown(e, b.bQueenB, b.bQueenW);
        }
    }

    void blackKnightMovement(MouseEvent e, int i, int j) {

        // picking the Figure
        if(e.getSource() == b.squares[i][j] && !isWhiteTurn) {
            if(b.squares[i][j].getIcon().equals(b.bKnightB.getFigure())) {
                pickedFigure = Figures.BlackKnight;
                x = j;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.bKnightW.getFigure())) {
                pickedFigure = Figures.BlackKnight;
                x = j;
                y = i;
            }
        }

        // placing the Figure
        if(pickedFigure == Figures.BlackKnight) {

            knightMovement(e, b.bKnightB, b.bKnightW);
        }
    }


    void blackKingMovement(MouseEvent e, int i, int j) {

        // picking the Figure
        if(e.getSource() == b.squares[i][j] && !isWhiteTurn) {
            if(b.squares[i][j].getIcon().equals(b.bKingB.getFigure())) {
                pickedFigure = Figures.BlackKing;
                x = j;
                y = i;
            }

            if(b.squares[i][j].getIcon().equals(b.bKingW.getFigure())) {
                pickedFigure = Figures.BlackKing;
                x = j;
                y = i;
            }
        }

        // placing the Figure
        if(pickedFigure == Figures.BlackKing) {

            kingMovement(e, b.bKingB, b.bKingW);
        }
    }

    void blackCastling(MouseEvent e) {

        if(b.squares[0][0].getIcon() != b.bRookW.getFigure() || b.squares[0][7].getIcon() != b.bRookB.getFigure())
            canBlackCastle = false; // if rooks are moved, so we cant do a castling

        // if king is moved, then castling is not possible
        if(b.squares[0][4].getIcon() != b.bKingW.getFigure()) canBlackCastle = false;


        if(canBlackCastle && e.getSource() == b.squares[0][6]
                && b.squares[0][5].getIcon() == b.blackBoard.getFigure()
                && b.squares[0][6].getIcon() == b.whiteBoard.getFigure()
                && pickedFigure == Figures.BlackKing) {

            b.squares[0][6].setIcon(b.bKingW.getFigure());
            b.squares[0][4].setIcon(b.whiteBoard.getFigure());
            b.squares[0][5].setIcon(b.bRookB.getFigure());
            b.squares[0][7].setIcon(b.blackBoard.getFigure());
            isWhiteTurn = true;
        }

        if(canBlackCastle && e.getSource() == b.squares[0][1]
                && b.squares[0][3].getIcon() == b.blackBoard.getFigure()
                && b.squares[0][2].getIcon() == b.whiteBoard.getFigure()
                && b.squares[0][1].getIcon() == b.blackBoard.getFigure()
                && pickedFigure == Figures.BlackKing) {

            b.squares[0][1].setIcon(b.bKingB.getFigure());
            b.squares[0][4].setIcon(b.whiteBoard.getFigure());
            b.squares[0][2].setIcon(b.bRookW.getFigure());
            b.squares[0][0].setIcon(b.whiteBoard.getFigure());
            isWhiteTurn = true;
        }
    }


//////////////////////////////////////// Other helpful methods /////////////////////////////////////////////////////////
    private boolean isWhiteFigure(int i, int j) {

        if(b.squares[i][j].getIcon().equals(b.wBishopB.getFigure())) return true;
        if(b.squares[i][j].getIcon().equals(b.wBishopW.getFigure())) return true;
        if(b.squares[i][j].getIcon().equals(b.wKnightB.getFigure())) return true;
        if(b.squares[i][j].getIcon().equals(b.wKnightW.getFigure())) return true;
        if(b.squares[i][j].getIcon().equals(b.wKingB.getFigure())) return true;
        if(b.squares[i][j].getIcon().equals(b.wKingW.getFigure())) return true;
        if(b.squares[i][j].getIcon().equals(b.wQueenB.getFigure())) return true;
        if(b.squares[i][j].getIcon().equals(b.wQueenW.getFigure())) return true;
        if(b.squares[i][j].getIcon().equals(b.wRookB.getFigure())) return true;
        if(b.squares[i][j].getIcon().equals(b.wRookW.getFigure())) return true;
        if(b.squares[i][j].getIcon().equals(b.wPawnB.getFigure())) return true;
        return b.squares[i][j].getIcon().equals(b.wPawnW.getFigure());
    }

    private boolean isBlackFigure(int y, int x) {

        return b.squares[y][x].getIcon() != b.whiteBoard.getFigure()
                && b.squares[y][x].getIcon() != b.blackBoard.getFigure()
                && !isWhiteFigure(y, x);
    }

    private void movingUp(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        int maxUpDistance = 1;
        for(int k = y - 1; k > -1; k--) {

            if(b.squares[k][x].getIcon().equals(b.whiteBoard.getFigure()) || b.squares[k][x].getIcon().equals(b.blackBoard.getFigure()))
                maxUpDistance++;
            else break;
        }


        try {
            for(int k = y - 1; k > y - 1 - maxUpDistance; k--) {

                if(isWhiteFigure(y, x)) {
                    if(e.getSource() == b.squares[k][x] && !isWhiteFigure(k, x)
                            && b.squares[k][x].getIcon() != b.bKingW.getFigure()
                            && b.squares[k][x].getIcon() != b.bKingB.getFigure()) {

                        capturingAFigure(k, x);
                        if((k + x) % 2 == 0) b.squares[k][x].setIcon(figureOnWhite.getFigure());
                        else b.squares[k][x].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = false;
                    }
                }

                if(isBlackFigure(y, x)) {

                    if(e.getSource() == b.squares[k][x] && !isBlackFigure(k, x)
                            && b.squares[k][x].getIcon() != b.wKingW.getFigure()
                            && b.squares[k][x].getIcon() != b.wKingB.getFigure()) {

                        capturingAFigure(k, x);
                        if((k + x) % 2 == 0) b.squares[k][x].setIcon(figureOnWhite.getFigure());
                        else b.squares[k][x].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = true;
                    }
                }

            }
        } catch(ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void movingDown(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        int maxDownDistance = 1;
        for(int k = y + 1; k < 8; k++) {

            if(b.squares[k][x].getIcon().equals(b.whiteBoard.getFigure()) || b.squares[k][x].getIcon().equals(b.blackBoard.getFigure()))
                maxDownDistance++;
            else break;
        }


        try {
            for(int k = y + 1; k < y + 1 + maxDownDistance; k++) {

                if(isWhiteFigure(y, x)) {

                    if(e.getSource() == b.squares[k][x] && !isWhiteFigure(k, x)
                            && b.squares[k][x].getIcon() != b.bKingW.getFigure()
                            && b.squares[k][x].getIcon() != b.bKingB.getFigure()) {

                        capturingAFigure(k, x);
                        if((k + x) % 2 == 0) b.squares[k][x].setIcon(figureOnWhite.getFigure());
                        else b.squares[k][x].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = false;
                    }
                }

                if(isBlackFigure(y, x)) {

                    if(e.getSource() == b.squares[k][x] && !isBlackFigure(k, x)
                            && b.squares[k][x].getIcon() != b.wKingW.getFigure()
                            && b.squares[k][x].getIcon() != b.wKingB.getFigure()) {

                        capturingAFigure(k, x);
                        if((k + x) % 2 == 0) b.squares[k][x].setIcon(figureOnWhite.getFigure());
                        else b.squares[k][x].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = true;
                    }
                }

            }
        } catch(ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void movingLeft(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        int maxLeftDistance = 1;
        for(int k = x - 1; k > -1; k--) {

            if(b.squares[y][k].getIcon().equals(b.whiteBoard.getFigure()) || b.squares[y][k].getIcon().equals(b.blackBoard.getFigure()))
                maxLeftDistance++;
            else break;
        }


        try {
            for(int k = x - 1; k > x - 1 - maxLeftDistance; k--) {

                if(isWhiteFigure(y, x)) {

                    if(e.getSource() == b.squares[y][k] && !isWhiteFigure(y, k)
                            && b.squares[y][k].getIcon() != b.bKingW.getFigure()
                            && b.squares[y][k].getIcon() != b.bKingB.getFigure()) {

                        capturingAFigure(y, k);
                        if((k + y) % 2 == 0) b.squares[y][k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y][k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = false;
                    }
                }

                if(isBlackFigure(y, x)) {

                    if(e.getSource() == b.squares[y][k] && !isBlackFigure(y, k)
                            && b.squares[y][k].getIcon() != b.wKingW.getFigure()
                            && b.squares[y][k].getIcon() != b.wKingB.getFigure()) {

                        capturingAFigure(y, k);
                        if((k + y) % 2 == 0) b.squares[y][k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y][k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = true;
                    }

                }


            }
        } catch(ArrayIndexOutOfBoundsException ignored) {
        }
    }


    private void movingRight(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        int maxRightDistance = 1;
        for(int k = x + 1; k < 8; k++) {

            if(b.squares[y][k].getIcon().equals(b.whiteBoard.getFigure()) || b.squares[y][k].getIcon().equals(b.blackBoard.getFigure()))
                maxRightDistance++;
            else break;
        }


        try {
            for(int k = x + 1; k < x + 1 + maxRightDistance; k++) {

                if(isWhiteFigure(y, x)) {
                    if(e.getSource() == b.squares[y][k] && !isWhiteFigure(y, k)
                            && b.squares[y][k].getIcon() != b.bKingW.getFigure()
                            && b.squares[y][k].getIcon() != b.bKingB.getFigure()) {

                        capturingAFigure(y, k);
                        if((k + y) % 2 == 0) b.squares[y][k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y][k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = false;
                    }
                }

                if(isBlackFigure(y, x)) {

                    if(e.getSource() == b.squares[y][k] && !isBlackFigure(y, k)
                            && b.squares[y][k].getIcon() != b.wKingW.getFigure()
                            && b.squares[y][k].getIcon() != b.wKingB.getFigure()) {

                        capturingAFigure(y, k);
                        if((k + y) % 2 == 0) b.squares[y][k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y][k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = true;
                    }
                }

            }
        } catch(ArrayIndexOutOfBoundsException ignored) {
        }
    }




    private void movingDiagonalLeftUp(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        //////////////////////////////// checking number of possible moves ////////////////////////
        int maxDiagonalDistance = 1;
        try {
            for(int k = 1; k < 8; k++) {

                if(b.squares[y - k][x - k].getIcon().equals(b.whiteBoard.getFigure()) || b.squares[y - k][x - k].getIcon().equals(b.blackBoard.getFigure()))
                    maxDiagonalDistance++;
                else break;
            }

        } catch(ArrayIndexOutOfBoundsException ignored) {
        }

        //////////////////////////////////////// Placing a Figure /////////////////////////////////////////////////////
        try {

            for(int k = 0; k <= maxDiagonalDistance; k++) {

                if(isWhiteFigure(y, x)) {
                    if(e.getSource() == b.squares[y - k][x - k] && !isWhiteFigure(y - k, x - k)
                            && b.squares[y - k][x - k].getIcon() != b.bKingW.getFigure()
                            && b.squares[y - k][x - k].getIcon() != b.bKingB.getFigure()) {

                        capturingAFigure(y - k, x - k);
                        if(((y - k) + (x - k)) % 2 == 0) b.squares[y - k][x - k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y - k][x - k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = false;
                    }
                }

                if(isBlackFigure(y, x)) {

                    if(e.getSource() == b.squares[y - k][x - k] && !isBlackFigure(y - k, x - k)
                            && b.squares[y - k][x - k].getIcon() != b.wKingW.getFigure()
                            && b.squares[y - k][x - k].getIcon() != b.wKingB.getFigure()) {

                        capturingAFigure(y - k, x - k);
                        if(((y - k) + (x - k)) % 2 == 0) b.squares[y - k][x - k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y - k][x - k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = true;
                    }
                }
            }
        } catch(ArrayIndexOutOfBoundsException ignored) {
        }
    }


    private void movingDiagonalRightUp(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        //////////////////////////////// checking number of possible moves ////////////////////////
        int maxDiagonalDistance = 1;
        try {
            for(int k = 1; k < 8; k++) {

                if(b.squares[y - k][x + k].getIcon().equals(b.whiteBoard.getFigure()) || b.squares[y - k][x + k].getIcon().equals(b.blackBoard.getFigure()))
                    maxDiagonalDistance++;
                else break;
            }

        } catch(ArrayIndexOutOfBoundsException ignored) {
        }

        //////////////////////////////////////// Placing a Figure /////////////////////////////////////////////////////
        try {

            for(int k = 0; k <= maxDiagonalDistance; k++) {

                if(isWhiteFigure(y, x)) {

                    if(e.getSource() == b.squares[y - k][x + k] && !isWhiteFigure(y - k, x + k)
                            && b.squares[y - k][x + k].getIcon() != b.bKingW.getFigure()
                            && b.squares[y - k][x + k].getIcon() != b.bKingB.getFigure()) {

                        capturingAFigure(y - k, x + k);
                        if(((y - k) + (x + k)) % 2 == 0) b.squares[y - k][x + k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y - k][x + k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = false;
                    }
                }

                if(isBlackFigure(y, x)) {

                    if(e.getSource() == b.squares[y - k][x + k] && !isBlackFigure(y - k, x + k)
                            && b.squares[y - k][x + k].getIcon() != b.wKingW.getFigure()
                            && b.squares[y - k][x + k].getIcon() != b.wKingB.getFigure()) {

                        capturingAFigure(y - k, x + k);
                        if(((y - k) + (x + k)) % 2 == 0) b.squares[y - k][x + k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y - k][x + k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = true;
                    }

                }

            }
        } catch(ArrayIndexOutOfBoundsException ignored) {
        }
    }


    private void movingDiagonalLeftDown(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        //////////////////////////////// checking number of possible moves ////////////////////////
        int maxDiagonalDistance = 1;
        try {
            for(int k = 1; k < 8; k++) {

                if(b.squares[y + k][x - k].getIcon().equals(b.whiteBoard.getFigure()) || b.squares[y + k][x - k].getIcon().equals(b.blackBoard.getFigure()))
                    maxDiagonalDistance++;
                else break;
            }

        } catch(ArrayIndexOutOfBoundsException ignored) {
        }

        //////////////////////////////////////// Placing a Figure /////////////////////////////////////////////////////
        try {

            for(int k = 0; k <= maxDiagonalDistance; k++) {

                if(isWhiteFigure(y, x)) {

                    if(e.getSource() == b.squares[y + k][x - k] && !isWhiteFigure(y + k, x - k)
                            && b.squares[y + k][x - k].getIcon() != b.bKingW.getFigure()
                            && b.squares[y + k][x - k].getIcon() != b.bKingB.getFigure()) {

                        capturingAFigure(y + k, x - k);
                        if(((y + k) + (x - k)) % 2 == 0) b.squares[y + k][x - k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y + k][x - k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = false;
                    }
                }

                if(isBlackFigure(y, x)) {

                    if(e.getSource() == b.squares[y + k][x - k] && !isBlackFigure(y + k, x - k)
                            && b.squares[y + k][x - k].getIcon() != b.wKingW.getFigure()
                            && b.squares[y + k][x - k].getIcon() != b.wKingB.getFigure()) {

                        capturingAFigure(y + k, x - k);
                        if(((y + k) + (x - k)) % 2 == 0) b.squares[y + k][x - k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y + k][x - k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = true;
                    }
                }

            }
        } catch(ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void movingDiagonalRightDown(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        //////////////////////////////// checking number of possible moves ////////////////////////
        int maxDiagonalDistance = 1;
        try {
            for(int k = 1; k < 8; k++) {

                if(b.squares[y + k][x + k].getIcon().equals(b.whiteBoard.getFigure()) || b.squares[y + k][x + k].getIcon().equals(b.blackBoard.getFigure()))
                    maxDiagonalDistance++;
                else break;
            }

        } catch(ArrayIndexOutOfBoundsException ignored) {
        }

        //////////////////////////////////////// Placing a Figure /////////////////////////////////////////////////////
        try {

            for(int k = 0; k <= maxDiagonalDistance; k++) {

                if(isWhiteFigure(y, x)) {

                    if(e.getSource() == b.squares[y + k][x + k] && !isWhiteFigure(y + k, x + k)
                            && b.squares[y + k][x + k].getIcon() != b.bKingW.getFigure()
                            && b.squares[y + k][x + k].getIcon() != b.bKingB.getFigure()) {

                        capturingAFigure(y + k, x + k);
                        if(((y + k) + (x + k)) % 2 == 0) b.squares[y + k][x + k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y + k][x + k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = false;
                    }
                }

                if(isBlackFigure(y, x)) {

                    if(e.getSource() == b.squares[y + k][x + k] && !isBlackFigure(y + k, x + k)
                            && b.squares[y + k][x + k].getIcon() != b.wKingW.getFigure()
                            && b.squares[y + k][x + k].getIcon() != b.wKingB.getFigure()) {

                        capturingAFigure(y + k, x + k);
                        if(((y + k) + (x + k)) % 2 == 0) b.squares[y + k][x + k].setIcon(figureOnWhite.getFigure());
                        else b.squares[y + k][x + k].setIcon(figureOnBlack.getFigure());

                        if(b.squares[y][x].getIcon().equals(figureOnBlack.getFigure())) b.squares[y][x].setIcon(b.blackBoard.getFigure());
                        else b.squares[y][x].setIcon(b.whiteBoard.getFigure());

                        isWhiteTurn = true;
                    }
                }

            }
        } catch(ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void knightMovement(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        if(isWhiteFigure(y, x)) {

            try {

                if(e.getSource() == b.squares[y - 2][x - 1] && !isWhiteFigure(y - 2, x - 1)
                        && b.squares[y - 2][x - 1].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 2][x - 1].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y - 2, x - 1);
                    if(((y - 2) + (x - 1)) % 2 == 0) {
                        b.squares[y - 2][x - 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y - 2][x - 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {

                if(e.getSource() == b.squares[y + 2][x - 1] && !isWhiteFigure(y + 2, x - 1)
                        && b.squares[y + 2][x - 1].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 2][x - 1].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y + 2, x - 1);
                    if(((y + 2) + (x - 1)) % 2 == 0) {
                        b.squares[y + 2][x - 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y + 2][x - 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {

                if(e.getSource() == b.squares[y - 2][x + 1] && !isWhiteFigure(y - 2, x + 1)
                        && b.squares[y - 2][x + 1].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 2][x + 1].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y - 2, x + 1);
                    if(((y - 2) + (x + 1)) % 2 == 0) {
                        b.squares[y - 2][x + 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y - 2][x + 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }



            try {

                if(e.getSource() == b.squares[y + 2][x + 1] && !isWhiteFigure(y + 2, x + 1)
                        && b.squares[y + 2][x + 1].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 2][x + 1].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y + 2, x + 1);
                    if(((y + 2) + (x + 1)) % 2 == 0) {
                        b.squares[y + 2][x + 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y + 2][x + 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {

                if(e.getSource() == b.squares[y - 1][x - 2] && !isWhiteFigure(y - 1, x - 2)
                        && b.squares[y - 1][x - 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 1][x - 2].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y - 1, x - 2);
                    if(((y - 1) + (x - 2)) % 2 == 0) {
                        b.squares[y - 1][x - 2].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y - 1][x - 2].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {

                if(e.getSource() == b.squares[y + 1][x - 2] && !isWhiteFigure(y + 1, x - 2)
                        && b.squares[y + 1][x - 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 1][x - 2].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y + 1, x - 2);
                    if(((y + 1) + (x + 2)) % 2 == 0) {
                        b.squares[y + 1][x - 2].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y + 1][x - 2].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {

                if(e.getSource() == b.squares[y - 1][x + 2] && !isWhiteFigure(y - 1, x + 2)
                        && b.squares[y - 1][x + 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 1][x + 2].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y - 1, x + 2);
                    if(((y - 1) + (x + 2)) % 2 == 0) {
                        b.squares[y - 1][x + 2].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y - 1][x + 2].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {

                if(e.getSource() == b.squares[y + 1][x + 2] && !isWhiteFigure(y + 1, x + 2)
                        && b.squares[y + 1][x + 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 1][x + 2].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y + 1, x + 2);
                    if(((y + 1) + (x + 2)) % 2 == 0) {
                        b.squares[y + 1][x + 2].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y + 1][x + 2].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

        }

        if(isBlackFigure(y, x)) {

            try {

                if(e.getSource() == b.squares[y - 2][x - 1] && !isBlackFigure(y - 2, x - 1)
                        && b.squares[y - 2][x - 1].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 2][x - 1].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y - 2, x - 1);
                    if(((y - 2) + (x - 1)) % 2 == 0) {
                        b.squares[y - 2][x - 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y - 2][x - 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {

                if(e.getSource() == b.squares[y + 2][x - 1] && !isBlackFigure(y + 2, x - 1)
                        && b.squares[y + 2][x - 1].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 2][x - 1].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y + 2, x - 1);
                    if(((y + 2) + (x - 1)) % 2 == 0) {
                        b.squares[y + 2][x - 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y + 2][x - 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {

                if(e.getSource() == b.squares[y - 2][x + 1] && !isBlackFigure(y - 2, x + 1)
                        && b.squares[y - 2][x + 1].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 2][x + 1].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y - 2, x + 1);
                    if(((y - 2) + (x + 1)) % 2 == 0) {
                        b.squares[y - 2][x + 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y - 2][x + 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }



            try {

                if(e.getSource() == b.squares[y + 2][x + 1] && !isBlackFigure(y + 2, x + 1)
                        && b.squares[y + 2][x + 1].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 2][x + 1].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y + 2, x + 1);
                    if(((y + 2) + (x + 1)) % 2 == 0) {
                        b.squares[y + 2][x + 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y + 2][x + 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {

                if(e.getSource() == b.squares[y - 1][x - 2] && !isBlackFigure(y - 1, x - 2)
                        && b.squares[y - 1][x - 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 1][x - 2].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y - 1, x - 2);
                    if(((y - 1) + (x - 2)) % 2 == 0) {
                        b.squares[y - 1][x - 2].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y - 1][x - 2].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {

                if(e.getSource() == b.squares[y + 1][x - 2] && !isBlackFigure(y + 1, x - 2)
                        && b.squares[y + 1][x - 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 1][x - 2].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y + 1, x - 2);
                    if(((y + 1) + (x + 2)) % 2 == 0) {
                        b.squares[y + 1][x - 2].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y + 1][x - 2].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {

                if(e.getSource() == b.squares[y - 1][x + 2] && !isBlackFigure(y - 1, x + 2)
                        && b.squares[y - 1][x + 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 1][x + 2].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y - 1, x + 2);
                    if(((y - 1) + (x + 2)) % 2 == 0) {
                        b.squares[y - 1][x + 2].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y - 1][x + 2].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {

                if(e.getSource() == b.squares[y + 1][x + 2] && !isBlackFigure(y + 1, x + 2)
                        && b.squares[y + 1][x + 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 1][x + 2].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y + 1, x + 2);
                    if(((y + 1) + (x + 2)) % 2 == 0) {
                        b.squares[y + 1][x + 2].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    else {
                        b.squares[y + 1][x + 2].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }

            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

        }

    }

    private void kingMovement(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        if(isWhiteFigure(y, x)) {

            try {
                if(e.getSource() == b.squares[y - 1][x] && !isWhiteFigure(y - 1, x)
                        && b.squares[y - 2][x].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 2][x].getIcon() != b.bKingB.getFigure()
                        && b.squares[y - 2][x + 1].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 2][x + 1].getIcon() != b.bKingB.getFigure()
                        && b.squares[y - 2][x - 1].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 2][x - 1].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y - 1, x);
                    if(((y - 1) + x) % 2 == 0) {
                        b.squares[y - 1][x].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    } else {
                        b.squares[y - 1][x].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {
                if(e.getSource() == b.squares[y + 1][x] && !isWhiteFigure(y + 1, x)
                        && b.squares[y + 2][x].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 2][x].getIcon() != b.bKingB.getFigure()
                        && b.squares[y + 2][x + 1].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 2][x + 1].getIcon() != b.bKingB.getFigure()
                        && b.squares[y + 2][x - 1].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 2][x - 1].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y + 1, x);
                    if(((y + 1) + x) % 2 == 0) {
                        b.squares[y + 1][x].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    } else {
                        b.squares[y + 1][x].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {
                if(e.getSource() == b.squares[y][x - 1] && !isWhiteFigure(y, x - 1)
                        && b.squares[y][x - 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y][x - 2].getIcon() != b.bKingB.getFigure()
                        && b.squares[y - 1][x - 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 1][x - 2].getIcon() != b.bKingB.getFigure()
                        && b.squares[y + 1][x - 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 1][x - 2].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y, x - 1);
                    if((y + (x - 1)) % 2 == 0) {
                        b.squares[y][x - 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    } else {
                        b.squares[y][x - 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {
                if(e.getSource() == b.squares[y][x + 1] && !isWhiteFigure(y, x + 1)
                        && b.squares[y][x + 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y][x + 2].getIcon() != b.bKingB.getFigure()
                        && b.squares[y - 1][x + 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 1][x + 2].getIcon() != b.bKingB.getFigure()
                        && b.squares[y + 1][x + 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 1][x + 2].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y, x + 1);
                    if((y + (x + 1)) % 2 == 0) {
                        b.squares[y][x + 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    } else {
                        b.squares[y][x + 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {
                if(e.getSource() == b.squares[y + 1][x + 1] && !isWhiteFigure(y + 1, x + 1)
                        && b.squares[y + 2][x + 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 2][x + 2].getIcon() != b.bKingB.getFigure()
                        && b.squares[y + 2][x].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 2][x].getIcon() != b.bKingB.getFigure()
                        && b.squares[y][x - 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y][x - 2].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y + 1, x + 1);
                    if(((y + 1) + (x + 1)) % 2 == 0) {
                        b.squares[y + 1][x + 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    } else {
                        b.squares[y + 1][x + 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {
                if(e.getSource() == b.squares[y - 1][x - 1] && !isWhiteFigure(y - 1, x - 1)
                        && b.squares[y - 2][x - 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 2][x - 2].getIcon() != b.bKingB.getFigure()
                        && b.squares[y - 2][x].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 2][x].getIcon() != b.bKingB.getFigure()
                        && b.squares[y][x + 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y][x + 2].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y - 1, x - 1);
                    if(((y - 1) + (x - 1)) % 2 == 0) {
                        b.squares[y - 1][x - 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    } else {
                        b.squares[y - 1][x - 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {
                if(e.getSource() == b.squares[y + 1][x - 1] && !isWhiteFigure(y + 1, x - 1)
                        && b.squares[y + 2][x - 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 2][x - 2].getIcon() != b.bKingB.getFigure()
                        && b.squares[y + 2][x].getIcon() != b.bKingW.getFigure()
                        && b.squares[y + 2][x].getIcon() != b.bKingB.getFigure()
                        && b.squares[y][x - 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y][x - 2].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y + 1, x - 1);
                    if(((y + 1) + (x - 1)) % 2 == 0) {
                        b.squares[y + 1][x - 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    } else {
                        b.squares[y + 1][x - 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {
                if(e.getSource() == b.squares[y - 1][x + 1] && !isWhiteFigure(y - 1, x + 1)
                        && b.squares[y - 2][x + 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 2][x + 2].getIcon() != b.bKingB.getFigure()
                        && b.squares[y - 2][x].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 2][x].getIcon() != b.bKingB.getFigure()
                        && b.squares[y - 2][x + 1].getIcon() != b.bKingW.getFigure()
                        && b.squares[y - 2][x - 1].getIcon() != b.bKingB.getFigure()
                        && b.squares[y][x + 2].getIcon() != b.bKingW.getFigure()
                        && b.squares[y][x + 2].getIcon() != b.bKingB.getFigure()) {

                    capturingAFigure(y - 1, x + 1);
                    if(((y - 1) + (x + 1)) % 2 == 0) {
                        b.squares[y - 1][x + 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    } else {
                        b.squares[y - 1][x + 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }
        }

        if(isBlackFigure(y, x)) {

            try {
                if(e.getSource() == b.squares[y - 1][x] && !isBlackFigure(y - 1, x)
                        && b.squares[y - 2][x].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 2][x].getIcon() != b.wKingB.getFigure()
                        && b.squares[y - 2][x - 1].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 2][x - 1].getIcon() != b.wKingB.getFigure()
                        && b.squares[y - 2][x + 1].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 2][x + 1].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y - 1, x);
                    if(((y - 1) + x) % 2 == 0) {
                        b.squares[y - 1][x].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    } else {
                        b.squares[y - 1][x].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {
                if(e.getSource() == b.squares[y + 1][x] && !isBlackFigure(y + 1, x)
                        && b.squares[y + 2][x].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 2][x].getIcon() != b.wKingB.getFigure()
                        && b.squares[y + 2][x - 1].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 2][x - 1].getIcon() != b.wKingB.getFigure()
                        && b.squares[y + 2][x + 1].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 2][x + 1].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y + 1, x);
                    if(((y + 1) + x) % 2 == 0) {
                        b.squares[y + 1][x].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    } else {
                        b.squares[y + 1][x].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {
                if(e.getSource() == b.squares[y][x - 1] && !isBlackFigure(y, x - 1)
                        && b.squares[y][x - 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y][x - 2].getIcon() != b.wKingB.getFigure()
                        && b.squares[y - 1][x - 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 1][x - 2].getIcon() != b.wKingB.getFigure()
                        && b.squares[y + 1][x - 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 1][x - 2].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y, x - 1);
                    if((y + (x - 1)) % 2 == 0) {
                        b.squares[y][x - 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    } else {
                        b.squares[y][x - 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {
                if(e.getSource() == b.squares[y][x + 1] && !isBlackFigure(y, x + 1)
                        && b.squares[y][x + 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y][x + 2].getIcon() != b.wKingB.getFigure()
                        && b.squares[y - 1][x + 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 1][x + 2].getIcon() != b.wKingB.getFigure()
                        && b.squares[y + 1][x + 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 1][x + 2].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y, x + 1);
                    if((y + (x + 1)) % 2 == 0) {
                        b.squares[y][x + 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    } else {
                        b.squares[y][x + 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {
                if(e.getSource() == b.squares[y + 1][x + 1] && !isBlackFigure(y + 1, x + 1)
                        && b.squares[y + 2][x + 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 2][x + 2].getIcon() != b.wKingB.getFigure()
                        && b.squares[y + 2][x].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 2][x].getIcon() != b.wKingB.getFigure()
                        && b.squares[y][x - 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y][x - 2].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y + 1, x + 1);
                    if(((y + 1) + (x + 1)) % 2 == 0) {
                        b.squares[y + 1][x + 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    } else {
                        b.squares[y + 1][x + 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {
                if(e.getSource() == b.squares[y - 1][x - 1] && !isBlackFigure(y - 1, x - 1)
                        && b.squares[y - 2][x - 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 2][x - 2].getIcon() != b.wKingB.getFigure()
                        && b.squares[y - 2][x].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 2][x].getIcon() != b.wKingB.getFigure()
                        && b.squares[y][x + 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y][x + 2].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y - 1, x - 1);
                    if(((y - 1) + (x - 1)) % 2 == 0) {
                        b.squares[y - 1][x - 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    } else {
                        b.squares[y - 1][x - 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }

            try {
                if(e.getSource() == b.squares[y + 1][x - 1] && !isBlackFigure(y + 1, x - 1)
                        && b.squares[y + 2][x - 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 2][x - 2].getIcon() != b.wKingB.getFigure()
                        && b.squares[y + 2][x].getIcon() != b.wKingW.getFigure()
                        && b.squares[y + 2][x].getIcon() != b.wKingB.getFigure()
                        && b.squares[y][x - 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y][x - 2].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y + 1, x - 1);
                    if(((y + 1) + (x - 1)) % 2 == 0) {
                        b.squares[y + 1][x - 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    } else {
                        b.squares[y + 1][x - 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }


            try {
                if(e.getSource() == b.squares[y - 1][x + 1] && !isBlackFigure(y - 1, x + 1)
                        && b.squares[y - 2][x + 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 2][x + 2].getIcon() != b.wKingB.getFigure()
                        && b.squares[y - 2][x].getIcon() != b.wKingW.getFigure()
                        && b.squares[y - 2][x].getIcon() != b.wKingB.getFigure()
                        && b.squares[y][x + 2].getIcon() != b.wKingW.getFigure()
                        && b.squares[y][x + 2].getIcon() != b.wKingB.getFigure()) {

                    capturingAFigure(y - 1, x + 1);
                    if(((y - 1) + (x + 1)) % 2 == 0) {
                        b.squares[y - 1][x + 1].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    } else {
                        b.squares[y - 1][x + 1].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {
            }
        }


    }

    private void movingWhitePawn(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        try {

            // Putting figure on the picked square and setting a blank board on the previous location
            if(e.getSource() == b.squares[y - 1][x] && !isBlackFigure(y - 1, x) && !isWhiteFigure(y - 1, x)) {

                if(((y - 1) + x) % 2 == 0) {
                    b.squares[y - 1][x].setIcon(figureOnWhite.getFigure());
                    b.squares[y][x].setIcon(b.blackBoard.getFigure());
                } else {
                    b.squares[y - 1][x].setIcon(figureOnBlack.getFigure());
                    b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                }
                isWhiteTurn = false;
            }

        }catch (ArrayIndexOutOfBoundsException ignored) {
        }

        // First Double move
        try {
            if(y - 2 == 4 && isWhiteTurn) {
                if(e.getSource() == b.squares[y - 2][x] && !isBlackFigure(y - 2, x) && !isWhiteFigure(y - 2, x)) {

                    if(((y - 1) + x) % 2 == 0) {
                        b.squares[y - 2][x].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    } else {
                        b.squares[y - 2][x].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = false;
                }
            }

        }catch (ArrayIndexOutOfBoundsException ignored) {
        }



        // capturing
        try {

            if(e.getSource() == b.squares[y - 1][x - 1] && isBlackFigure(y - 1, x - 1)
                    && b.squares[y - 1][x - 1].getIcon() != b.bKingW.getFigure()
                    && b.squares[y - 1][x - 1].getIcon() != b.bKingB.getFigure()) {

                capturingAFigure(y - 1, x - 1);
                if(((y - 1) + (x - 1)) % 2 == 0) {
                    b.squares[y - 1][x - 1].setIcon(figureOnWhite.getFigure());
                    b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                } else {
                    b.squares[y - 1][x - 1].setIcon(figureOnBlack.getFigure());
                    b.squares[y][x].setIcon(b.blackBoard.getFigure());
                }
                isWhiteTurn = false;
            }

            if(e.getSource() == b.squares[y - 1][x + 1] && isBlackFigure(y - 1, x + 1)
                    && b.squares[y - 1][x + 1].getIcon() != b.bKingW.getFigure()
                    && b.squares[y - 1][x + 1].getIcon() != b.bKingB.getFigure()) {

                capturingAFigure(y - 1, x + 1);
                if(((y - 1) + (x + 1)) % 2 == 0) {
                    b.squares[y - 1][x + 1].setIcon(figureOnWhite.getFigure());
                    b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                } else {
                    b.squares[y - 1][x + 1].setIcon(figureOnBlack.getFigure());
                    b.squares[y][x].setIcon(b.blackBoard.getFigure());
                }
                isWhiteTurn = false;
            }

        }catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void movingBlackPawn(MouseEvent e, Figure figureOnBlack, Figure figureOnWhite) {

        try {

            // Putting figure on the picked square and setting a blank board on the previous location
            if(e.getSource() == b.squares[y + 1][x] && !isBlackFigure(y + 1, x) && !isWhiteFigure(y + 1, x)) {

                if(((y + 1) + x) % 2 == 0) {
                    b.squares[y + 1][x].setIcon(figureOnWhite.getFigure());
                    b.squares[y][x].setIcon(b.blackBoard.getFigure());
                } else {
                    b.squares[y + 1][x].setIcon(figureOnBlack.getFigure());
                    b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                }
                isWhiteTurn = true;
            }

        }catch (ArrayIndexOutOfBoundsException ignored) {
        }

        // First Double move
        try {
            if(y + 2 == 3 && !isWhiteTurn) {
                if(e.getSource() == b.squares[y + 2][x] && !isBlackFigure(y + 2, x) && !isWhiteFigure(y + 2, x)) {

                    if(((y + 1) + x) % 2 == 0) {
                        b.squares[y + 2][x].setIcon(figureOnBlack.getFigure());
                        b.squares[y][x].setIcon(b.blackBoard.getFigure());
                    } else {
                        b.squares[y + 2][x].setIcon(figureOnWhite.getFigure());
                        b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                    }
                    isWhiteTurn = true;
                }
            }

        }catch (ArrayIndexOutOfBoundsException ignored) {
        }



        // capturing
        try {

            if(e.getSource() == b.squares[y + 1][x - 1] && isWhiteFigure(y + 1, x - 1)
                    && b.squares[y + 1][x - 1].getIcon() != b.bKingW.getFigure()
                    && b.squares[y + 1][x - 1].getIcon() != b.bKingB.getFigure()) {

                capturingAFigure(y + 1, x - 1);
                if(((y + 1) + (x - 1)) % 2 == 0) {
                    b.squares[y + 1][x - 1].setIcon(figureOnWhite.getFigure());
                    b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                } else {
                    b.squares[y + 1][x - 1].setIcon(figureOnBlack.getFigure());
                    b.squares[y][x].setIcon(b.blackBoard.getFigure());
                }
                isWhiteTurn = true;
            }

            if(e.getSource() == b.squares[y + 1][x + 1] && isWhiteFigure(y + 1, x + 1)
                    && b.squares[y + 1][x + 1].getIcon() != b.bKingW.getFigure()
                    && b.squares[y + 1][x + 1].getIcon() != b.bKingB.getFigure()) {

                capturingAFigure(y + 1, x + 1);
                if(((y + 1) + (x + 1)) % 2 == 0) {
                    b.squares[y + 1][x + 1].setIcon(figureOnWhite.getFigure());
                    b.squares[y][x].setIcon(b.whiteBoard.getFigure());
                } else {
                    b.squares[y + 1][x + 1].setIcon(figureOnBlack.getFigure());
                    b.squares[y][x].setIcon(b.blackBoard.getFigure());
                }
                isWhiteTurn = true;
            }

        }catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void capturingAFigure(int y, int x) {

        // Capturing white figures
        if(isWhiteFigure(y, x)) {

            if(b.squares[y][x].getIcon() == b.wPawnW.getFigure())
                b.whiteCaptureSlots[whiteIndex].setIcon(b.wPawnW.getFigure());

            if(b.squares[y][x].getIcon() == b.wPawnB.getFigure())
                b.whiteCaptureSlots[whiteIndex].setIcon(b.wPawnW.getFigure());

            if(b.squares[y][x].getIcon() == b.wRookW.getFigure())
                b.whiteCaptureSlots[whiteIndex].setIcon(b.wRookW.getFigure());

            if(b.squares[y][x].getIcon() == b.wRookB.getFigure())
                b.whiteCaptureSlots[whiteIndex].setIcon(b.wRookW.getFigure());

            if(b.squares[y][x].getIcon() == b.wBishopW.getFigure())
                b.whiteCaptureSlots[whiteIndex].setIcon(b.wBishopW.getFigure());

            if(b.squares[y][x].getIcon() == b.wBishopB.getFigure())
                b.whiteCaptureSlots[whiteIndex].setIcon(b.wBishopW.getFigure());
            if(b.squares[y][x].getIcon() == b.wQueenW.getFigure())
                b.whiteCaptureSlots[whiteIndex].setIcon(b.wQueenW.getFigure());

            if(b.squares[y][x].getIcon() == b.wQueenB.getFigure())
                b.whiteCaptureSlots[whiteIndex].setIcon(b.wQueenW.getFigure());

            if(b.squares[y][x].getIcon() == b.wKnightW.getFigure())
                b.whiteCaptureSlots[whiteIndex].setIcon(b.wKnightW.getFigure());

            if(b.squares[y][x].getIcon() == b.wKnightB.getFigure())
                b.whiteCaptureSlots[whiteIndex].setIcon(b.wKnightW.getFigure());


            b.whiteCaptureSlots[whiteIndex].setEnabled(true);
            whiteIndex++;

        }

        // capturing black figures
        if(isBlackFigure(y, x)) {

            if(b.squares[y][x].getIcon() == b.bPawnW.getFigure())
                b.blackCaptureSlots[blackIndex].setIcon(b.bPawnW.getFigure());

            if(b.squares[y][x].getIcon() == b.bPawnB.getFigure())
                b.blackCaptureSlots[blackIndex].setIcon(b.bPawnW.getFigure());

            if(b.squares[y][x].getIcon() == b.bRookW.getFigure())
                b.blackCaptureSlots[blackIndex].setIcon(b.bRookW.getFigure());

            if(b.squares[y][x].getIcon() == b.bRookB.getFigure())
                b.blackCaptureSlots[blackIndex].setIcon(b.bRookW.getFigure());

            if(b.squares[y][x].getIcon() == b.bBishopW.getFigure())
                b.blackCaptureSlots[blackIndex].setIcon(b.bBishopW.getFigure());

            if(b.squares[y][x].getIcon() == b.bBishopB.getFigure())
                b.blackCaptureSlots[blackIndex].setIcon(b.bBishopW.getFigure());
            if(b.squares[y][x].getIcon() == b.bQueenW.getFigure())
                b.blackCaptureSlots[blackIndex].setIcon(b.bQueenW.getFigure());

            if(b.squares[y][x].getIcon() == b.bQueenB.getFigure())
                b.blackCaptureSlots[blackIndex].setIcon(b.bQueenW.getFigure());

            if(b.squares[y][x].getIcon() == b.bKnightW.getFigure())
                b.blackCaptureSlots[blackIndex].setIcon(b.bKnightW.getFigure());

            if(b.squares[y][x].getIcon() == b.bKnightB.getFigure())
                b.blackCaptureSlots[blackIndex].setIcon(b.bKnightW.getFigure());

            b.blackCaptureSlots[blackIndex].setEnabled(true);
            blackIndex++;
        }
    }

    private void check() {

        for(int i = 1; i < 7; i++) {
            for(int j = 1; j < 7; j++) {

                // checking by white pawns
                if(b.squares[i][j].getIcon() == b.wPawnW.getFigure() || b.squares[i][j].getIcon() == b.wPawnW.getFigure()) {

                    if(b.squares[i - 1][j - 1].getIcon() == b.bKingW.getFigure() || b.squares[i - 1][j - 1].getIcon() == b.bKingW.getFigure()) {
                        isBlackCheck = true;
                        System.out.println("Check");
                    }
                }

                if(b.squares[i][j].getIcon() == b.wPawnW.getFigure() || b.squares[i][j].getIcon() == b.wPawnW.getFigure()) {

                    if(b.squares[i - 1][j + 1].getIcon() == b.bKingW.getFigure() || b.squares[i - 1][j + 1].getIcon() == b.bKingW.getFigure()) {
                        isWhiteCheck = true;
                        System.out.println("Check");
                    }
                }

                // checking by white rook
                if(b.squares[i][j].getIcon() == b.wRookW.getFigure() && b.squares[i][j].getIcon() == b.wRookB.getFigure()) {

                    int k = 1;
                    while((b.squares[i - k][j].getIcon() == b.whiteBoard.getFigure()
                            || b.squares[i - k][j].getIcon() == b.blackBoard.getFigure()
                            || b.squares[i - k][j].getIcon() == b.bKingW.getFigure()
                            || b.squares[i - k][j].getIcon() == b.bKingB.getFigure())
                            && i - k < 8 && i - k >= 0) {

                        if(b.squares[i - k][j].getIcon() == b.bKingW.getFigure()
                                || b.squares[i - k][j].getIcon() == b.bKingB.getFigure()) {

                            isBlackCheck = true;
                            System.out.println("Check");
                            break;
                        }
                        k++;
                    }
                }


                // checking by black pawns
                if(b.squares[i][j].getIcon() == b.bPawnW.getFigure() || b.squares[i][j].getIcon() == b.bPawnW.getFigure()) {

                    if(b.squares[i + 1][j - 1].getIcon() == b.wKingW.getFigure() || b.squares[i + 1][j - 1].getIcon() == b.wKingW.getFigure()) {
                        isWhiteCheck = true;
                        System.out.println("Check");
                    }
                }

                if(b.squares[i][j].getIcon() == b.bPawnW.getFigure() || b.squares[i][j].getIcon() == b.bPawnW.getFigure()) {

                    if(b.squares[i + 1][j + 1].getIcon() == b.wKingW.getFigure() || b.squares[i + 1][j + 1].getIcon() == b.wKingW.getFigure()) {
                        isWhiteCheck = true;
                        System.out.println("Check");
                    }
                }

            }
        }

    }

    private void cantMove() {

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {

                availableSquares[i][j] = false;

            }
        }


        for(int i = 1; i < 7; i++) {
            for(int j = 1; j < 7; j++) {

                if(b.squares[i][j].getIcon() == b.wKingW.getFigure()
                        || b.squares[i][j].getIcon() == b.wKingB.getFigure()
                        || b.squares[i][j].getIcon() == b.bKingW.getFigure()
                        || b.squares[i][j].getIcon() == b.bKingB.getFigure()) {

                    availableSquares[i + 1][j] = true;
                    availableSquares[i - 1][j] = true;
                    availableSquares[i][j + 1] = true;
                    availableSquares[i][j - 1] = true;
                    availableSquares[i + 1][j + 1] = true;
                    availableSquares[i + 1][j - 1] = true;
                    availableSquares[i - 1][j + 1] = true;
                    availableSquares[i - 1][j - 1] = true;

                }

            }
        }

    }
}
