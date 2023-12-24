/**
 * Author: Heng Xin Phei
 * Lab: Occ9
 * Date: 22/12/2023
 * Objective: Viva 3-Q1: 1) * Phantom class - empty constructor, constructor (2: accuracy, critRate), accessor and mutator
 *                       2) - BlackMage class - empty constructor, int damage(BalckMage blackMage) method (total accuracy and critRate, return damage) , toString method returns combat log
 */

public class Phantom {
    
    //declare instance variables
    private double accuracy;
    private double critRate;
    
    //empty constructor
    public Phantom(){
        accuracy = 1.00;
        critRate = 1.00;
    }
    
    //constructor with parameters
    public Phantom(double accuracy, double critRate){
        
        //[0.00 < accuracy < 1.00]
        if (accuracy < 0.00) {
            this.accuracy = 0.00;
        } 
        else if (accuracy > 1.00) {
            this.accuracy = 1.00;
        } 
        else {
            this.accuracy = accuracy;
        }
        
        //[0.00 < critRate < 1.00]
        if (critRate < 0.00) {
            this.critRate = 0.00;
        } 
        else if (critRate > 1.00) {
            this.critRate = 1.00;
        } 
        else {
            this.critRate = critRate;
        }  
    }
    
    //accessor (get)
    public double getAccuracy(){
        return accuracy;
    }
    
    public double getCritRate(){
        return critRate;
    }
    
    //mutator (set)
    public void setAccuracy(double accuracy){
        this.accuracy = accuracy;
    }
    
    public void setCritRate(double critRate){
        this.critRate = critRate;
    }
    
    // Damage calculation method
    public int damage(BlackMage blackMage) {
        double effectiveAccuracy = accuracy * (1 - blackMage.getEvasion());
        double effectiveCritRate = critRate - blackMage.getCritResistance();
        //[0.0, 1.0)
        double randomValue = Math.random(); // Random value between 0 and 1

        if (randomValue < effectiveAccuracy) {
            if (randomValue < effectiveCritRate) {
                // Critical hit: Deal 4 damage
                blackMage.setHp(blackMage.getHp() - 4);
                return 4;
            } else {
                // Normal hit: Deal 2 damage
                blackMage.setHp(blackMage.getHp() - 2);
                return 2;
            }
        } 
        else {
            // Missed hit: Deal 0 damage
            return 0;
        }
        
    }

    // ToString method
    public String toString(int damageValue) {
        return "Combat Log:\n" +
               "Damage Dealt: " + damageValue;
    }
}

class BlackMage {
    private int hp;
    private double evasion;
    private double critResistance;

    // Empty constructor
    public BlackMage() {
        this.hp = 100;
        this.evasion = 0.05;
        this.critResistance = 0.10;
    }

    // Accessor methods
    public int getHp() {
        return hp;
    }

    public double getEvasion() {
        return evasion;
    }

    public double getCritResistance() {
        return critResistance;
    }
    
    public void setHp(int hp) {
        this.hp = hp;
    }

}

class PhantomTest {
    public static void main(String[] args) {
        
        // Initialize Phantom and BlackMage
        Phantom phantom = new Phantom();
        
        BlackMage blackMage = new BlackMage();

        // Start the battle
        System.out.println("The epic battle begins!");

        int damageValue;
        
        while (blackMage.getHp() > 0) {
            // Inside the loop, Phantom deals damage to the Black Mage
            damageValue = phantom.damage(blackMage);

            if (damageValue > 0) {
                System.out.println("[NORM] Phantom has dealt " + damageValue + " damage to the Black Mage (" + blackMage.getHp() + "/100)");
            } else {
                System.out.println("[MISS] Phantom has dealt 0 damage to the Black Mage (" + blackMage.getHp() + "/100)");
            }
        }

        // Display the result of the battle
        System.out.println("The Black Mage is defeated...");
    }
}
