import java.util.Random;
public class Level {
    private int Num;
    private Hobbit hobbit;
    private Opponent Opponent;
    private Shop shop;
    private boolean ifCouldWin;
    private boolean ifAwardLevel;

    public Level() {
    }

    ;

    public Level(int Num, Hobbit hobbit, Opponent Opponent, Shop shop) {
        this.Num = Num;
        this.hobbit = hobbit;
        this.Opponent = Opponent;
        this.shop = shop;
        if (Num % 3 == 0) {
            this.ifAwardLevel = true;
        } else {
            this.ifAwardLevel = false;
        }
    }

    public void getIfCouldWin() {
        if (ifAwardLevel)
            return;
        else {
            if (Opponent.getBlood() == 0)
                ifCouldWin = true;
            else {
                if (hobbit.getBlood() >= 2) {
                    if (Opponent.getName() == "Dwarf" && Opponent.getBlood() <= 2) {
                        ifCouldWin = true;
                    } else {
                        Random r = new Random();
                        int flag = r.nextInt(1, 11);
                        if (Opponent.getName() == "Elf") {
                            if (flag >= 1 && flag <= 2)
                                ifCouldWin = true;
                            else
                                ifCouldWin = false;
                        }
                        if (Opponent.getName() == "Dwarf") {
                            if (flag >= 1 && flag <= 5)
                                ifCouldWin = true;
                            else
                                ifCouldWin = false;
                        }
                        if (Opponent.getName() == "Orc") {
                            if (Opponent.getBlood() > hobbit.getBlood()) {
                                if (flag >= 1 && flag <= 4)
                                    ifCouldWin = true;
                                else
                                    ifCouldWin = false;
                            }
                            if (Opponent.getBlood() == hobbit.getBlood()) {
                                if (flag >= 1 && flag <= 6)
                                    ifCouldWin = true;
                                else
                                    ifCouldWin = false;
                            }
                            if (Opponent.getBlood() < hobbit.getBlood()) {
                                if (flag >= 1 && flag <= 7)
                                    ifCouldWin = true;
                                else
                                    ifCouldWin = false;
                            }
                        }
                    }
                } else {
                    if (Opponent.getName() == "Dwarf") {
                        if (Opponent.getBlood() <= 2)
                            ifCouldWin = true;
                    } else {
                        ifCouldWin = false;
                    }
                }
            }
        }
    }

    public boolean retrunIfCouldWin() {
        return this.ifCouldWin;
    }

    public void shopping(int ID) {
        Item potion = new Item(ID);
        if (hobbit.getCurrentCoin() < potion.getPrice()) {
            System.out.println("资金不够，无法完成购买");
        } else {
            if (shop.getLeftItem(ID) == 0)
                System.out.println("此商品已出售完");
            else {
                hobbit.setPotionID(ID);
                hobbit.setCurrentCoin(hobbit.getCurrentCoin() - potion.getPrice());
                shop.sale(ID);
            }
        }
    }

    public void setCoin() {
        if (ifAwardLevel) {
            hobbit.setCurrentCoin(hobbit.getCurrentCoin() + 2);
            Opponent.setCurrentCoin(Opponent.getCurrentCoin() + 2);
        } else {
            Coin coin = new Coin(1, false);
            coin.setIfReal();
            coin.setValue();
            if (ifCouldWin) {
                hobbit.setCurrentCoin(hobbit.getCurrentCoin() + coin.getValue());
            } else {
                Opponent.setCurrentCoin(Opponent.getCurrentCoin() + coin.getValue());
            }
        }
    }

    public void resetBlood_1() {
        if (ifAwardLevel) {
            Opponent.setBlood(Opponent.getBlood() + 2);
            hobbit.setBlood(hobbit.getBlood() + 2);
        } else {
            if (ifCouldWin) {
                if ((Opponent.getName() == "Dwarf" && Opponent.getBlood() <= 2) || Opponent.getBlood() == 0) {
                    Opponent.setBlood(Opponent.getBlood());
                } else {
                    Opponent.setBlood(Opponent.getBlood() - 2);
                }
            } else {
                if (hobbit.getBlood() < 2) {
                    hobbit.setBlood(hobbit.getBlood());
                } else {
                    hobbit.setBlood(hobbit.getBlood() - 2);
                }
            }
        }        //第一次更新血量
    }
    public void resetBlood_2() {
        if (Num > 1&&!ifAwardLevel) {
            if (Opponent.getBlood() > 0) {
                Opponent.setBlood(Opponent.getBlood() + 1);
            }
            if (hobbit.getBlood() > 0&&!ifAwardLevel) {
                if (hobbit.getBlood() > 2) {
                    hobbit.setBlood(hobbit.getBlood() + 1);
                } else {
                    hobbit.setBlood(hobbit.getBlood() - 1);
                }
            }            //第二次更新血量
        }
    }
}