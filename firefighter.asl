// Agent firefighter in project Firefighters.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!tryToWalk(FF).

/*!start.*/

/* Plans */

!work(FF).

/*+!start : true <- .print("hello world.").*/

+help(X, Y)
	:	noFireAt(FF)
	<-	tryToAssist(FF, X, Y); !work(FF).

+!work(FF)
	:	noFireAt(FF)
	<-	walk(FF); !work(FF).
+!work(FF)
	:	fireAt(FF) & victimsAt(FF) & location(X, Y)
	<-	.broadcast(tell, help(X, Y)); grabVictim(FF); !rescue(FF).	
+!work(FF)
	:	fireAt(FF) & not victimsAt(FF) & weakFireAt(FF)
	<-	squashWeakFire(FF); walk(FF); !work(FF).
+!work(FF)
	:	fireAt(FF) & not victimsAt(FF) & strongFireAt(FF) & location(X, Y)
	<-	.send(plane, achieve, goToFire(X, Y)); walk(FF); !work(FF).
+!work(FF) <- !work(FF).
	
+!rescue(FF)
	:	fireAt(FF)
	<-	walk(FF); !rescue(FF).
+!rescue(FF)
	:	noFireAt(FF)
	<-	dropVictim(FF); !work(FF).
+!rescue(FF) <- !rescue(FF).
