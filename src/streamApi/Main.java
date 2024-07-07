package streamApi;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Person> persons = getPeople();
		
		
		
		//sort(persons);
		//filter(persons, Gender.FEMALE);
		//allMatch(persons,12);
		reduce(persons);
		
	}
	
	
	public static void sort(List<Person> list) {
		
		list.stream().sorted(new Comparator<Person>() {
		
			@Override
			public int compare(Person o1, Person o2) {
				// TODO Auto-generated method stub
				return o1.getAge() - o2.getAge();
			}
			
		})
		.forEach((person) -> System.out.println(person));
		
//		list.stream().sorted(Comparator.comparing(Person::getAge))
//		.forEach((person) -> System.out.println(person));
//		
//		
//		list.stream().sorted((p1,p2) -> p1.getAge() - p2.getAge())
//		.forEach((person) -> System.out.println(person));		
		 
	}
	
	public static void filter(List<Person> persons, Gender gender) {
		persons.stream().filter((person) -> person.getGender().equals(gender)).forEach((person) -> System.out.println(person));
	}
	
	public static void allMatch(List<Person> persons, int age) {
		boolean res = persons.stream().allMatch((person) -> person.getAge() > age);
		System.out.printf("all greater than %d : %b", age,res);
	
	}
	
	public static void reduce(List<Person> persons) {
		// calculate average age
		long count = persons.stream().count();
		Double average = persons.stream().mapToDouble(person -> person.getAge()).reduce(0.0,(prev,curr) -> prev + (curr / count));
		System.out.println(average);
		
	
	}
	
	private static List<Person> getPeople() {
	    return List.of(
	        new Person("Antonio", 20, Gender.MALE),
	        new Person("Alina Smith", 33, Gender.FEMALE),
	        new Person("Helen White", 57, Gender.FEMALE),
	        new Person("Alex Boz", 14, Gender.MALE),
	        new Person("Jamie Goa", 99, Gender.MALE),
	        new Person("Anna Cook", 7, Gender.FEMALE),
	        new Person("Zelda Brown", 120, Gender.FEMALE)
	    );
	  }

}
