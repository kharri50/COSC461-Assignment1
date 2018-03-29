package COSC461.Sample_GreedPlayerAgent.aconover;

import COSC461.Greed.*;


/**
 * Sample "AI"
 *
 * @author Adam J. Conover, D.Sc.
 */
public class BruteForceBoundedDepthExample implements GreedAgent {

	@Override
	public String getAgentName() {
		return "A Simple/Compiled brute force Agent!";
	}

	/**
	 * Given a StateNode, reply with you best move.) *
	 */
	@Override
	public Direction getMove(GreedGame game) {
		final int MAX_DEPTH = 4;

		StateNode bestMove = tryMoves(game.state(), 0, MAX_DEPTH);
		Direction bestDirection = bestMove.getDirection();

		return bestDirection;
	}
	
	
	
	/**
	 * Recursively Explore all children of the parent node DEPTH-FIRST until
	 * MAX_DEPTH is reached.
	 *
	 * @param parentNode the node to explore
	 * @param depth the current depth.
	 * @return The result of the best child.
	 */
	static StateNode tryMoves(final StateNode parentNode, final int depth, final int maxDepth) {
		if (depth >= maxDepth)
			return parentNode;

		StateNode bestSoFar = parentNode;
		Direction bestDir = Direction.NONE;

		// Recursivly Evaluate each child node to MAX_DEPTH.
		for (Direction direction : GreedGame.getAvailableDirections(parentNode)) {
			// System.out.printf("Trying %d:%n%s%n", depth, GreedGame.testMove(parentNode, direction));

			StateNode childNode = tryMoves(GreedGame.testMove(parentNode, direction), depth + 1, maxDepth);

			if (childNode.getScore() > bestSoFar.getScore()) {
				bestSoFar = childNode;
				bestDir = direction;
			}

			bestSoFar.setDirection(bestDir);
		}

		return bestSoFar;
	}


}
