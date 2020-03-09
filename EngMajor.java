import java.util.ArrayList;
import java.util.Scanner;

public class EngMajor extends Chara{
	Scanner userIn = new Scanner(System.in);
	
	public EngMajor(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range);
	}
	
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		boolean didSomething = false;
		if(getMana() < 2) System.out.println("This special requires 2 mana");
		else {
			System.out.println("Attack all enemies in the same row as you (Costs 2 mana");
			System.out.println("Enter 1 to confirm");
			int choice = userIn.nextInt();
			if(choice == 1) {
				int row = theMap.getPos(getID())[1];
				for(int i = 0; i < enemies.size(); i++) {
					if(theMap.getPos(enemies.get(i).getID())[1] == row) {
						attack(enemies.get(i));
					}
				}
				setMana(getMana() - 2);
				didSomething = true;
			}
		}
		return didSomething;
	}
}
