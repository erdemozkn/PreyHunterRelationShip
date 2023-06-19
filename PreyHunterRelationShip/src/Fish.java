public class Fish extends Animal{
    private int strength;
    private int movement = 0;
    private int gender;//0 is female - 1 is male
    public Fish(){
        super();
        this.strength = 9999; // fish can move everytime and fish will die to predator only
    }
    @Override
    public void mv(){
        if(this.movement==9999){
            this.movement=0;
            this.strength--;
        }
        else{
            this.movement++;
        }
    }
    @Override
    public boolean isDie(){
        return this.strength==0;
    }
}
