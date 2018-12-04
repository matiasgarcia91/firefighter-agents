// Agent firefighter in project Firefighters.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!tryToWalk(FF).

/*!start.*/

/* Plans */

/*+!start : true <- .print("hello world.").*/

+noFireAt(FF) : hasVictim(FF) <- dropVictim(FF); !tryToWalk(FF).
+!tryToWalk(FF) : noFireAt(FF) <- walk(FF); !tryToWalk(FF).

+!goAway(FF) : fireAt(FF) <- walk(FF); !goAway(FF).

+fireAt(FF) : victimsAt(FF) & location(X, Y) <- grabVictim(FF); .broadcast(achieve, goToHelp(X, Y)) !goAway(FF).
+fireAt(FF) : not victimsAt(FF) & weakFireAt(FF) <- squashWeakFire(FF); !tryToWalk(FF).
+fireAt(FF) : not victimsAt(FF) & strongFireAt(FF) & location(X, Y) <- .send(plane, achieve, goToFire(X, Y)); !tryToWalk(FF).

/*+!goToHelp(X, Y): noFireAt(FF) <- tryToAssist(FF, X, Y).*/
/*+!goToHelp(X, Y).*/

+!tryToWalk(FF) : fireAt(FF) <- walk(FF); !tryToWalk(FF).
