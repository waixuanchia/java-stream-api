package streamApi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Person> persons = getPeople();
		
		
		
		// sort the person based on age
		//sort(persons);
		
		// filter the person if the person gender is Male
		//filter(persons, Gender.FEMALE);
		
		// determine all the persons is greater than 12 years old
		//allMatch(persons,12);
		
		//reduce(persons);
		
		// find the person with max age and return the person name
		collectingAndThen(persons);
		
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
	
	public static void collect(List<Person> persons) {
		List<Double> numsInDouble = persons.stream().mapToDouble(person -> person.getAge()).boxed().collect(Collectors.toList());
		System.out.println(numsInDouble);
		
		ArrayList<Double> numsInDouble2 = persons.stream()
												.mapToDouble(person -> person.getAge())
												.collect(() -> new ArrayList<Double>(), (prev,curr) -> {
													prev.add(curr);
												},(prev,curr) -> {
													prev.addAll(curr);
												});
		
		ArrayList<Double> numsInDouble3 = persons.stream()
												.mapToDouble(Person::getAge)
												.collect(ArrayList<Double>::new, ArrayList<Double>::add,ArrayList<Double>::addAll);
		
		System.out.println(numsInDouble2);
	}
	
	public static void collectingAndThen(List<Person> persons) {
		
		String personWithMaxAge = persons.stream()
		.collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Person::getAge)), (person) -> {
			if(person.isPresent()) {
				
				return person.get().getName();
			}
			return null;
		}));
		if(personWithMaxAge != null) {
			System.out.println(personWithMaxAge);
		}
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
