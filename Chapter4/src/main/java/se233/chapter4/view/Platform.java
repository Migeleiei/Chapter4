package se233.chapter4.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import se233.chapter4.model.Character;
import se233.chapter4.Launcher;
import se233.chapter4.model.Keys;

public class Platform extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public final static int GROUND = 300;
    private Image platformImg;
    private Character character1;
    private Character character2;
    private Keys keys;
    public Platform(){
        keys = new Keys();
        platformImg = new Image(Launcher.class.getResourceAsStream("assets/Background.png"));
        ImageView backgroundImg = new ImageView(platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        character1 = new Character(30, 30,0,0, KeyCode.A,KeyCode.D,KeyCode.W,"assets/MarioSheet.png",16,32,1,1);
        character2 = new Character(710,30,0,0,KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP,"assets/kindpng_129142.png",77,72,10,5);
        getChildren().addAll(backgroundImg, character1,character2);
    }
    public Character getCharacter1(){
        return character1;
    }
    public Character getCharacter2(){ return character2;}
    public Keys getKeys() { return keys; }
}
