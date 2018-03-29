package COSC461.Sample_GreedPlayerAgent.aconover;

import COSC461.Greed.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample "AI"
 *
 * @author Adam J. Conover, D.Sc.
 */
public class RandomPlayer implements GreedAgent {

	@Override
	public String getAgentName() {
		return "A Compiled Random Mover Agent in Java.";
	}

	/**
	 * Given a StateNode, reply with you best move.) *
	 */
	@Override
	public Direction getMove(GreedGame game) {
		// NONE can be returned to end play!!!
		final List<Direction> availableDirections = new ArrayList<>(game.getAvailableDirections());
		
		final int randomIndex = (int) (Math.random() * (availableDirections.size()));
		Direction randomDirection = availableDirections.get(randomIndex);
		
		return randomDirection;
	}

}
