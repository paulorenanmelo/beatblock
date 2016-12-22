package com.robotllama.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robotllama.Entities.Hero.Herostate;
import com.robotllama.Entities.Objects.Side;
import com.robotllama.Settings.GameSettings;

import java.util.ArrayList;
import java.util.List;

public class ObjectDropHandler {


	enum CurrentSide{
		LEFT,
		RIGHT;
	}

	CurrentSide currentSide;

	static final float objectsWidth = GameSettings.objectSize;
	static final float screenWidth = GameSettings.screenWidth;

	public List<Objects> leftList = new ArrayList<Objects>();
	public List<Objects> rightList = new ArrayList<Objects>();

	boolean started = false;

	int dropCount = 0;
	int spacerCount = 0;
	int currentSpacerCount = 0;
	
	float  maxCount = 8;
	float  minCount = 1;
	float  spacerMin = 2;
	float  spacerMax = 4;
	float currentDropMax;
	float  currentSpacerMax;
	
	float offSet;
	
//	List<Objects> addLeft = new ArrayList<Objects>();
//	List<Objects> addRight = new ArrayList<Objects>();
//	List<Objects> removeLeft = new ArrayList<Objects>();
//	List<Objects> removeRight = new ArrayList<Objects>();
	
	List<Objects> passiveObjectsLeft = new ArrayList<Objects>();
	List<Objects> activeObjectsLeft = new ArrayList<Objects>();
	List<Objects> passiveObjectsRight = new ArrayList<Objects>();
	List<Objects> activeObjectsRight = new ArrayList<Objects>();

	
	public ObjectDropHandler(){
		int objectsCount = (int)(screenWidth / objectsWidth) * 2;
		for(int i = 0; i < objectsCount; i++){
			passiveObjectsLeft.add(new Objects(Side.LEFT, 0));
			passiveObjectsRight.add(new Objects(Side.RIGHT, 0));
		}

		started = false;

		leftList .clear();
		rightList.clear();

        System.out.println("ObjectDropHandler initialized");
	}
	
	public void reset(){
        int objectsCount = (int)(screenWidth / objectsWidth) * 2;

        passiveObjectsLeft.clear();
        passiveObjectsRight.clear();
        for(int i = 0; i < objectsCount; i++){
            passiveObjectsLeft.add(new Objects(Side.LEFT, 0));
            passiveObjectsRight.add(new Objects(Side.RIGHT, 0));
        }

        started = false;

		leftList .clear();
		rightList.clear();

        System.out.println("ObjectDropHandler reset");
	}
	
	public void start(){
        leftList = new ArrayList<Objects>();
        rightList = new ArrayList<Objects>();

        currentSide = CurrentSide.RIGHT;

		//rightList.add(drop(Side.RIGHT, 0));

		started = true;
        System.out.println("ObjectDropHandler started");
	}

    public void dropOne(float offSet) {
        if (currentSide == CurrentSide.LEFT) {
            leftList.add(drop(Side.LEFT, offSet));
            currentSide = CurrentSide.RIGHT;
        }
        else{
            rightList.add(drop(Side.RIGHT, offSet));
            currentSide = CurrentSide.LEFT;
        }
    }
	
	public boolean hasStarted(){
		return started;
	}
	
	private Objects drop(Side side, float offSet){
		//up to counter
		switch(side){
		case LEFT:
            // Pop the object on the bottom of the list
			Objects left = passiveObjectsLeft.get(0);
			passiveObjectsLeft.remove(0);
            // reset the object
            left.reset();
            // set the offset
            left.setOffSet(offSet);
//            System.out.println("ObjectDropHandler dropped");
			return left;
		case RIGHT:
            // Pop the object on the bottom of the list
			Objects right = passiveObjectsRight.get(0);
			passiveObjectsRight.remove(0);
            // reset the object
            right.reset();
            // set the offset
            right.setOffSet(offSet);
            return right;
		}
		return null;
	}
	
	public List<Objects> getLists(){
		List<Objects> merged = new ArrayList<Objects>();
		merged.addAll(leftList);
		merged.addAll(rightList);
		return merged;
	}
	
	public void update(SpriteBatch sb, Hero hero){
		//left side
		for(int i = 0; i < leftList.size(); i++){
			Objects obj = leftList.get(i);
			
			if(obj.getRect().overlaps(hero.getRect())){
				hero.dead(Herostate.LEFT);
			}
			
			if(!obj.update(sb)){
                passiveObjectsLeft.add(obj);
                leftList.remove(obj);
			}
		}

		//right side
		for(int i = 0; i < rightList.size(); i++){
			Objects obj = rightList.get(i);
			
			if(obj.getRect().overlaps(hero.getRect())){
				hero.dead(Herostate.RIGHT);
			}
			
			if(!obj.update(sb)){
                passiveObjectsRight.add(obj);
                rightList.remove(obj);
			}
		}


	}
}
