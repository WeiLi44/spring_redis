package com.example.spring_redis;

import com.example.spring_redis.dao.IEmployeeDao;
import com.example.spring_redis.model.Employee;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private IEmployeeDao emp;

	DemoApplicationTests() throws ParseException {
	}
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
	Date date = simpleFormat.parse("2022-03-18");
	Date date2 = simpleFormat.parse("2022-04-20");

	@Test
	@Order(1)
	void saveEmployee() {
		emp.saveEmployee(new Employee("0", "John", "Engineering",8, date));
	}

	@Test
	@Order(2)
	void saveAllEmployee() {
		emp.saveAllEmployees(
				Map.of(
						"1", new Employee("1", "Mary","Creative", 5, date),
						"2", new Employee("2", "Tom", "Account",6, date),
						"3", new Employee("3", "Jane", "Creative",7, date)));
	}

	@Test
	@Order(3)
	void getEmployee() {
		System.out.print("Details here: " + emp.getEmployeeById("2"));
	}

	@Test
	@Order(4)
	void updateEmployee() {
		emp.updateEmployee(new Employee("3", "Pepe", "Engineering",8, date2));
	}

	@Test
	@Order(5)
	void deleteEmployeeById() {
		emp.deleteEmployeeById("0", "John");
	}

	@Test
	@Order(6)
	void getAllEmployees() {
		emp.getAllEmployees().forEach((k,v) -> System.out.print(k + ":" + v));
	}

	@Test
	@Order(7)
	void getSelectedEmployees(){
		List<Employee> newList = Arrays.asList(
				new Employee("4", "Kate", "Creative",10, date2),
				new Employee("5", "Vince", "Account", 10, date2),
				new Employee("6", "Johnson", "Engineering",7, date2),
				new Employee("7", "Charlotte", "Engineering", 4, date2)
		);

		// for single condition
/*		Employee result = newList.stream()
				.filter(x -> "Vince".equals(x.getName()))
				.findAny()
				.orElse(null);

		Employee result2 = newList.stream()
				.filter(x -> "Tete".equals(x.getName()))
				.findAny()
				.orElse(null);

		System.out.print(result);
		System.out.print(result2);*/

		// for multiple conditions
		Employee result3 = newList.stream()
				.filter(e -> "Johnson".equals(e.getName()) && e.getYears()== 7)
				.findAny()
				.orElse(null);

		Employee result4 = newList.stream()
				.filter(e -> "Johnson".equals(e.getName()) && e.getYears()== 10)
				.findAny()
				.orElse(null);

		System.out.print(result3);
		System.out.print(result4);
	}
}
