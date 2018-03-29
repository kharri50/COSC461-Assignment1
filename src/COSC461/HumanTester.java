package COSC461;

import COSC461.Greed.Direction;
import static COSC461.Greed.Direction.*;
import COSC461.Greed.GreedGame;
import static COSC461.Greed.GreedGame.*;
import static java.lang.System.in;
import static java.lang.System.out;
import java.util.*;
import javax.swing.JFrame;

/**
 * Example of a human player.
 *
 * @author Adam J. Conover, D.Sc. &lt;aconover@towson.edu&gt;
 */
public class HumanTester {

	/**
	 * Test as human. : Enter as compass direction ("QUIT" to quit).
	 *
	 * @param args n/a
	 */
	public static void main(String[] args) {
		GreedGame greed;

		// Seed this with any integer to always generate the same grid.
		final Random rnd = new Random(0);
		greed = new GreedGame(() -> rnd.nextInt(9) + 1, 17, 17);

		Scanner scan = new Scanner(in);
		do {
			out.println("Total Score: " + greed.getScore());
			out.println(greed.toString());

			final Set<Direction> availableDirections = getAvailableDirections(greed.getBoard(), greed.getLocation());
			if (availableDirections.isEmpty()) {
				out.println("GAME OVER!");
				break;
			}

			out.printf("Enter Direction %s : ", availableDirections);
			String input = scan.next().toUpperCase();
			if (input.equalsIgnoreCase("QUIT")) {
				break;
			}

			Optional<Direction> dir = fromString(input);

			greed.makeMove(dir.orElse(NONE));
		} while (true);
	}
	

	
}
