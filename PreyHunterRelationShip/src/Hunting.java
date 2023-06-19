import java.security.SecureRandom;
public class Hunting {
    public final int width = 50;
    public final int height = 20;
    public final int begintofish = 30;
    public final int begintobear = 20;
    public final int begintocroc = 5;
    private int bearcount = 0;
    private int fishcount = 0;
    private int crocount = 0;
    
    SecureRandom rnd = new SecureRandom();
    Animal[][] arr;

    
    public Hunting(){
        arr = new Animal[height][width];
        while (fishcount < begintofish){
            fishcount += add(new Fish());
        }
        while (bearcount < begintobear){
            bearcount += add(new Bear());
        }
        while(crocount < begintocroc){
            crocount += add(new Crocodile());
        }
    }
        private int add(Animal animal){
            int addCount = 0;
            int fill = fishcount + bearcount + crocount;
            if(fill<width*height){
                while (addCount == 0){

                    int x = rnd.nextInt (width);  // 0 <= x < WIDTH
                    int y = rnd.nextInt (height); // 0 <= y < HEIGHT

                    if (arr[y][x] == null){
                        arr[y][x] = animal;
                        addCount++;
                    }
                }
            }
            return addCount;
        }
    
     
    public void move(){// 0 = > up, 1 = > right , 2 = > down,3 = > left, (4 to 9(include)) = > stay(%60)
        for(int y = 0;y<height;y++){
            for(int x = 0;x<width;x++){
                int a = rnd.nextInt(10);
                if(arr[y][x]!=null){//fish, bear and crocodile 
                    if(a==0&&!(arr[y][x].isDie())){
                        arr[y][x].mv();
                        int up = y-1;
                        if((up < height)&&(up>0)){
                            if(arr[up][x]==null){
                                arr[up][x]=arr[y][x];
                                arr[y][x]= null;
                            }
                            else if(arr[y][x].getClass()==arr[up][x].getClass()){
                                if((arr[y][x].getClass().getName().equals("Fish"))){
                                    if(arr[y][x].getGender()!=arr[up][x].getGender()){
                                        fishcount+=add(new Fish());
                                    }
                                }
                                else if(arr[y][x].getClass().getName().equals("Bear")){
                                    if(arr[y][x].getGender()!=arr[up][x].getGender()){
                                        bearcount+=add(new Bear());
                                    }
                                    else if(arr[y][x].getGender()==arr[up][x].getGender()){//if they are same gender will fight
                                        if(arr[y][x].getStrength()>arr[up][x].getStrength()){
                                            arr[y][x].setStrength(arr[y][x].getStrength()-arr[up][x].getStrength());
                                            arr[up][x] = arr[y][x];    
                                            arr[y][x]=null;
                                            bearcount--;
                                        }
                                        else if(arr[y][x].getStrength()<arr[up][x].getStrength()){
                                            arr[up][x].setStrength(arr[up][x].getStrength()-arr[y][x].getStrength());
                                            arr[y][x] =null;
                                            bearcount--;
                                        }
                                        else if(arr[y][x].getStrength()==arr[up][x].getStrength()){
                                            arr[y][x] = null;
                                            arr[up][x]= null;
                                            bearcount-=2;
                                        }
                                    }
                                }
                                else if(arr[y][x].getClass().getName().equals("Crocodile")){
                                    if(arr[y][x].getGender()!=arr[up][x].getGender()){
                                        crocount+=add(new Crocodile());
                                    }
                                    else if(arr[y][x].getGender()==arr[up][x].getGender()){//if they are same gender will fight
                                        if(arr[y][x].getStrength()>arr[up][x].getStrength()){
                                            arr[y][x].setStrength(arr[y][x].getStrength()-arr[up][x].getStrength());
                                            arr[up][x] = arr[y][x];    
                                            arr[y][x]=null;
                                            crocount--;
                                        }
                                        else if(arr[y][x].getStrength()<arr[up][x].getStrength()){
                                            arr[up][x].setStrength(arr[up][x].getStrength()-arr[y][x].getStrength());
                                            arr[y][x] = null;
                                            crocount--;
                                        }
                                        else if(arr[y][x].getStrength()==arr[up][x].getStrength()){
                                            arr[y][x] = null;
                                            arr[up][x]= null;
                                            crocount-=2;
                                        }
                                    }
                                }
                            }
                            else if((arr[y][x].getClass()!=arr[up][x].getClass())){
                                if(arr[y][x].getClass().getName().equals("Fish")){
                                    arr[up][x].eatFish();
                                    arr[y][x] = null;
                                    fishcount--;
                                }
                                else if(arr[up][x].getClass().getName().equals("Fish")){
                                    arr[y][x].eatFish();
                                    arr[up][x] = arr[y][x];
                                    arr[y][x] = null;
                                    fishcount--;
                                }
                                else if(arr[up][x].getClass().getName().equals("Bear")&&arr[y][x].getClass().getName().equals("Crocodile")){
                                    int chance = rnd.nextInt(2);//0 or 1
                                    if(chance==0){
                                        arr[up][x]=arr[y][x];
                                        arr[y][x] = null;
                                        bearcount--;
                                    }
                                    else if(chance ==1){
                                        arr[y][x]= null;
                                        crocount--;
                                    }
                                }
                                else if(arr[up][x].getClass().getName().equals("Bear")&&arr[y][x].getClass().getName().equals("Crocodile")){
                                    int chance = rnd.nextInt(2);//0 or 1
                                    if(chance==0){
                                        arr[up][x]=arr[y][x];
                                        arr[y][x] = null;
                                        bearcount--;
                                    }
                                    else if(chance ==1){
                                        arr[y][x]= null;
                                        crocount--;
                                    }
                                }
                                
                            }
                        }
                    }
                    else if(a==1&&!(arr[y][x].isDie())){
                        arr[y][x].mv();
                        int right = x+1;
                        if((right < width)&&(right>0)){
                            if(arr[y][right]==null){
                                arr[y][right]=arr[y][x];
                                arr[y][x]= null;
                            }
                            else if((arr[y][x].getClass()==arr[y][right].getClass())){
                                if((arr[y][x].getClass().getName().equals("Fish"))){
                                    if(arr[y][x].getGender()!=arr[y][x].getGender()){
                                        fishcount+=add(new Fish());
                                    }
                                }
                                else if(arr[y][x].getClass().getName().equals("Bear")){
                                    if(arr[y][x].getGender()!=arr[y][right].getGender()){
                                        bearcount+=add(new Bear());
                                    }
                                    else if(arr[y][x].getGender()==arr[y][right].getGender()){//if they are same gender will fight
                                        if(arr[y][x].getStrength()>arr[y][right].getStrength()){
                                            arr[y][x].setStrength(arr[y][x].getStrength()-arr[y][right].getStrength());
                                            arr[y][right] = arr[y][x];    
                                            arr[y][x]=null;
                                            bearcount--;
                                        }
                                        else if(arr[y][x].getStrength()<arr[y][right].getStrength()){
                                            arr[y][right].setStrength(arr[y][right].getStrength()-arr[y][x].getStrength());
                                            arr[y][x] = null;
                                            bearcount--;
                                        }
                                        else if(arr[y][x].getStrength()==arr[y][right].getStrength()){
                                            arr[y][x] = null;
                                            arr[y][right]= null;
                                            bearcount-=2;
                                        }
                                    }
                                }
                                else if(arr[y][x].getClass().getName().equals("Crocodile")){
                                    if(arr[y][x].getGender()!=arr[y][right].getGender()){
                                        crocount+=add(new Crocodile());
                                    }
                                    else if(arr[y][x].getGender()==arr[y][right].getGender()){//if they are same gender will fight
                                        if(arr[y][x].getStrength()>arr[y][right].getStrength()){
                                            arr[y][x].setStrength(arr[y][x].getStrength()-arr[y][right].getStrength());
                                            arr[y][right] = arr[y][x];    
                                            arr[y][x]=null;
                                            crocount--;
                                        }
                                        else if(arr[y][x].getStrength()<arr[y][right].getStrength()){
                                            arr[y][right].setStrength(arr[y][right].getStrength()-arr[y][x].getStrength());
                                            arr[y][x] = null;
                                            crocount--;
                                        }
                                        else{
                                            arr[y][x] = null;
                                            arr[y][right]= null;
                                            crocount-=2;
                                        }
                                    }
                                }
                            }
                            else if((arr[y][x].getClass()!=arr[y][right].getClass())){
                                if(arr[y][x].getClass().getName().equals("Fish")){
                                    arr[y][right].eatFish();
                                    arr[y][x] = null;
                                    fishcount--;
                                    
                                }
                                else if(arr[y][right].getClass().getName().equals("Fish")){
                                    arr[y][x].eatFish();
                                    arr[y][right] = arr[y][x];
                                    arr[y][x] = null;
                                    fishcount--;
                                }
                                else if(arr[y][right].getClass().getName().equals("Bear")&&arr[y][x].getClass().getName().equals("Crocodile")){
                                    int chance = rnd.nextInt(2);//0 or 1
                                    if(chance==0){
                                        arr[y][right]=arr[y][x];
                                        arr[y][x]=null;
                                        bearcount--;
                                    }
                                    else if(chance == 1){
                                        arr[y][x]= null;
                                        crocount--;
                                    }
                                }
                                else if(arr[y][right].getClass().getName().equals("Crocodile")&&arr[y][x].getClass().getName().equals("Bear")){
                                    int chance = rnd.nextInt(2);//0 or 1
                                    if(chance==0){
                                        arr[y][right]=arr[y][x];
                                        arr[y][x]=null;
                                        crocount--;
                                    }
                                    else if(chance == 1){
                                        arr[y][x]= null;
                                        bearcount--;
                                    }
                                }
                            }
                        }
                    }
                    else if(a==2&&!(arr[y][x].isDie())){
                        arr[y][x].mv();
                        int down = y+1;
                        if((down < height)&&(down>0)){
                            if(arr[down][x]==null){
                                arr[down][x]=arr[y][x];
                                arr[y][x]= null;
                            }
                            else if((arr[y][x].getClass()==arr[down][x].getClass())){
                                if((arr[y][x].getClass().getName().equals("Fish"))){
                                    if(arr[y][x].getGender()!=arr[down][x].getGender()){
                                        fishcount+=add(new Fish());
                                    }
                                }
                                else if(arr[y][x].getClass().getName().equals("Bear")){
                                    if(arr[y][x].getGender()!=arr[down][x].getGender()){
                                        bearcount+=add(new Bear());
                                    }
                                    else if(arr[y][x].getGender()==arr[down][x].getGender()){//if they are same gender will fight
                                        if(arr[y][x].getStrength()>arr[down][x].getStrength()){
                                            arr[y][x].setStrength(arr[y][x].getStrength()-arr[down][x].getStrength());
                                            arr[down][x] = arr[y][x];   
                                            arr[y][x]=null;
                                            bearcount--;
                                        }
                                        else if(arr[y][x].getStrength()<arr[down][x].getStrength()){
                                            arr[down][x].setStrength(arr[down][x].getStrength()-arr[y][x].getStrength());
                                            arr[y][x] =null;
                                            bearcount--;
                                        }
                                        else if(arr[y][x].getStrength()==arr[down][x].getStrength()){
                                            arr[y][x] = null;
                                            arr[down][x]= null;
                                            bearcount-=2;
                                        }
                                    }
                                }
                                else if(arr[y][x].getClass().getName().equals("Crocodile")){
                                    if(arr[y][x].getGender()!=arr[down][x].getGender()){
                                        crocount+=add(new Crocodile());
                                    }
                                    else if(arr[y][x].getGender()==arr[down][x].getGender()){//if they are same gender will fight
                                        if(arr[y][x].getStrength()>arr[down][x].getStrength()){
                                            arr[y][x].setStrength(arr[y][x].getStrength()-arr[down][x].getStrength());
                                            arr[down][x] = arr[y][x];   
                                            arr[y][x]=null;
                                            crocount--;
                                        }
                                        else if(arr[y][x].getStrength()<arr[down][x].getStrength()){
                                            arr[down][x].setStrength(arr[down][x].getStrength()-arr[y][x].getStrength());
                                            arr[y][x] =null;
                                            crocount--;
                                        }
                                        else if(arr[y][x].getStrength()==arr[down][x].getStrength()){
                                            arr[y][x] = null;
                                            arr[down][x]= null;
                                            crocount-=2;
                                        }
                                    }
                                }
                            }
                            else if((arr[y][x].getClass()!=arr[down][x].getClass())){
                                if(arr[y][x].getClass().getName().equals("Fish")){
                                    arr[down][x].eatFish();
                                    arr[y][x] = null;
                                    fishcount--;
                                }
                                else if(arr[down][x].getClass().getName().equals("Fish")){
                                    arr[y][x].eatFish();
                                    arr[down][x] = arr[y][x];
                                    arr[y][x] = null;
                                    fishcount--;
                                }
                                else if(arr[down][x].getClass().getName().equals("Bear")&&arr[y][x].getClass().getName().equals("Crocodile")){
                                    int chance = rnd.nextInt(2);//0 or 1
                                    if(chance==0){
                                        arr[down][x]=arr[y][x];
                                        arr[y][x]=null;
                                        bearcount--;
                                    }
                                    else if(chance == 1){
                                        arr[y][x]= null;
                                        crocount--;
                                    }
                                }
                                else if(arr[down][x].getClass().getName().equals("Crocodile")&&arr[y][x].getClass().getName().equals("Bear")){
                                    int chance = rnd.nextInt(2);//0 or 1
                                    if(chance==0){
                                        arr[down][x]=arr[y][x];
                                        arr[y][x]=null;
                                        crocount--;
                                    }
                                    else if(chance == 1){
                                        arr[y][x]= null;
                                        bearcount--;
                                    }
                                }
                                
                                
                            }
                            
                        }
                    }
                    else if(a==3&&!(arr[y][x].isDie())){
                        arr[y][x].mv();
                        int left = x-1;
                        if((left < height)&&(left>0)){
                            if(arr[y][left]==null){
                                arr[y][left]=arr[y][x];
                                arr[y][x]= null;
                            }
                            else if((arr[y][x].getClass()==arr[y][left].getClass())){
                                if((arr[y][x].getClass().getName().equals("Fish"))){
                                    if(arr[y][x].getGender()!=arr[y][x].getGender()){
                                        fishcount+=add(new Fish());
                                    }
                                }
                                else if(arr[y][x].getClass().getName().equals("Bear")){
                                    if(arr[y][x].getGender()!=arr[y][left].getGender()){
                                        bearcount+=add(new Bear());
                                    }
                                    else if(arr[y][x].getGender()==arr[y][left].getGender()){//if they are same gender will fight
                                        if(arr[y][x].getStrength()>arr[y][left].getStrength()){
                                            arr[y][x].setStrength(arr[y][x].getStrength()-arr[y][left].getStrength());
                                            arr[y][left] = arr[y][x];    
                                            arr[y][x]=null;
                                            bearcount--;
                                        }
                                        else if(arr[y][x].getStrength()<arr[y][left].getStrength()){
                                            arr[y][left].setStrength(arr[y][left].getStrength()-arr[y][x].getStrength());
                                            arr[y][x] =null;
                                            bearcount--;
                                        }
                                        else if(arr[y][x].getStrength()==arr[y][left].getStrength()){
                                            arr[y][x] = null;
                                            arr[y][left]= null;
                                            bearcount-=2;
                                        }
                                    }
                                }
                                else if(arr[y][x].getClass().getName().equals("Crocodile")){
                                    if(arr[y][x].getGender()!=arr[y][left].getGender()){
                                        crocount+=add(new Crocodile());
                                    }
                                    else if(arr[y][x].getGender()==arr[y][left].getGender()){//if they are same gender will fight
                                        if(arr[y][x].getStrength()>arr[y][left].getStrength()){
                                            arr[y][x].setStrength(arr[y][x].getStrength()-arr[y][left].getStrength());
                                            arr[y][left] = arr[y][x];    
                                            arr[y][x]=null;
                                            crocount--;
                                        }
                                        else if(arr[y][x].getStrength()<arr[y][left].getStrength()){
                                            arr[y][left].setStrength(arr[y][left].getStrength()-arr[y][x].getStrength());
                                            arr[y][x] =null;
                                            crocount--;
                                        }
                                        else if(arr[y][x].getStrength()==arr[y][left].getStrength()){
                                            arr[y][x] = null;
                                            arr[y][left]= null;
                                            crocount-=2;
                                        }
                                    }
                                }
                            }
                            else if((arr[y][x].getClass()!=arr[y][left].getClass())){
                                if(arr[y][x].getClass().getName().equals("Fish")){
                                    arr[y][left].eatFish();
                                    arr[y][x] = null;
                                    fishcount--;
                                }
                                else if(arr[y][left].getClass().getName().equals("Fish")){
                                    arr[y][x].eatFish();
                                    arr[y][left] = arr[y][x];
                                    arr[y][x] = null;
                                    fishcount--;
                                }
                                else if(arr[y][left].getClass().getName().equals("Bear")&&arr[y][x].getClass().getName().equals("Crocodile")){
                                    int chance = rnd.nextInt(2);//0 or 1
                                    if(chance==0){
                                        arr[y][left]=arr[y][x];
                                        arr[y][x]=null;
                                        bearcount--;
                                    }
                                    else if(chance == 1){
                                        arr[y][x]= null;
                                        crocount--;
                                    }
                                }
                                else if(arr[y][left].getClass().getName().equals("Crocodile")&&arr[y][x].getClass().getName().equals("Bear")){
                                    int chance = rnd.nextInt(2);//0 or 1
                                    if(chance==0){
                                        arr[y][left]=arr[y][x];
                                        arr[y][x]=null;
                                        crocount--;
                                    }
                                    else if(chance == 1){
                                        arr[y][x]= null;
                                        bearcount--;
                                    }
                                }
                                
                                
                            }
                        }
                    }
                    else if(a>3&&!(arr[y][x].isDie())){
                        //stay
                    }
                    else{
                        if(arr[y][x].getClass().getName().equals("Fish")){
                            arr[y][x]=null;
                            fishcount--;
                        }
                        else if(arr[y][x].getClass().getName().equals("Bear")){
                            arr[y][x]=null;
                            bearcount--;
                        }
                        else if(arr[y][x].getClass().getName().equals("Crocodile")){
                            arr[y][x]=null;
                            crocount--;
                        }
                    }
                }
                
            }
            
        }
    }
    @Override
    public String toString(){
        String output = ""; //Empty
        
        for(int y = 0;y<height;y++){
            for(int x = 0;x<width;x++){
                if(arr[y][x]==null){
                    output+=".";
                }
                else if(arr[y][x] instanceof Fish){
                    output+="F";
                }
                else if(arr[y][x] instanceof Bear){
                    output+="B";
                }
                else if(arr[y][x] instanceof Crocodile){
                    output+="C";
                }
                
            }
            output+="\n";
        }
        output+= "Fish: " + fishcount + " Bear: " + bearcount + " Crocodile " + crocount;
        return output;
    }
    
}
