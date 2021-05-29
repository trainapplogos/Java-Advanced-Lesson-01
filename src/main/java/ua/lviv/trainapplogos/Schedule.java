package ua.lviv.trainapplogos;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Schedule {
	private Set <Seance> seances = new TreeSet<>(new sortSeancesByAllFields());

	public void removeMovieByName(String name) {
		seances.removeIf(m -> m.getTitle().equalsIgnoreCase(name));
	}
	
	//get seance by name of the movie and start time
	public Seance getSeanceByNameAndTime(String movieName, Time startTime) {
		Iterator<Seance> iterator = seances.iterator();
		
		while(iterator.hasNext()) {
			Seance seance = iterator.next();
			if (seance.getTitle().equalsIgnoreCase(movieName) &&
					(seance.getStartTime().compareTo(startTime) == 0)) {
				return seance;
			}
		}
		
		return null;
	}
	
	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}

	public void addSeance(Seance seance) {
		seances.add(seance);
	}
	
	public void removeSeance(Seance seance) {
		seances.remove(seance);
	}
	
	public void showSeances() {
		AtomicInteger ind = new AtomicInteger(1);
		seances.forEach(s -> System.out.println("  " + ind.getAndIncrement() + ") " + s)); 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seances == null) ? 0 : seances.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (seances == null) {
			if (other.seances != null)
				return false;
		} else if (!seances.equals(other.seances))
			return false;
		return true;
	}
	
}

class sortSeancesByAllFields implements Comparator<Seance> {
	@Override
	public int compare(Seance sOne, Seance sTwo) {
		if (sOne.getMovie().compareTo(sTwo.getMovie()) == 0) { 
			int startTimeComparator = sOne.getStartTime().compareTo(sTwo.getStartTime()); //if time of 1st movie is lower, the result will be negative.
			
			if (startTimeComparator == 0) {
				int endTimeComparator = sOne.getEndTime().compareTo(sTwo.getEndTime());
				
				if (endTimeComparator == 0) {
					return 0;
				} else return endTimeComparator;
			} else return startTimeComparator;
		} else return 1; //1 if the movies are different 
	}
}
