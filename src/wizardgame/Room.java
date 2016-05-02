package wizardgame;

public class Room {

    private Room east;
    private Room west;
    private Room north;
    private Room south;
    private String name;
    private String description;
    private int damage;

    public Room( String name, String description, int damage ) {
        this.name = name;
        this.description = description;
        this.damage = damage;
    }

    public Room( String name, String description ) {
        this.name = name;
        this.description = description;
    }

    public Room getEast() {
        return east;
    }

    public void setEast( Room east ) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest( Room west ) {
        this.west = west;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth( Room north ) {
        this.north = north;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth( Room south ) {
        this.south = south;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage( int damage ) {
        this.damage = damage;
    }
}
