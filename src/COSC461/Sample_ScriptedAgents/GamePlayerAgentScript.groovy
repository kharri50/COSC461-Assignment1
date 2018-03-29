package COSC461.GreedPlayerAgent

import COSC461.Greed.*;


def initInstance(self, javaLogger) {
    this.self = self;
    this.logger = javaLogger;
}


// ********************************************************************
// These are the implementing methods of the GreedAgent interface.
// See the Interface documentation for the parameter details.
// ********************************************************************

// JAVA: Direction getMove(StateNode node);
def getMove(game) {
    // NONE can be returned to end play!!!
    
    def List<Direction> availableDirections = new ArrayList<>(game.getAvailableDirections());
		
    def randomIndex = (int) (Math.random() * (availableDirections.size()));
    def randomDirection = availableDirections.get(randomIndex);
		
    randomDirection;
};



// JAVA: String getAgentName();
def getAgentName() {
    "A Random Mover Agent in Groovy"
};
