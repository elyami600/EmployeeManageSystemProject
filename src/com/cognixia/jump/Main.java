package com.cognixia.jump;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.exceptions.EmployeeNotFoundException;
import com.cognixia.jump.model.Employee;
import com.cognixia.jump.util.EmployeeManager;
import com.cognixia.jump.util.EmployeeManagerInMemory;

// CRUD Operations - Create Read Update Delete

// Create an EMS that allows us to:
// 1. view all employees
// 2. view particular employee
// 3. create employees
// 4. delete employees
// 5. update employees
// 6. view all employees in a singular department (ex: all employees in HR)
// Expect: Console Based Menu


/*
 * Assignment:
 * - finish the EmployeeManagerInMemory (implement rest of methods)
 * - set up the create employee section of the menu
 * - send it over through slack (files, zip, or github)
 * */

public class Main {

	private static EmployeeManager manager;
	private static Scanner sc;

	public static void main(String[] args) {

		manager = new EmployeeManagerInMemory();
		//manager = new EmployeeManagerFile();

		sc = new Scanner(System.in);

		System.out.println("WELCOME TO THE EMPLOYEE MANAGEMENT SYSTEM (EMS)\n");

		mainMenu();

	}

	public static void mainMenu() {

		while (true) {

			try {
				System.out.println("\nPlease enter one of the following options :"
						+ "\n1.) View Employees"
						+ "\n2.) Select Employee By Id"
						+ "\n3.) Create Employee"
						+ "\n4.) Update Employee"
						+ "\n5.) Delete Employee"
						+ "\n6.) Exit");

				int option = sc.nextInt();
				sc.nextLine(); // getting rid of new line character

				switch (option) {
					case 1:
						viewEmployees();
						break;

					case 2:
						getEmployById();
						break;
					case 3:
						getInputToCreateAnEmployee();
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						break;

					default:
						System.out.println("\nPlease enter a number between 1 and 6");
						break;
				}

				if (option == 6) {
					break;
				}

			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nPlease enter a number between 1 and 6");
			}

		}

	}

	public static void viewEmployees() {


		while(true) {

			try {

				System.out.println("Select one of the follow:" +
						"\n1. Select all employees" +
						"\n2. Select employees by department" +
						"\n3. Exit to return to main menu");

				int option = sc.nextInt();
				sc.nextLine();

				switch (option) {
					case 1:
						viewAllEmployees();
						break;
					case 2:
						getEmployeeByDepart();

						break;
					case 3:
						break;
					default:
						System.out.println("Enter number between 1 and 3");
						break;
				}

				if(option == 3) {
					break;
				}


			} catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("Enter number between 1 and 3");
			}
		}

	}

	public static void viewAllEmployees() {
		List<Employee> employees = manager.getAllEmployees();


		if(employees.isEmpty()) {
			System.out.println("No employees currently in EMS");
		}
		else {
			for(Employee e : employees) {
				System.out.println(e);
			}
		}
	}

	public  static  void getEmployById() {
		try {
			System.out.println("Please enter employee ID ");
			int id  = sc.nextInt();
			viewEmployeeByID(id);

		}
		catch (InputMismatchException e) {
			System.out.println(e);
		}
	}
	public static void viewEmployeeByID(int id)  {


		try {
			Employee employee = manager.findEmployeeById(id);
			System.out.println(employee);

		}
		catch (EmployeeNotFoundException e)  {
			System.out.println("not find");

		}


	}







	// create a new Employee and than add to the ArrayList
	public static boolean CreateEmployee( int id ,String name, String depart, int salary, String email) {


		Employee newEmployee = new Employee(id, name, depart, salary, email);

		boolean employees = manager.createEmployee(newEmployee);

		if(employees) {
			System.out.println("Please try again");
			getInputToCreateAnEmployee();

		}
		else {
			System.out.println("Employee successful was created");
			// add the last index where is the recent employee added
			// ask to the user if they  like to add another Employeee

		}
		return  employees;


	}
	public static void getInputToCreateAnEmployee() {
		System.out.println("Please enter employee ID ");
		int id = sc.nextInt();


		System.out.println("Please enter employee Department");
		String department = sc.next();


		System.out.println("Please enter employee Name ");
		String name = sc.next();


		System.out.println("Please enter employee  Salary");
		int salary = sc.nextInt();


		System.out.println("Please enter employee Email");
		String email = sc.next();


		CreateEmployee(id, name, department, salary, email);


	}
	public  static  boolean deleteEmployeeById(int id) {
		boolean employee =  manager.deleteEmployee(id);

		if(employee) {
			System.out.println("Employee was deleted ");
		}
		else {
			System.out.println("Try again ");
		}
		return employee;
	}



	public static void getEmployeeByDepart() {

		try {
			System.out.println("Please enter employee by department ");
			String departID = sc.nextLine();
			viewEmployeeByDepartment(departID);


		}
		catch (InputMismatchException e) {
			System.out.println(e);
		}




	}
	public static  void viewEmployeeByDepartment(String str) {
		List<Employee> employees = manager.getEmployeesByDepartment(str);

		if(employees.isEmpty()) {
			System.out.println("No find id found ");

		}

		else {
			for (Employee e : employees) {
				System.out.println(e);
			}
		}

	}
	public static int readInt() throws InputMismatchException {

		int  value = sc.nextInt();

		return value;
	}

	public static String readString(Scanner sc) throws InputMismatchException {

		String string = sc.next();

		return string;
	}

}
