package characters;

import java.util.ArrayList;

import main.map;

public class PhilosophyMajor extends Chara {

	
	//constructor
	public PhilosophyMajor(int id) {
		super("Philosophy Major", id, 15, 200, 2, 15, 200, 5, 5, 2, "This special lowers the attack \n of a chosen enemy (costs 2 mana). \n Select the tile of the enemy \n you want to target", "Philosophy.png");
	}
	
	//methods
	//this special lowers the attack of an enemy
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos) {
		boolean didSomething = false;
		if(getMana() < 2)
			{System.out.println("This special requires 2 mana");}
		else {
			int choice = theMap.getID(xPos, yPos);
			for(Chara foe: enemies) {
				if (choice == foe.getID()) {
					foe.setAttack(foe.getAttack() - 5);
					setMana(getMana() - 2);
					didSomething = true;
				}
			}	
		}
		return didSomething;
	}
}
