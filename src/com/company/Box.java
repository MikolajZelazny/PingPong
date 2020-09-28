package com.company;

import java.awt.Rectangle;

public class Box {
    private final int WIDTH_FRAME, HEIGHT_FRAME;
    private final int MARGIN = 10;

    public Box(int width, int height){
        WIDTH_FRAME = width;
        HEIGHT_FRAME = height;
    }

    public Rectangle getLeftWall(){
        return new Rectangle(-MARGIN, 0, MARGIN, HEIGHT_FRAME);
    }

    public Rectangle getRightWall(){
        return new Rectangle(WIDTH_FRAME, 0, MARGIN, HEIGHT_FRAME);
    }

    public Rectangle getBottomWall(){
        return new Rectangle(0, HEIGHT_FRAME, WIDTH_FRAME, MARGIN);
    }

    public Rectangle getUpperWall(){
        return new Rectangle(0, -MARGIN, WIDTH_FRAME, MARGIN);
    }
}