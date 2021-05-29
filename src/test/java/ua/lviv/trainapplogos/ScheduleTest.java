package ua.lviv.trainapplogos;

import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

public class ScheduleTest {
	private Schedule schedule;
	
	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		protected void failed(Throwable e, org.junit.runner.Description description) {
			System.out.println("FAILED ---> " + description.getMethodName());
		};
		
		protected void succeeded(org.junit.runner.Description description) {
			System.out.println("SUCCEEDED ---> " + description.getMethodName());
		};
	};
	
	@Before
	public void beforeTest() {
		schedule = new Schedule();
	}
	
	@After
	public void afterTest() {
		schedule = null;
	}
	
	@Test
	public void getSeanceByNameAndTimeTest() {
		Movie testMovie = new Movie("Test movie", new Time(2, 40));
		Seance expectedSeance = new Seance(testMovie, new Time(14, 00));
		
		schedule.addSeance(expectedSeance);
		
		Seance actualSeance = schedule.getSeanceByNameAndTime("Test movie", new Time(14, 00));
		Assert.assertEquals(expectedSeance, actualSeance);
	}
	
	@Test
	public void removeMovieByNameTest() {
		Movie testMovie = new Movie("Test movie", new Time(2, 40));
		Seance testSeance = new Seance(testMovie, new Time(14, 00));
		
		schedule.addSeance(testSeance);
		schedule.removeMovieByName("Test movie");
		
		Set<Seance> seances = schedule.getSeances();
		Iterator<Seance> seance = seances.iterator();
		
		boolean equals = false;
		while (seance.hasNext()) {
			Seance currentSeance = seance.next();
			equals = currentSeance.equals(testSeance);
		}
		Assert.assertFalse(equals);
	}
	
	@Ignore("Have no time to make test for showSeancesTest()")
	public void showSeancesTest() {
		schedule.showSeances();
	}
	
}
