package wizardgame;

import java.util.Random;

public class Control {

    public int findRandom() {
        Random rand = new Random();
        int intrand = rand.nextInt( (9) + 1 );
        return intrand;
    }

    public int findNextRoom() {
        Random rand = new Random();
        int intrand = rand.nextInt( 2 );
        return intrand;
    }

}
