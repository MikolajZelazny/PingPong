package com.company;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

    private BufferedImage playImg, helpImg, exitImg, backgroundImg, titleImg;
    private BufferedImage paddleImg, ballImg;
    private final int X_MENU_IMG_POSITION;
    private String winnerInfo;
    private final Paddle player, pc;
    private final Ball ball;

    public GamePanel(Paddle player, Paddle pc, Ball ball) throws Exception{
        this.X_MENU_IMG_POSITION = 251;
        loadImages();
        this.player = player;
        this.pc = pc;
        this.ball = ball;
    }

//    public final void loadImages() throws IOException{
//        playImg       = ImageIO.read(getClass().getResource
//                ("/images/play.png"));
//        helpImg       = ImageIO.read(getClass().getResource
//                ("/images/help.png"));
//        exitImg       = ImageIO.read(getClass().getResource
//                ("/images/exit.png"));
//        backgroundImg = ImageIO.read(getClass().getResource
//                ("/images/bitmap.png"));
//        titleImg      = ImageIO.read(getClass().getResource
//                ("/images/title220.png"));
//        paddleImg     = ImageIO.read(getClass().getResource
//                ("/images/yellow.png"));
//        ballImg       = ImageIO.read(getClass().getResource
//                ("/images/football18.png"));
//    }


      public final void loadImages() throws IOException{
        File input = new File("C:\\Users\\Windows\\IdeaProjects\\PingPong\\src\\com\\company\\images\\play.png");
        File input2 = new File("C:\\Users\\Windows\\IdeaProjects\\PingPong\\src\\com\\company\\images\\help.png");
        File input3 = new File("C:\\Users\\Windows\\IdeaProjects\\PingPong\\src\\com\\company\\images\\exit.png");
        File input4 = new File("C:\\Users\\Windows\\IdeaProjects\\PingPong\\src\\com\\company\\images\\background.png");
        File input5 = new File("C:\\Users\\Windows\\IdeaProjects\\PingPong\\src\\com\\company\\images\\logo.png");
        File input6 = new File("C:\\Users\\Windows\\IdeaProjects\\PingPong\\src\\com\\company\\images\\paddle.png");
        File input7 = new File("C:\\Users\\Windows\\IdeaProjects\\PingPong\\src\\com\\company\\images\\ball.png");
        playImg       = ImageIO.read(input);
        helpImg       = ImageIO.read(input2);
        exitImg       = ImageIO.read(input3);
        backgroundImg = ImageIO.read(input4);
        titleImg      = ImageIO.read(input5);
        paddleImg     = ImageIO.read(input6);
        ballImg       = ImageIO.read(input7);
    }


    public int getXMenuImgPosition(){
        return X_MENU_IMG_POSITION;
    }

    public void endOfGame(boolean win)   {
        if (win)  {
            winnerInfo = "You win!";
            JOptionPane.showMessageDialog(this, winnerInfo,
                    "End of game", JOptionPane.INFORMATION_MESSAGE);
        }

        else{
            winnerInfo = "You lost";
            JOptionPane.showMessageDialog(this, winnerInfo,
                    "End of game", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g)   {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, null);

        if (Game.getStateOfGame() == Game.State.MENU)
            paintMenu(g);
        else
            paintGame(g);
    }

    private void paintMenu(Graphics g){
        g.drawImage(titleImg, 280, 25, null);
        g.drawImage(playImg, X_MENU_IMG_POSITION, 280, null);
        g.drawImage(helpImg, X_MENU_IMG_POSITION, 400, null);
        g.drawImage(exitImg, X_MENU_IMG_POSITION, 520, null);
    }

    private void paintGame(Graphics g) {
        g.drawImage(ballImg, ball.getRectangle().x, ball.getRectangle().y, null);

        g.drawImage(paddleImg, player.getRectangle().x,
                player.getRectangle().y, null);
        g.drawImage(paddleImg, pc.getRectangle().x,
                pc.getRectangle().y, null);
    }
}