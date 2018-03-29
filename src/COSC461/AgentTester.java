package COSC461;

import COSC461.Greed.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.*;

/**
 * This is just an example of a simple "Agent Tester". Notice how there really
 * isn't much to it! You just need your own simulated world and state... which
 * is just a few lines of code.
 *
 * @author Adam J. Conover
 */
public class AgentTester {

	// Of course... any of the "Constants" here could very easilly be command-line
	// parameters for much greater flexability, but they are hardcoded here as to 
	// not cloud the example with a bunch of command-line parsing code.
	static final int GRID_SIZE = 25;
	static final int MAX_GRID_VALUE = 9;
	static final int RANDOM_SEED = 0;

	static final boolean SHOW_DEBUGGING_BOARDS = false;

	/* Test the agents... */
	public static void main(String[] args) throws IOException, FileNotFoundException {
		// FOR DEBUGGING: Only show severe message from the agent loader.
		Logger.getLogger("AGENT_LOADER").setLevel(Level.SEVERE);
		
		// For testing to see if two agents have the same name, to prevent duplicate loading.
		final Comparator<GreedAgent> agentComparator = (a1, a2) -> a1.getAgentName().compareTo(a2.getAgentName());
//		final Comparator<GreedAgent> agentComparator = (a1, a2) -> 1;   // Or if we want duplicates....
		
		// Set of agents to test/play
		Set<GreedAgent> agentSet = new TreeSet<GreedAgent>(agentComparator);

		// Display the Scripting Engines which are available
		showAvailableEngines();
		System.out.println();

		
		
		
		// *******************************************************************
		// ***** START: EXAMPLE OF ADDING PARTICIPATING AGENTS TO A SET ******
		// ***** Of Course, not all of these are necessary (they are just examples)!!!!
		
		// Example of adding a Java (compiled) Agent to the agent set:
		final String COMPILED_TESTING_AGENT = "COSC461.Sample_GreedPlayerAgent.aconover.BruteForceBoundedDepthExample";

		agentSet.add(AgentLoader.getCompiledAgent(COMPILED_TESTING_AGENT).get());

		// Example of adding a Scripted (interpreted) Agent to the agent set:
		final String SCRIPTED_TESTING_AGENT = System.getProperty("user.dir") + "\\src\\COSC461\\Sample_ScriptedAgents\\GamePlayerAgentScript_1.js";

		agentSet.add(AgentLoader.getScriptedAgent(SCRIPTED_TESTING_AGENT).get());

		// Example of adding all agents in a particular Java PACKAGE to the set:
		final String COMPILED_AGENTS_PACKAGE = "COSC461";

		agentSet.addAll(AgentLoader.getAllCompiledAgents(COMPILED_AGENTS_PACKAGE));

		// Example of adding all scripts in a particular directory to the set
		final String SCRIPTED_AGENTS_DIRECTORY = System.getProperty("user.dir") + "\\src\\COSC461\\Sample_ScriptedAgents";

		agentSet.addAll(AgentLoader.getAllScriptedAgents(SCRIPTED_AGENTS_DIRECTORY));

		// ***** END: EXAMPLE OF ADDING PARTICIPATING AGENTS TO A SET ******
		// *****************************************************************
		
		
		// Show all of the agents which have been loaded.
		showAgentInfo(agentSet);
		System.out.println();

		
		
		
		// Iterate over all agents and have them play....
		for (GreedAgent agent : agentSet) {
			System.out.println("Now Playing: " + agent.getAgentName());

			// Game object declaration.
			GreedGame greedGame;

			// If a filename was passed in, use it.  Otherwise use a randomly generated grid.
			if (args.length >= 1 && (new File(args[0])).exists()) {
				try (Scanner gridFile = new Scanner(new File(args[0]))) {
					greedGame = new GreedGame(() -> gridFile.nextInt(), gridFile.nextInt(), gridFile.nextInt());
				}
			} else {
				Random RND = new Random(RANDOM_SEED);
				greedGame = new GreedGame(() -> RND.nextInt(MAX_GRID_VALUE) + 1, GRID_SIZE, GRID_SIZE);
			}

			testAgent(agent, greedGame, SHOW_DEBUGGING_BOARDS);
		}
	}

//
//
// *********************************************************************** 
// *********** NO CHANGES SHOULD BE NECESSARY BELOW THIS POINT ***********
// *********************************************************************** 
//
//
/* ********************************************************************* */
// Testing Loop. Simulating two copies of the same agent playing against each other.
	static void testAgent(GreedAgent player, GreedGame game, boolean bShowBoardStates) {
		while (game.hasPlayableMoves()) {

			// Ask the agent for it's move, given a gameState node.
			Direction dir = player.getMove(game);

			// System.out.println("Making Move: " + dir);
			if (dir == Direction.NONE) {
				break;
			}

			StateNode oldState = game.state();
			StateNode newState = game.makeMove(dir);

			// Compare States to make sure progress is being made.
			if (newState.equals(oldState)) {
				System.out.println(player.getAgentName() + " was making no progress!");
				break;
			}

			// Print the entire game grid.
			if (bShowBoardStates)
				System.out.println(game);
		}

		System.out.printf("Scored: %d%n%n", game.getScore());
	}

	// Information about each agent.
	private static void showAgentInfo(Set<GreedAgent> agents) {
		// Just display some information...
		System.out.println("Found the following valid Agents:");
		final String fmt = "\tName: %-50s (Class: %s)";
		agents.stream()
			.map(x -> String.format(fmt, x.getAgentName(), x.getClass().toGenericString()))
			.forEach(System.out::println);
	}

	// Information about each engine.
	private static void showAvailableEngines() {
		// Show what Scripting engines are available.
		System.out.println("Engines:");
		AgentLoader.availableEngineInfo().stream()
			.map(x -> String.format("\t%s", x))
			.forEach(System.out::println);
	}

}
