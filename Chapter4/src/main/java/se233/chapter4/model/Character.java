package se233.chapter4.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se233.chapter4.Launcher;
import se233.chapter4.view.Platform;

public class Character extends Pane {
    private static final Logger logger = LogManager.getLogger(Character.class);
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    private Image characterImg;
    private AnimatedSprite imageView;
    private int x;
    private int y;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    int xVelocity = 0;
    int yVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 7;
    int yMaxVelocity = 17;
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;




    public Character(int x, int y, int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey,String imgPath,int width,int height,int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.characterImg = new Image(Launcher.class.getResourceAsStream(imgPath));
        this.imageView = new AnimatedSprite(characterImg,4,4,1,offsetX,offsetY,width,height);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.xMaxVelocity += xSpeed;
        this.yMaxVelocity += ySpeed;
        this.getChildren().addAll(this.imageView);
    }


    public void moveLeft() {
        isMoveLeft = true;
        isMoveRight = false;
    }
    public void moveRight() {
        isMoveLeft = false;
        isMoveRight = true;
    }
    public void stop() {
        isMoveLeft = false;
        isMoveRight = false;
    }
    public void moveX() {
        setTranslateX(x);
        if(isMoveLeft) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x - xVelocity;
        }
        if(isMoveRight) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x + xVelocity;
        }
    }
    public void moveY(){
        setTranslateY(y);
        if(isFalling){
            yVelocity = yVelocity >= yMaxVelocity? yMaxVelocity : yVelocity+yAcceleration;
            y = y + yVelocity;
        }else if(isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity-yAcceleration;
            y = y - yVelocity;
        }
    }
    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }
    public AnimatedSprite getImageView() {
        return imageView;
    }
    public void checkReachGameWall() {
        if(x <= 0) {
            x = 0;
        } else if( x+getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH-(int)getWidth();
        }

    }
    public void jump() {
        if (canJump) {
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        } }
    public void checkReachHighest () {
        if(isJumping && yVelocity <= 0) {
            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }
    public void checkReachFloor(){
        if(isFalling && y >= Platform.GROUND - CHARACTER_HEIGHT){
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }
    public void repaint(){
        moveX();
        moveY();
    }
    int linecount = 0;

    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}",x,y,xVelocity,yVelocity,linecount++);
    }
}
