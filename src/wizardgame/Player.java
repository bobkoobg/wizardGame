/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizardgame;

/**
 *
 * @author Boyko
 */
public class Player
{

    private String name;
    private int health;

    public Player(String name, int points)
    {
        this.name = name;
        this.health = points;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getHealth()
    {
        if(health <= 0)
        {
            health = 0;
        }
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public void takeDamage(int health)
    {

        this.health = health;

    }
}
