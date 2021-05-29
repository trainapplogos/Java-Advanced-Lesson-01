package ua.lviv.trainapplogos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;



public class CinemaTest {
	private Cinema cinema;
	
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
		Time opened = new Time(8, 0);
		Time closed = new Time(23, 0);
		cinema = new Cinema("KingCross Leopolis", opened, closed);
	}
	
	@After
	public void afterTest() {
		cinema = null;
	}
	
	@Test
	public void addMovieTest() {
		Movie testMovie = new Movie("Test movie", new Time(2, 40));
		cinema.addMovie(testMovie);
		Iterator<Movie> movies = cinema.getMoviesLibrary().iterator();
		
		boolean moviesEquals = false; 
		
		while (movies.hasNext()) {
			Movie movie = movies.next();
			moviesEquals = movie.equals(testMovie);
		}
		
		Assert.assertTrue(moviesEquals);
	}
	
	@Test
	public void removeMovieFromLibTest() {
		Movie testMovie = new Movie("Test movie", new Time(2, 40));
		cinema.addMovie(testMovie);
		cinema.removeMovieFromLib(testMovie);
		
		Iterator<Movie> movies = cinema.getMoviesLibrary().iterator();
		
		boolean moviesEquals = false; 
		
		while (movies.hasNext()) {
			Movie movie = movies.next();
			moviesEquals = movie.equals(testMovie);
		}
		
		Assert.assertFalse(moviesEquals);
	}
	
	@Test
	public void removeMovieByNameTest() {
		Movie testMovie = new Movie("Test movie", new Time(2, 40));
		cinema.addMovie(testMovie);
		
		cinema.removeMovieByName("Test movie");
		
		Iterator<Movie> movies = cinema.getMoviesLibrary().iterator();
		
		boolean moviesEquals = false; 
		
		while (movies.hasNext()) {
			Movie movie = movies.next();
			moviesEquals = movie.equals(testMovie);
		}
		
		Assert.assertFalse(moviesEquals);
	}
	
	
//	@Ignore
	@Test
	public void addSeanceTest() throws Exception {
		Movie testMovie = new Movie("Test movie", new Time(2, 40));
		Seance seance = new Seance(testMovie, new Time(14, 00));
		
		cinema.addSeance(seance, "sunday");
		
		Schedule schedule = cinema.getScheduleByDay("sunday");
		Set<Seance> seancesList = schedule.getSeances();
		Iterator<Seance> seances = seancesList.iterator();
		
		boolean equals = false;
		
		while (seances.hasNext()) {
			Seance currentSeance = seances.next();
			equals = currentSeance.equals(seance);
		}
		Assert.assertTrue(equals);
	}
	
	@Test
	public void removeSeanceTest() throws Exception {
		Movie testMovie = new Movie("Test movie", new Time(2, 40));
		Seance seance = new Seance(testMovie, new Time(14, 00));
		cinema.addSeance(seance, "sunday");
		
		cinema.removeSeance(seance, "sunday");
		
		Schedule schedule = cinema.getScheduleByDay("sunday");
		Set<Seance> seancesList = schedule.getSeances();
		Iterator<Seance> seances = seancesList.iterator();
		
		boolean equals = false;
		
		while (seances.hasNext()) {
			Seance currentSeance = seances.next();
			equals = currentSeance.equals(seance);
		}
		Assert.assertFalse(equals);
	}
	
	
	
	@Test
	public void stringToDaysTest() throws Exception {
		Days realDay = cinema.stringToDays("sunday");
		Days expectedValue = Days.SUNDAY;
		Assert.assertEquals(expectedValue, realDay);
	}
	
	
	@Test(expected = Exception.class)
	public void stringToDaysWithExceptionTest() throws Exception {
		Days realDay = cinema.stringToDays("Sunnnnday!dsfsdfsdf");
		Days expectedValue = Days.SUNDAY;
		Assert.assertEquals(expectedValue, realDay);
	}
	
	@Test
	public void getScheduleByDayTest() throws Exception {
		Schedule expectedSchedule = cinema.getScheduleByDay("sunday");
		TreeMap<Days, Schedule> schedules = cinema.getSchedules();
		Schedule realSchedule = schedules.get(Days.SUNDAY);
		Assert.assertEquals(expectedSchedule, realSchedule);
	}
	
	@Test(expected = Exception.class)
	public void getScheduleByDayWithExceptionTest() throws Exception {
		Schedule expectedSchedule = cinema.getScheduleByDay("SUNdayQwerty");
		TreeMap<Days, Schedule> schedules = cinema.getSchedules();
		Schedule realSchedule = schedules.get(Days.SUNDAY);
		Assert.assertEquals(expectedSchedule, realSchedule);
	}
	
	@Ignore("Have no time to make test for showSeancesTest()")
	public void showSeancesTest() throws Exception {
		cinema.showSeances("sunday");
	}
	
	
	@Ignore("Have no time to make test for showMoviesListTest()")
	public void showMoviesListTest() throws Exception {
		cinema.showMoviesList();
	}
	
	@Test
	public void getMovieByNameTest() {
		Movie actualMovie = new Movie("Test movie", new Time(2, 40));
		cinema.addMovie(actualMovie);
		
		Movie expectedMovie = cinema.getMovieByName("Test movie");
		
		Assert.assertEquals(expectedMovie, actualMovie);
	}
	
}
