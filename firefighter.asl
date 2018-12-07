// Agent firefighter in project Firefighters.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!tryToWalk(FF).

/*!start.*/

/* Plans */

/*+!start : true <- .print("hello world.").*/


+!saveVictims(FF): true <- grabVictim(FF); !goAway(FF).
+!squashWeak(FF) : true <- squashWeakFire(FF); walk(FF); !tryToWalk(FF).
+!squashStrong(FF) : location(X, Y) <- .send(plane, achieve, goToFire(X, Y)); .print("plane called"); walk(FF); !goAway(FF).
+!goAway(FF) : noFireAt(FF) <- dropVictim(FF); walk(FF); !tryToWalk(FF).

+fireAt(FF) : victimsAt(FF) <- !saveVictims(FF).
+fireAt(FF) : not victimsAt(FF) & weakFireAt(FF) <- !squashWeak(FF).
+fireAt(FF) : not victimsAt(FF) & strongFireAt(FF) <- !squashStrong(FF).

+!goAway(FF) : fireAt(FF) <- walk(FF); !goAway(FF).
+!tryToWalk(FF) : noFireAt(FF) <- walk(FF); !tryToWalk(FF).


/* +!tryToWalk(FF) : fireAt(FF) <- walk(FF); .print(" ==============  NADAAAA =============="); !tryToWalk(FF). */
