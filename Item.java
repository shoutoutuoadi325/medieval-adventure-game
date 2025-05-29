public class Item {
    private int ID;
    private int price;
    private String name;
    public Item(int ID){
        this.ID=ID;
       if(ID==1){
           this.name="PotionOfLife";
           this.price=2;
       }
       else if (ID==2) {
           this.name="PotionOfCriticalStrike";
           this.price=2;
       }
       else{
           this.name="PotionOfMiss";
           this.price=3;
       }
    }
    public int getID(){
        return this.ID;
    }
    public void setID(int ID){
        this.ID=ID;
    }
    public int getPrice(){
        return this.price;
    }
    public void changeBlood(Hobbit hobbit,Opponent Opponent,boolean ifCouldWin){
        if(this.ID==1){
                hobbit.setBlood(hobbit.getBlood()+2);
        }
        if(this.ID==2){
            if(ifCouldWin)
                Opponent.setBlood(Opponent.getBlood()-1);
        }
        if(this.ID==3){
            if(!ifCouldWin)
                hobbit.setBlood(hobbit.getBlood()+2);
            }
        }
    }




