// Agent firefighter in project Firefighters.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!tryToWalk(FF).

/*!start.*/

/* Plans */

/*+!start : true <- .print("hello world.").*/

+fireAt(FF) : victimsAt(FF) <- grabVictim(FF).
+!tryToWalk(FF) : noFireAt(FF) <- .print("noFire"); walk(FF); !tryToWalk(FF).
