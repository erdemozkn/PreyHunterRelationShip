import java.util.Random;

public class Animal {
    private int strength;
    private int movement = 0;
    private int gender;//0 is female - 1 is male
    Random rnd = new Random();
    
    public Animal(){
        this.strength = rnd.nextInt(5);//Strength can be 0-4(included)
        this.gender = rnd.nextInt(2);//gender can 0 or 1
    }
    //getter and setter
    
    public int getStrength(){
        return strength;
    }


    public void setStrength(int strength){
        this.strength = strength;
    }

    public int getMovement(){
        return movement;
    }

    public void setMovement(int movement){
        this.movement = movement;
    }

    /**
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(int gender) {
        this.gender = gender;
    }
    public boolean isDie(){
        if(this.strength==0){
            return true;
        }
        return false;
    }
    public void mv(){

    }
    public void eatFish(){
        
    }
}
