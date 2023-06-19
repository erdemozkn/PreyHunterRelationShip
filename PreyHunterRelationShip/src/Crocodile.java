public class Crocodile extends Animal{
    private int strength;
    private int movement = 0;
    private int gender;//0 is female - 1 is male
    public Crocodile(){
        super();
        this.strength = rnd.nextInt(10)+5; // 5 to 15 strength
    }
    @Override
    public void mv(){
        if(this.movement==15){
            this.movement=0;
            this.strength--;
        }
        else{
            this.movement++;
        }
    }
    @Override
    public void eatFish() {
         this.strength+=2;
    }
    @Override
    public boolean isDie(){
        return this.strength==0;
    }
}
