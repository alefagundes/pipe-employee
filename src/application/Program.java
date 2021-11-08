package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		//C:\temp\pipeline.txt
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter with a file path:");
		String path = sc.nextLine();
		
		System.out.println("Enter with a salary:");
		Double salary = sc.nextDouble();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			List<Employee> list = new ArrayList<>();
			while(line != null) {
				String filds[] = line.split(",");
				list.add(new Employee(filds[0], filds[1], Double.parseDouble(filds[2])));
				line = br.readLine();
			}
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> emails = list.stream().filter(e -> e.getSalary() > 2000.00).map(e -> e.getEmail()).sorted(comp) 
					.collect(Collectors.toList());
			
			double sum = list.stream().filter(e -> e.getName().charAt(0)=='M').map(e->e.getSalary()).reduce(0.0, (x, y) -> x + y);
			
			System.out.println();
			emails.forEach(System.out::println);
			System.out.println("\n" + "Sum of salarys of employees whose name star 'M': " + String.format("%.2f", sum));
			
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
