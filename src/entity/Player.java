package entity;

public class Player {

    private String name;
    private int health;

    public Player( String name, int points ) {
        this.name = name;
        this.health = points;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        if ( health <= 0 ) {
            health = 0;
        }
        return health;
    }

    public void takeDamage( int health ) {

        this.health = health;

    }
}
