# Dragons Of Mugloar

## Task Description link 
https://www.dragonsofmugloar.com/
```
So much so that many people discovered a dragon-shaped hole in their hearts and a corresponding amount of space in their barns and began describing themselves as „freelance dragon trainers“. This was before the general public learned the fire hazards of keeping fire-breathing creatures in wooden buildings and the dangers of heart burn, soon after, fresh dragon trainers began investing in stone dragon dens and making the dragon strictly a matter of the brain, not heart.
...
```

## Acceptance Criteria
```
Create a program (using the api documented here) which:

Starts a new game
Fetches a list of ads
Picks the best ads to solve and solves them
Optionally buys items to improve its chances.
Repeats from step two until lives run out.
Your solution is considered acceptable when the application is reliably able to achieve a score of at least 1000 points.
```

## Installation and build program

Please add java (plain java) library into path use the intelijj or Eclipse.

``` Required Java 
$ javac -cp "../libs/common.jar" Game.java 
$ java -cp "../libs/common.jar" Game
```

## Filtering and Sorting Logic

```Java
messages
.stream()
.filter(m -> !CommonUtility.isTrapMessage(m))
.filter(c -> ProbabilityUtility.checkProbabilityType(c.getProbability()) != ProbabilityType.IGNORE)
.peek(x -> x.setRank(CommonUtility.applyRating(x)))
.peek(x -> x.setProbabilityRank(ProbabilityUtility.probabilityRanking(x.getProbability())))
.sorted(Comparator.comparing(MessageDto::getProbabilityRank)
    .thenComparing(MessageDto::getExpiresIn)
    .thenComparing(MessageDto::getRank)
    .thenComparing(MessageDto::getReward)
).collect(Collectors.toList());
```

## LIMITATIONS

Currently, I am using Java 11 on my developing machine and due to which I am facing issue while mock http response using power mokito. Also tried to use spring-boot no web application to complete task and mock it but that is also throwing same error. So, I written Plain Java test case

