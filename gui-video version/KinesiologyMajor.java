package sample;

import java.util.ArrayList;

public class KinesiologyMajor extends Chara {

	//instance variables
			
	//constructor
	public KinesiologyMajor(int id) {
		super("KinesiologyMajor", id, 30, /**/100, 3, 30, /**/10, 5, 4, 1, "This special deals a chosen near-by \n enemy a large amount of damage \n (costs 2 mana). \n Select the tile of the enemy \n you want to attack.", "Kinesiology.png");
			}
	
	//methods
	
	//this special deals a close-by enemy a large amount of damage
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos) {
		boolean didSomething = false;
		if(getMana() < 2) System.out.println("This special requires 2 mana");
		else {
			int choice = theMap.getID(xPos, yPos);
			boolean isEnemy = false;
			int enemyID = 0;
			int enemyIndex = 0;
			for(int i = 0; i < enemies.size(); i++) {
				if(!isEnemy) {
					enemyID = enemies.get(i).getID();
					isEnemy = enemyID == choice;
					enemyIndex = i;
				}
			}
			if(isEnemy) {
				int[] enemyPos = theMap.getPos(enemyID);
				int[] playerPos = theMap.getPos(getID());
				int range = Math.abs(enemyPos[0] - playerPos[0]) + Math.abs(enemyPos[1] - playerPos[1]);
				if(range <= 1) {
					int regAttack = getAttack();
					setAttack(regAttack * 2);
					attack(enemies.get(enemyIndex));
					setAttack(regAttack);
					setMana(getMana() - 2);
					didSomething = true;
				} else {
					System.out.println("Enemy is not in range");
				}
			} else {
				System.out.println("That enemy does not exist");
			}
		}
		return didSomething;
	}
}
