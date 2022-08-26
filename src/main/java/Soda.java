

public enum Soda
{
    COKE(20), SPRITE(15), FANTA(15);

    final int cost;
    Soda(final int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
