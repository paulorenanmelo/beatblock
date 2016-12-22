package com.robotllama.CustomActors;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.robotllama.Main;
import com.robotllama.Settings.GameSettings;

import java.util.ArrayList;

import Utils.OffScreenY;

/**
 * @author Paulo Renan Melo
 * Used for the song list in the main menu
 */
public class CustomTableActor extends CustomActor{

    BitmapFont font;
    String string;
    Image image;
    float height, width;
    float relativeX, relativeY;
    float relativeXPos, relativeYPos;
    String oldString, newString;

    float widthSecondCol = GameSettings.getSongListWidthSecondCol();
    float maxWidthFirstCol = screenWidth*0.9f - widthSecondCol;

    Table table, container;
    ScrollPane scroller;
    Music currentPlayingMusic;
    FileHandle currentPlayingMusicFile;
    Label labelPreviousSelected;
    ArrayList<FileHandle> listOfFilesDisplayed;

    public CustomTableActor(Skin skin){
        onCamera = new Vector2();

        //Skin unused, just passing as a parameter because it's required for the table constructor
        this.table = new Table(skin);
        this.container = new Table(skin);

        this.container.setFillParent(true);
        this.table.setWidth(maxWidthFirstCol);
//        this.table.columnDefaults(0).expand().right();
//        this.table.columnDefaults(1).left();

        this.table.debugTable().debugCell().debug();

        this.listOfFilesDisplayed = new ArrayList<>();
    }

    public void add(FileHandle file, Stage stage) {
        // ADD THE SONG DISPLAY
        table.add(getSongLabel(file, stage)).left().width(maxWidthFirstCol).maxWidth(maxWidthFirstCol);

        // GET THE HIGHSCORE OF THIS SONG (IF ANY) AND ADD TO SECOND COL
        table.add(getHighScoreLabel(file)).right().expandX();

        listOfFilesDisplayed.add(file);

        row();
    }

    public Label getSongLabel(final FileHandle file, final Stage stage) {
        //TODO; refractor this functionality to ActionResolver -> public Label getSongDisplay(FileHandle file)
        final String displayText = Main.actionResolver.getSongDisplay(file);

        final Label label = new Label(displayText, GameSettings.getDefaultStyle());
        label.setEllipse(true);//libgdx grammar mistake: Ellipsis (...)
        label.addListener(new InputListener() {
            boolean dragged = false;
            float dragX, dragY;
            float dragEnergy, dragEnergyIgnore = GameSettings.dragEnergyIgnore;

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //PLAYER HAS SELECTED A SONG
                if (!dragged || dragEnergy < dragEnergyIgnore)
                    labelSelected(file, label, stage);
            }

            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                dragged = true;
                dragEnergy += Math.abs(Math.abs(x - dragX) + Math.abs(y - dragY));
                dragX = x;
                dragY = y;
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dragged = false;
                dragX = x;
                dragY = y;
                dragEnergy = 0;
                return true;
            }
        });
        return label;
    }

    public Label getDisplayLabel(final String displayText){
        return new Label(displayText, GameSettings.getDefaultStyle());
    }

    public Label getHighScoreLabel(final FileHandle file){
        return new Label("A++", GameSettings.getDefaultStyle());
    }

    // ====================
    // Called when player selects a song from the list in the menu screen
    // ====================
    public void labelSelected(FileHandle file, Label label, Stage stage){
        //TODO: handle the label for it to show "Loading ..."

        Main.actionResolver.songSelected(file, label, stage);

    }

    public void releaseTableToStage(Stage stage){
        // ADD SONG LIST ITEMS TO A SCROLLER
        scroller = new ScrollPane(this.table);
        scroller.setWidth(screenWidth*0.5f);
        scroller.setForceScroll(false, true);
        scroller.setFlickScroll(true);
        scroller.setOverscroll(false, false);

        //ADD SONG LIST TITLE
        this.container.add(getDisplayLabel(GameSettings.songListTitle))
                .bottom()
                .expand();

        this.container.stack();
        this.container.row();

        // ADD SCROLLER TO A CONTAINER
        this.container.add(scroller)
                .bottom()
//                .expand()
                .height(screenHeight * 0.45f)
                .width(screenWidth * 0.9f)
                .spaceBottom(2f);

        // ADD CONTAINER TO STAGE
        if (!isOnStage(stage, this.container)) {
            stage.addActor(this.container);
            this.table.drawDebug(stage);
        }
    }

    // ADD ROW
    private void row() {
        this.table.stack();
        this.table.row();
    }

    private static boolean isOnStage(Stage stage, Actor actor){
        if(stage.getActors().contains(actor, true)) return true;
        return false;
    }

    public void settingsRelative(float x, float y){
        setRelativeX(x);
        setRelativeY(y);
        reposition();
        setCameraVectors();
    }

    public void settingsFixed(float x, float y, OffScreenY offY){
        setX(x);
        setY(y);
    }

    public float getRelativeX(){
        return relativeX;
    }

    public float getRelativeY(){
        return relativeY;
    }

    public BitmapFont getFont(){
        return font;
    }

    public Image getImage(){
        return image;
    }

    public void setString(String s){
        string = s;
        newString = s;
    }

    public void setString(String s, boolean x, boolean y){
        string = s;
        if(s != newString){
            if(x){repositionX(); onCamera.x = getX(); }
            if(y){repositionY(); onCamera.y = getY();}
        }
        newString = s;
    }

    public void setRelativeY(float f){
        relativeY = f;
        relativeYPos = GameSettings.getScreenHeightPos(relativeY);
    }

    public void setRelativeX(float f){
        relativeX = f;
        relativeXPos = GameSettings.getScreenWidthPos(relativeX);
    }

    public String getString(){
        return string;
    }

    @Override
    public float getHeight(){
        height = table.getHeight();
        return height;
    }

    @Override
    public float getWidth(){
        width = table.getWidth();
        return width;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

//        repositionX();

//        System.out.println("drawing method from CustomTableActor.java");
        try {
//            this.container.draw(batch, parentAlpha);
//            this.table.draw(batch, parentAlpha);
//            this.scroller.draw(batch, parentAlpha);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void reposition(){
        repositionX(); onCamera.x = getX();
        repositionY(); onCamera.y = getY();
    }

    public void repositionX(){
        float newX = relativeXPos - (getWidth() * .5f);
        setX(newX);
    }

    public void repositionY(){
        float newY = relativeYPos + (getHeight() * .5f);
        setY(newY);
    }

    public Vector2 getOnCamera(){
        return onCamera;
    }
}