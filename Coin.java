import java.util.Random;
public class Coin {
    private int Number;
    private boolean ifAwardLevel;
    private boolean ifReal;
    private int value;
    public Coin(){};
    public Coin(int Number,boolean ifAwardLevel){
        this.Number=Number;
        this.ifAwardLevel=ifAwardLevel;   //设置面额数
    }
    public void setIfReal(){    //设置是否是假币
        if(ifAwardLevel){
            ifReal=true;
        }
        else{
            Random r=new Random();
            int flag=r.nextInt(1,11);
            if(flag>=1&&flag<=7){
                ifReal=true;
            }
            else{
                ifReal=false;
            }
        }
    }
    public void setValue(){
        if(ifReal){
            this.value=this.Number;
        }
        else{
            this.value=0;
        }
    }
    public int getValue(){
        return value;
    }
}
