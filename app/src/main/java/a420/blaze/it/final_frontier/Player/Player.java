package a420.blaze.it.final_frontier.Player;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import a420.blaze.it.final_frontier.Architecture.GameObject;
import a420.blaze.it.final_frontier.Architecture.MainGame;

//Loosely based on tutorial found on Reddit.com

/**
 * Created by Conor on 02/02/2016.
     */
public class Player extends GameObject {

    private Bitmap spriteSheet;
    private int score;
    private double dxa;
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames)
    {
        x = 100;
        y = MainGame.HEIGHT / 2;
        dy = 0;
        score = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames]; // passes the amount of frames the player ss has
        spriteSheet = res;

        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(spriteSheet, i * width, 0, width, height);

        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();


    }

    // setup
    // method called by motion event - press down - player object goes up etc
    public void setUp(Boolean b) {
        up = b;
    }

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            // every 1/10 of a second, score goes up by one.
            /*
            * later be improved to increase score via other sources
            * - beating enemy unit
            * - destroying alien object
            * - destroying meteorite
            * etc..
            * */
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        // if user presses down on the screen
        if(up) {
            dy -= 1;
        } else {
            dy += 1;
        }

        if(dy>14) dy=14;
        if(dy<-14) dy=-14;

        y += dy*2;
        dy=0;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }

    public int getScore() {
        return score;
    }

    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }

    public void resetDY() {
        dxa = 0;
    }

    public void resetScore() {
        score = 0;
    }


}
