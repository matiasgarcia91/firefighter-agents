// Agent firefighter in project Firefighters.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!tryToWalk(FF).

/*!start.*/

/* Plans */

/*+!start : true <- .print("hello world.").*/

+noFireAt(FF) : hasVictim(FF) <- dropVictim(FF); !tryToWalk(FF).

+!tryToWalk(FF) : noFireAt(FF) <- .print("noFire"); walk(FF); !tryToWalk(FF).

+fireAt(FF) : victimsAt(FF) <- grabVictim(FF); !goAway(FF).

+!tryToWalk(FF) : strongFireAt(FF) <- walk(FF); !tryToWalk(FF).


+fireAt(FF) : not victimsAt(FF) & weakFireAt(FF) <- squashWeakFire(FF); !tryToWalk(FF).
+fireAt(FF) : not victimsAt(FF) & strongFireAt(FF) & location(X, Y) <- .send(plane, achieve, goToFire(X, Y)); !tryToWalk(FF).
+fireAt(FF).


+!goAway(FF) : fireAt(FF) <- walk(FF); !goAway(FF).
+!goAway(FF) : noFireAt(FF) <- !tryToWalk(FF).
+!goAway(FF).

+!tryToWalk(FF) : fireAt(FF) <- walk(FF); !tryToWalk(FF).
