# Covid Simulation
`2022PDSA` 、 `Priority Queue` 、 `Event driven `、 `Covid Simulation`

Due to the covid situation, travelling is concerning, but it is obvious that some people care less than the others. To simulate the actual situation is rather difficult. Therefore, we are going to simplify the situation and simulate it according to the following rules:

* The Virus would attack a specific `City_i` at `Day_i` randomly according to the function ‘virusAttackPlan’
* The whole city would be infected immediately, if the city was attacked by the virus or an infected traveller entered the city.
* The recovery rules are as follows:
    * An infected city will **recover after 4 days** by default.
    * For an infected city, if there are travellers from another city which was infected more recently, the recovery date for the city would be extended according to the travellers. If the travellers were infected earlier, the recovery date of the city wouldn’t be changed instead.
    * The recovery time for any given city, would be **7 days at max**, regardless of any recent infected traveller, but it is possible for the city to be infected on the 8th day, which would be a new cycle.
    * If a virus attacks a city which was already infected, there will be no effect on the recovery date.
    
* Not so important definition:
    * If a virus attack/infected traveller enter the city on the departure date of a traveller, assume that the traveller would always be **infected before departure.**
    * Any traveller on the road is not considered part of any city.
    * A traveller is **not** considered part of the city on the date of departure but would be considered part the destination city on the date of arrival.
    * Assume no traveller would be infected by another traveller on the road.
    * Our Calendar has neither months nor years, it only counts from day 1 to day N.
    * We guaranteed that no `TravelPlan` & `virusAttackPlan` would have dates before the last `CityWithTheMostPatient `date.
    * We also guarantee that date of `CityWithTheMostPatient` would be strictly increasing.
    * We guarantee that the number of citizens in a city wouldn’t be less than one after TravelPlan.

Please return the city with the most patients on a certain day with the given information when the function `CityWithTheMostPatient` is called. If there are more than two cities with the same amount of patients, return the one with the larger index.

## Hint
1. Event-Driven.
2. Study about Comparable if using Priority Queue.
3. Try to list out all the events
4. Find the chronological order between all the events
(eg. infection would always happen before departure.)

## Test Data
Time Limit: `10ms`

* case1: NumOfCity < 100, MaxDate < 1000, MaxPeople < 1000
* case2: NumOfCity < 200, MaxDate < 2000, MaxPeople < 2000
* case3: NumOfCity < 1000, MaxDate < 10000, MaxPeople < 10000
* case4: NumOfCity < 2000, MaxDate < 20000, MaxPeople < 20000
* case5: NumOfCity < 20000, MaxDate < 30000, MaxPeople < 200000