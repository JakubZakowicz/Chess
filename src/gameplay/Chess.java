package gameplay;

import javax.swing.*;

public class Chess extends JFrame {

    private Chess() {

        add(new Board());
        setTitle("gameplay.Chess Game");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Chess();
            }
        });

    }

}
