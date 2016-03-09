package ttt;

public class MoveObserver extends Observer {
    private final Game game;

    public MoveObserver(Game game) {
        this.game = game;
        this.game.attach(this);
    }

    @Override
    public String update() {
        return String.valueOf(game.getMove());
    }
}