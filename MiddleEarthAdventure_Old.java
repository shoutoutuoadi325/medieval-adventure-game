/**
import java.util.Random;
import java.util.Scanner;

abstract class Creature {
    protected String name;
    protected int health;
    protected int gold;

    public Creature(String name, int health) {
        this.name = name;
        this.health = health;
        this.gold = 0;
    }

    public abstract boolean fight(Creature opponent);

    public void gainGold(int amount) {
        this.gold += amount;
    }

    public void decreaseHealth(int amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }
}

class Hobbit extends Creature {
    private static final int HEALTH_MIN = 5;
    private static final int HEALTH_MAX = 9;

    public Hobbit() {
        super("Hobbit", new Random().nextInt(HEALTH_MAX - HEALTH_MIN + 1) + HEALTH_MIN);
    }

    @Override
    public boolean fight(Creature opponent) {
        Random random = new Random();
        double rand = random.nextDouble();
        if (this.health >= opponent.getHealth()) {
            return rand < 0.7;
        } else if (this.health == opponent.getHealth()) {
            return rand < 0.5;
        } else {
            return false;
        }
    }
}

abstract class Opponent extends Creature {
    public Opponent(String name, int health) {
        super(name, health);
    }
}

class Dwarf extends Opponent {
    public Dwarf() {
        super("Dwarf", 9);
    }

    @Override
    public boolean fight(Creature opponent) {
        return !((Hobbit) opponent).fight(this);
    }
}

class Adventure {
    private Hobbit hobbit;
    private Opponent opponent;
    private int level = 1;
    private Scanner scanner = new Scanner(System.in);

    public Adventure() {
        this.hobbit = new Hobbit();
        this.opponent = chooseOpponent();
    }

    private Opponent chooseOpponent() {
        Random random = new Random();
        String[] opponents = { "Dwarf", "Elf", "Orc" };
        switch (opponents[random.nextInt(opponents.length)]) {
            case "Dwarf":
                return new Dwarf();
            case "Elf":
                return new Elf();
            default:
                return new Orc();
        }
    }

    class Elf extends Opponent {
        public Elf() {
            super("Elf", 7);
        }

        @Override
        public boolean fight(Creature opponent) {
            // 实现精灵的战斗逻辑
            // 精灵的战斗逻辑与霍比特人类似，但可能需要根据游戏规则进行调整
            return !((Hobbit) opponent).fight(this);
        }
    }

    class Orc extends Opponent {
        public Orc() {
            super("Orc", 5);
        }

        @Override
        public boolean fight(Creature opponent) {
            // 实现兽人的战斗逻辑
            // 兽人的战斗逻辑与霍比特人类似，但可能需要根据游戏规则进行调整
            return !((Hobbit) opponent).fight(this);
        }
    }

    public void start() {
        System.out.println("欢迎来到中世纪大冒险！");
        String destination = getDestination();
        while (true) {
            if (level >= 12 || hobbit.getHealth() <= 0) {
                printEndGameInfo(destination);
                break;
            }
            boolean fightResult = hobbit.fight(opponent);
            if (fightResult) {
                hobbit.gainGold(1);
                if (opponent.getHealth() > 0) {
                    opponent.decreaseHealth(2);
                }
            } else {
                opponent.gainGold(1);
                if (hobbit.getHealth() > 0) {
                    hobbit.decreaseHealth(2);
                }
            }
            printStatus();
            if (!continueGame()) {
                break;
            }
            level++;
            opponent = chooseOpponent();
            increaseHealth();
        }
    }

    private String getDestination() {
        System.out.print("请输入冒险的目的地名称(4-16个字符，只包含字母和空格): ");
        return scanner.nextLine().trim();
    }

    private void increaseHealth() {
        Random random = new Random();
        int healthIncrease = random.nextInt(2) + 1; // Randomly increase health between 1 and 2
        hobbit.gainGold(healthIncrease);
        opponent.gainGold(healthIncrease);
    }

    private boolean continueGame() {
        System.out.print("是否继续下一关? (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    private void printStatus() {
        System.out.println(hobbit.getName() + "的生命值: " + hobbit.getHealth() + ", 金币: " + hobbit.getGold());
        System.out.println(opponent.getName() + "的生命值: " + opponent.getHealth() + ", 金币: " + opponent.getGold());
    }

    private void printEndGameInfo(String destination) {
        System.out.println("游戏结束!");
        System.out.println("目的地名称: " + destination);
        System.out.println("胜利方: " + (hobbit.getHealth() > 0 ? "Hobbit" : opponent.getName()));
        System.out.println("冒险者获得的总金币: " + hobbit.getGold());
        System.out.println("对手获得的总金币: " + opponent.getGold());
        int totalGold = hobbit.getGold() + opponent.getGold();
        System.out.println("冒险者评分: " + totalGold);
    }
}

public class MiddleEarthAdventure_Old {
    public static void main(String[] args) {
        Adventure adventure = new Adventure();
        adventure.start();
        Scanner scanner = new Scanner(System.in);
        System.out.println("按任意键退出程序...");
        scanner.nextLine();
    }
}
*/