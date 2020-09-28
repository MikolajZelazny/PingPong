package com.company;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game implements Runnable {

    public static enum State {MENU, GAME}
    public static enum Direction {LEFT, RIGHT}
    private final GamePanel gPanel;
    private static State stateOfGame;
    private final MouseInput mouseInput;
    private final Ball ball = new Ball();
    private final int UPDATE_INTERVAL = 15;
    private final Paddle player = new Paddle(300, 600);
    private final Paddle playe2 = new Paddle(300, 42);
    private boolean win = false;
    private final Box box;
    private final KeyInput keyInput;


    public Game() throws Exception  {
        stateOfGame = State.MENU;
        gPanel = new GamePanel(player, playe2, ball);
        mouseInput = new MouseInput(this, gPanel);
        gPanel.addMouseListener(mouseInput);
        box = new Box(GameFrame.WIDTH_GAME_FRAME, GameFrame.HEIGHT_GAME_FRAME);
        keyInput = new KeyInput(gPanel, player, playe2);
        player.setTurn(true);

        ScheduledThreadPoolExecutor executor, keyExecutor;
        keyExecutor = new ScheduledThreadPoolExecutor(10);

        keyExecutor.scheduleAtFixedRate
                (keyInput, 0L, UPDATE_INTERVAL, TimeUnit.MILLISECONDS);

        executor = new ScheduledThreadPoolExecutor(10);
        keyExecutor = new ScheduledThreadPoolExecutor(10);

        executor.scheduleAtFixedRate
                (this, 0L, 15L, TimeUnit.MILLISECONDS);

        keyExecutor.scheduleAtFixedRate
                (keyInput, UPDATE_INTERVAL, 15L, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        if (stateOfGame == State.GAME){
            ball.move();
            checkCollisions();
            gPanel.repaint();
        }

    }
    public void changeStateOfGame(State state){
        stateOfGame = state;
    }

    public void checkCollisions(){
        if (ball.getRectangle().intersects(player.getRectangle()) && player.isTurn()){
            ball.bouncePaddle();
            player.setTurn(false);
            playe2.setTurn(true);
        }
        else if (ball.getRectangle().intersects(playe2.getRectangle()) && playe2.isTurn()){
            ball.bouncePaddle();
            playe2.setTurn(false);
            player.setTurn(true);
        }
        else if (ball.getRectangle().intersects(box.getLeftWall()) ||
                ball.getRectangle().intersects(box.getRightWall()))
            ball.bounceWall();
        else if(ball.getRectangle().intersects(box.getUpperWall())){
            win = true;
            stateOfGame = State.MENU;
            gPanel.endOfGame(true);
            resetSettings();
        }
        else if (ball.getRectangle().intersects(box.getBottomWall())){
            win = false;
            stateOfGame = State.MENU;
            gPanel.endOfGame(false);
            resetSettings();
        }
    }

    public static State getStateOfGame(){
        return stateOfGame;
    }

    public GamePanel getGamePanel() {
        return gPanel;
    }

    public void resetSettings(){
        ball.reset();
        player.reset();
        playe2.reset();
    }
}