package com.company;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class MouseInput extends MouseAdapter{
    private final GamePanel gPanel;
    private final Game game;
    private final String helpInfo = "This is a very simple game which imitates"
            + " world-known game Ping Pong \n\n " + "First player use arrows"
            + ", second player use A and D keys";

    public MouseInput(Game game, GamePanel gPanel){
        this.game = game;
        this.gPanel = gPanel;
    }

    @Override
    public void mousePressed(MouseEvent e){
        int xMousePosition = e.getX();
        int yMousePosition = e.getY();

        if (Game.getStateOfGame() == Game.State.GAME)
            return;

        if (xMousePosition < gPanel.getXMenuImgPosition() || xMousePosition > 551)
            return;

        if (yMousePosition > 280 && yMousePosition < 350)
            game.changeStateOfGame(Game.State.GAME);

        else if (yMousePosition > 400 && yMousePosition < 470)
            JOptionPane.showMessageDialog(gPanel, helpInfo,
                    "Help information", JOptionPane.INFORMATION_MESSAGE);

        else if (yMousePosition > 520 && yMousePosition < 590)
            System.exit(0);
    }
}