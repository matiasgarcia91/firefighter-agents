// Agent firefighter in project Firefighters.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!tryToWalk(FF).

/*!start.*/

/* Plans */

/*+!start : true <- .print("hello world.").*/

+!tryToWalk(FF) : noFireAt(FF) <- .print("noFire"); walk(FF); !tryToWalk(FF).

+fireAt(FF) : victimsAt(FF) <- grabVictim(FF); !goAway(FF).

+!goAway(FF) : fireAt(FF) <- walk(FF); !goAway(FF).

+!goAway(FF) : noFireAt(FF) <- dropVictim(FF); !tryToWalk(FF).
