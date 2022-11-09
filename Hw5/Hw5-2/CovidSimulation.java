import java.util.Queue;
import java.util.PriorityQueue;

class CovidSimulation {
    private int Date = 1; private final City[] cities;
    private final Queue<Event> events = new PriorityQueue<>();

    // Event objects to recording the events attribute with date, city and citizen
    private static class Event implements Comparable<Event> {
        public int date, city, from, citizen, interval;
        public Event(int date, int city, int from, int citizen, int interval) {
            this.date = date; this.city = city; this.from = from; this.citizen = citizen; this.interval = interval;
        }
        /**
         * override the compareTo for the Event objects.
         * @param that the object to be compared.
         * @return the compare relation.
         */
        @Override
        public int compareTo(Event that) {
            return Integer.compare(this.date, that.date);
        }
    }

    // City object to record the city's attribute about citizen, infect and recover status.
    private static class City {
        public int citizen, index;
        private int infectDate, recoverDate;
        City(int citizen, int index) {
            this.citizen = citizen; this.index = index; this.infectDate = 0; this.recoverDate = 0;
        }
        /**
         * Update the recover status, infected date and recover date of city.
         * @param date the update date with corresponding events.
         * @param updateDate the possible update recover date to be compared.
         * @param attack the flag to check that the update is according to the virus attack or not.
         */
        private void update(int date, int updateDate, boolean attack) {
            if(recovered(date)) {
                if(attack){this.infectDate = date; this.recoverDate = date + 4;}
                else{this.infectDate = 0; this.recoverDate = 0;}
            }
            if(this.recoverDate < updateDate) {
                if (updateDate - this.infectDate > 7) this.recoverDate = this.infectDate + 7;
                else this.recoverDate = updateDate;
            }
        }
        /**
         * The function to check the city in indicated date is recovered or not.
         * @param date the indicated date to be checked.
         * @return the boolean status of recovered.
         */
        private boolean recovered(int date) {
            return date >= this.recoverDate;
        }
    }
    /**
     * The constructor of Covid simulation.
     * @param numOfCitizen the number of citizen of each city.
     */
    public CovidSimulation(int[] numOfCitizen) {
        cities = new City[numOfCitizen.length];
        for(int i = 0; i < numOfCitizen.length; i++) cities[i] = new City(numOfCitizen[i], i);
    }
    /**
     * To define the date and city that the covid virus attacks.
     * @param city the index of city being attacked.
     * @param date the data that the virus attack.
     */
    public void virusAttackPlan(int city, int date) {
        events.add(new Event(date, city, -1, 0, -1));
    }
    /**
     * To define the information of traveller from a city to another city during the time interval.
     * @param numOfTraveller the number of traveller.
     * @param fromCity the index of departure city.
     * @param toCity the index of arrival city.
     * @param dateOfDeparture the departure date.
     * @param dateOfArrival the arrival date.
     */
    public void TravelPlan(int numOfTraveller, int fromCity, int toCity, int dateOfDeparture, int dateOfArrival) {
        events.add(new Event(dateOfArrival, toCity, fromCity, numOfTraveller, dateOfArrival - dateOfDeparture));
        events.add(new Event(dateOfDeparture, fromCity, -1, -numOfTraveller, dateOfArrival - dateOfDeparture));
    }

    /**
     * Find the most patients city.
     * @param date the corresponding date.
     * @return the index of city which has the most patients.
     */
    public int CityWithTheMostPatient(int date) {
        while(Date <= date) {
            while(!events.isEmpty() && events.peek().date == Date) {
                Event event = events.poll();
                cities[event.city].citizen += event.citizen;
                if (event.from < 0) cities[event.city].update(Date, 0, event.citizen == 0);
                else if (!cities[event.from].recovered(Date) && cities[event.from].infectDate <= event.date - event.interval)
                    cities[event.city].update(Date, cities[event.from].recoverDate, true);
            }Date++;
        }
        int index = -1, num = 0;
        for(City c: cities) {
            if(!c.recovered(date) && c.citizen >= num) {num = c.citizen; index = c.index;}
        }
        return index;
    }
}