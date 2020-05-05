package figures;

import javax.swing.*;

public class Figure {

    ImageIcon figure;

    public Figure(String path) {

        figure = new ImageIcon(path);
    }


    public ImageIcon getFigure() {
        return figure;
    }
}
