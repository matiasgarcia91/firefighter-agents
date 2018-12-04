// Agent firefighter in project Firefighters.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!tryToWalk(FF).

/*!start.*/

/* Plans */

/*+!start : true <- .print("hello world.").*/

+!tryToWalk(FF) : noFireAt(FF) <- .print("noFire"); walk(FF); !tryToWalk(FF).

+fireAt(FF) : victimsAt(FF) <- grabVictim(FF); !goAway(FF).


+fireAt(FF) : not victimsAt(FF) & weakFireAt(FF) <- squashWeakFire(FF); walk(FF); !tryToWalk(FF).
+fireAt(FF) : not victimsAt(FF) & strongFireAt(FF) & location(X, Y) <- .send(plane, achieve, goToFire(X, Y)); walk(FF); !tryToWalk(FF).

+!goAway(FF) : fireAt(FF) <- walk(FF); !goAway(FF).
+!goAway(FF) : noFireAt(FF) <- dropVictim(FF); !tryToWalk(FF).

+!tryToWalk(FF).

/* +!tryToWalk(FF) : fireAt(FF) <- walk(FF); .print(" ==============  NADAAAA =============="); !tryToWalk(FF). */
