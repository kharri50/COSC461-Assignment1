/* ****************************************************************************
 * This is a JavaScript implemention of An Agent.
 * 
 * CHANGEME: To Your Script implementation
 * ****************************************************************************/

var myName = "A Scripted Random Mover Agent in JavaScript.";

// Bind to a Java Class.
var Direction = Java.type("COSC461.Greed.Direction");


/*
 * For infrormation about creating Java Objects from Javascript see:
 * 
 * http://www.oracle.com/technetwork/articles/java/jf14-nashorn-2126515.html
 * https://docs.oracle.com/javase/8/docs/technotes/guides/scripting/nashorn/api.html
 * 
 * Bet essentially, as long a the script implements the proper interface methods,
 * (As instances of functions in Javascipt) then the Java VM can treat the script
 * as if it were a "First Class" object.
 * 
 * Examples:
 var javaType = Java.type(agentInterfaceName);    // A Java type from a string
 var javaClass = Java.extend(javaType, jsCode);   // A Java class (usually an interface) to extended via JavaScript.
 var javaImpl = new javaClass();                  // A Java instance of a class which can be used here.
 */

/** 
 * THIS FUNCTION MUST EXIST IN YOUR SCRIPT. It effectivelty just acts as a constructor.
 * 
 * @param {GreedAgent} self
 * @param {java.util.logging.Logger} javaLogger
 **/
function initInstance(self, javaLogger) {
    this.self = self;
    this.logger = javaLogger;

    // Just an example of invoking Java code (if necessary) from Javascript::
    //    var javaClass = Java.type("Some.fully.qualified.Class");
    //    var result = Java.from(javaClass.someMethod());
}


// ********************************************************************
// These are the implementing methods of the GreedAgent interface.
// See the Interface documentation for the parameter details.
// ********************************************************************

// JAVA: Direction getMove(StateNode node);
var getMove = function (game) {
    var availableDirections = game.getAvailableDirections().toArray();

    var randomIndex = Math.floor(Math.random() * (availableDirections.length));
    var randomDirection = availableDirections[randomIndex];

    //this.logger.info( randomDirection.toString());
    return randomDirection;
};

// JAVA: String getAgentName();
var getAgentName = function () {
    return myName;
};
