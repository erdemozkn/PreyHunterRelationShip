public class Bear extends Animal{
    private int strength;
    private int movement = 0;
    private int gender;//0 is female - 1 is male
    
    public Bear() {
        super();
        this.strength = rnd.nextInt(10)+10;// 10 to 20 strength
    }
    @Override
    public void mv(){
        if(this.movement==10){
            this.movement=0;
            this.strength--;
        }
        else{
            this.movement++;
        }
    }

    @Override
    public void eatFish() {
        this.strength+=3;
    }
    @Override
    public boolean isDie(){
        return this.strength==0;
    }
    
    
}
