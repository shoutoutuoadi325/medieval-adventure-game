import java.util.Scanner;
import java.util.Random;

abstract class Creature {
    private String name;
    private int Blood;
    private int currentCoin;
    private Item potion;

    public Creature() {
    }

    ;

    public abstract void setInitialBlood();// 抽象方法，设置初始生命

    public abstract void CreatName();

    public void setName(String name) {
        this.name = name;
    }

    public void setBlood(int Blood) {
        this.Blood = Blood;
    }

    public void setCurrentCoin(int currentCoin) {
        this.currentCoin = currentCoin;
    }

    public int getBlood() {
        return this.Blood;
    }

    public int getCurrentCoin() {
        return this.currentCoin;
    }

    public String getName() {
        return this.name;
    }

    public void setPotion(Item potion) {
        this.potion = potion;
    }

    public Item getPotion() {
        return this.potion;
    }

    public int getPotionId() {
        return potion.getID();
    }

    public void setPotionID(int ID) {
        potion = new Item(ID);
    }
}

class Hobbit extends Creature {
    public Hobbit() {
        super();
    }

    @Override
    public void setInitialBlood() {
        Random r = new Random();
        this.setBlood(r.nextInt(5, 10));
    }

    @Override
    public void CreatName() {
        this.setName("Hobbit");
    }
}

class Opponent extends Creature {
    public Opponent() {
        super();
    }

    @Override
    public void CreatName() {
        Random r = new Random();
        int flag = r.nextInt(0, 3);
        if (flag == 0)
            this.setName("Dwarf");
        if (flag == 1)
            this.setName("Elf");
        if (flag == 2)
            this.setName("Orc");
    }

    @Override
    public void setInitialBlood() {
        if (this.getName() == "Dwarf")
            this.setBlood(9);
        if (this.getName() == "Elf")
            this.setBlood(7);
        if (this.getName() == "Orc")
            this.setBlood(8);
    }
}
public  class MiddleEarthAdventure {
    public static boolean checkTheDestination(String s) {
        boolean check = true;
        if (s.length() < 4 || s.length() > 16) {
            check = false;
        }
        char[] s_array = s.toCharArray();
        for (int i = 0; i < s_array.length; i++) {
            if (!(s_array[i] >= 'a' && s_array[i] <= 'z' || s_array[i] >= 'A' && s_array[i] <= 'Z'
                    || s_array[i] == ' ')) {
                check = false;
            }
        }
        return check;
    }
    public void MiddleEarthAdventure(){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("请输入冒险的目的地名称(4-16个字符，只包含字母和空格):");
        String destination = input.nextLine();
        String finalWinner=null;
        double mark=0;
        boolean Iflegal = checkTheDestination(destination);
        while (!Iflegal) {
            System.out.println("Error, retype please. ");
            destination = input.nextLine();
            Iflegal = checkTheDestination(destination);
        }
        int Level_num = 1;
        Hobbit hobbit = new Hobbit();
        hobbit.setInitialBlood();
        hobbit.CreatName();
        Opponent Opponent = new Opponent();
        Opponent.CreatName();
        Opponent.setInitialBlood();
        Shop shop = new Shop(1, 1, 1);
        hobbit.setPotion(null);
        while (Level_num < 13 && hobbit.getBlood() > 0) {
            Level level = new Level(Level_num, hobbit, Opponent, shop);
            level.getIfCouldWin();
            level.setCoin();
            level.resetBlood_1();
            if (hobbit.getPotion() != null&&hobbit.getPotion().getID()!=1) {
                hobbit.getPotion().changeBlood(hobbit, Opponent, level.retrunIfCouldWin());
                hobbit.setPotion(null);
            }
            level.resetBlood_2();
            System.out.println(hobbit.getName() + "的生命值为: " + hobbit.getBlood() + " 金币价值: " + hobbit.getCurrentCoin());
            if (Opponent.getBlood() >= 0) {
                System.out.println(Opponent.getName() + "的生命值为: " + Opponent.getBlood() + " 金币价值: " + Opponent.getCurrentCoin());
            } else {
                System.out.println(Opponent.getName() + "的生命值为: " + 0 + " 金币价值: " + Opponent.getCurrentCoin());
            }
            if (Level_num < 12) {
                boolean flag = false;
                while (!flag) {
                    System.out.println("shopping?");
                    char c = input.next().charAt(0);
                    if(c=='y'){
                        flag=true;
                    } else if (c=='1'||c=='2'||c=='3') {
                        int ID=(int)c-48;
                        level.shopping(ID);
                        if(ID==1&&hobbit.getPotion()!=null){
                            hobbit.getPotion().changeBlood(hobbit,Opponent,level.retrunIfCouldWin());
                            hobbit.setPotion(null);
                        }
                        flag=true;
                    }else {
                        System.out.println("error");
                        flag=false;
                    }
                }
            }
            if(hobbit.getBlood()<=0){
                System.out.println("游戏结束!");
                break;
            }
            Level_num++;
        }
        if(Level_num==13&&hobbit.getBlood()>0){
            finalWinner=hobbit.getName();
        }
        else{
            finalWinner=Opponent.getName();
        }                                           //注意类型转换
        mark=100.0*((double) hobbit.getCurrentCoin()/((double) hobbit.getCurrentCoin()+(double) Opponent.getCurrentCoin()+(double) shop.saleValue()));
        System.out.println("目的地: "+destination);
        System.out.println("胜利方: "+finalWinner);
        System.out.println("冒险者获得的总金币价值: "+hobbit.getCurrentCoin());
        System.out.println("对手获得的总金币价值: "+Opponent.getCurrentCoin());
        System.out.println("冒险者评分: "+mark+"%");
    }
    
}
