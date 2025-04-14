class Game {
    private String level;
    private int health;

    public void set(String level, int health) {
        this.level = level;
        this.health = health;
    }

    public GameMemento save() {
        return new GameMemento(level, health);
    }

    public void restore(GameMemento memento) {
        this.level = memento.getLevel();
        this.health = memento.getHealth();
    }

    @Override
    public String toString() {
        return "Level: " + level + ", Health: " + health;
    }
}

class GameMemento {
    private final String level;
    private final int health;

    public GameMemento(String level, int health) {
        this.level = level;
        this.health = health;
    }

    public String getLevel() { return level; }
    public int getHealth() { return health; }
}

class Caretaker {
    private final Stack<GameMemento> history = new Stack<>();

    public void save(Game game) {
        history.push(game.save());
    }

    public void undo(Game game) {
        if (!history.isEmpty()) {
            game.restore(history.pop());
        }
    }
}
public class MementoExample {
    public static void main(String[] args) {
        Game game = new Game();
        Caretaker caretaker = new Caretaker();

        game.set("Level 1", 100);
        System.out.println(game);
        caretaker.save(game);

        game.set("Level 2", 80);
        System.out.println(game);
        caretaker.save(game);

        game.set("Level 3", 60);
        System.out.println(game);

        caretaker.undo(game);
        System.out.println("After undo: " + game);

        caretaker.undo(game);
        System.out.println("After undo: " + game);
    }
}
