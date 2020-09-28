package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.util.EnumMap;

public final class KeyInput implements Runnable{

    private static final String PRESSED = "pressed";
    private static final String RELEASED = "released";
    private final EnumMap<Key, Boolean> keyMap;
    private final GamePanel gPanel;
    private final Paddle player, playe2;

    public KeyInput(GamePanel gPanel, Paddle player, Paddle pc)  {
        this.keyMap =  new EnumMap<>(Key.class);
        this.gPanel = gPanel;
        this.player = player;
        this.playe2 = pc;

        resetKeyInput();

        ActionMap actionMap = gPanel.getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = gPanel.getInputMap(condition);

        for (Key key : Key.values())      {
            KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke
                    (key.getKeyCode(), 0, false);
            KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke
                    (key.getKeyCode(), 0, true);

            inputMap.put(pressedKeyStroke, key.getText() + PRESSED);
            inputMap.put(releasedKeyStroke, key.getText() + RELEASED);
            actionMap.put(key.getText() + PRESSED, new MyArrowBinding(key, false));
            actionMap.put(key.getText() + RELEASED, new MyArrowBinding(key, true));
        }
    }

    @Override
    public void run()    {
        int order = 1;

        for (Key key : keyMap.keySet())
        {
            if (keyMap.get(key) && order == 1)
                playe2.move(Game.Direction.LEFT);

            else if (keyMap.get(key) && order == 2)
                playe2.move(Game.Direction.RIGHT);

            else if (keyMap.get(key) && order == 3)
                player.move(Game.Direction.LEFT);

            else if (keyMap.get(key) && order == 4)
                player.move(Game.Direction.RIGHT);

            order++;
        }
    }

    public void resetKeyInput()   {
        keyMap.put(Key.A, false);
        keyMap.put(Key.D, false);
        keyMap.put(Key.LEFT, false);
        keyMap.put(Key.RIGHT, false);
    }

    private class MyArrowBinding extends AbstractAction    {
        private final Key key;
        private final boolean released;

        public MyArrowBinding(Key key, boolean released)     {
            this.key = key;
            this.released = released;
        }

        @Override
        public void actionPerformed(ActionEvent aEvt)     {
            keyMap.put(key, !released);
        }
    }

    enum Direction    {
        LEFT("Left"), RIGHT("Right"), NEUTRAL("Neutral");
        private final String text;

        private Direction(String text)     {
            this.text = text;
        }

        public String getText()     {
            return text;
        }
    }

    enum Key   {
        A("A", Direction.LEFT, KeyEvent.VK_A),
        D("D", Direction.RIGHT, KeyEvent.VK_D),
        LEFT("Left", Direction.LEFT, KeyEvent.VK_LEFT),
        RIGHT("Right", Direction.RIGHT, KeyEvent.VK_RIGHT);

        private final String text;
        private final Direction direction;
        private final int keyCode;

        private Key(String text, Direction direction, int keyCode)     {
            this.text = text;
            this.direction = direction;
            this.keyCode = keyCode;
        }

        public String getText()     {
            return text;
        }

        public Direction getDirection()    {
            return direction;
        }

        public int getKeyCode()    {
            return keyCode;
        }
    }
}