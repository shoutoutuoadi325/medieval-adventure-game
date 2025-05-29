public class Shop {
    private int ID1_num;
    private int ID2_num;
    private int ID3_num;
    public Shop(int ID1_num,int ID2_num,int ID3_num){
        this.ID1_num=ID1_num;
        this.ID2_num=ID2_num;
        this.ID3_num=ID3_num;
    }
    public int getLeftItem(int ID){
        if(ID==1){
            return ID1_num;
        } else if (ID==2) {
            return ID2_num;
        }
        else {
            return ID3_num;
        }
    }
    public void sale(int ID){
        if(ID==1)
            ID1_num=ID1_num-1;
        else if (ID==2) {
            ID2_num=ID2_num-1;
        }
        else{
            ID3_num=ID3_num-1;
        }
    }
    public int saleValue(){
        int value=0;
        if(ID1_num==0){
            value=value+2;
        }
        if(ID2_num==0){
            value=value+2;
        }
        if(ID3_num==0){
            value=value+3;
        }
        return value;
    }
}
