// Zainab Syed

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FinalProject {
	// Prints the menu and returns a selection
	private static int menu() {
		int option = 0;
		try {
			System.out.println("\n\nChoose one of the options:\n");
			System.out.println("  1. Enter the information a faculty");
			System.out.println("  2. Enter the information of a student");
			System.out.println("  3. Print tuition invoice for a student");
			System.out.println("  4. Print faculty information");
			System.out.println("  5. Enter the information of a staff member");
			System.out.println("  6. Print the information of a staff member");
			System.out.println("  7. Delete a person");
			System.out.println("  8. Exit Program");
			System.out.print("\tEnter your selection: ");
			option = (new Scanner(System.in).nextInt());
			return option;
		}
		catch(Exception e) {
			//System.out.println(e.getMessage());
			System.out.println("Invalid entry please try again\n");
			option = menu();
		}
		return option;
	}
	
	// Finds a person in the list
	private static Person findPerson(LinkedList<Person> list, String value) {
		for(Person element: list) {
			if(element.getId().equalsIgnoreCase(value) ) {
				return element;
			}
		}
		return null;
	}
		
//___________________________________________________________________________
	public static void main(String[] args) {
		// creates a list of type Person
		LinkedList <Person> list = new LinkedList<>();
		int selection = 0;
		System.out.println("\t\t\t Welcome to my Personal Management Program\n");
		while (selection != 8) {
			selection = menu();
			// Adds a faculty member
			if (selection == 1) {
				Faculty member = new Faculty();
				System.out.println("Enter the faculty info:");
				System.out.print("\tName of the faculty: ");
				
				try {					
					String name = (new Scanner(System.in).nextLine());
					String id = member.validID();
					// checks if the id is already in the list
					while (findPerson(list, id) != null) {
						System.out.println("ID is not unique, please try again.");
						id = member.validID();
					}
					
					String rank = member.validRank();			
					String Department = member.validDepartment();
					
					// Sets all information for a faculty member 
					member = new Faculty(name, id, rank, Department);
					list.add(member);					
					System.out.println("Faculty added!");					
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}				
			}
			// Adds a student
			else if(selection == 2) {
				Student student = new Student();
				System.out.println("Enter the student info:");
				System.out.print("\tName of Student: ");
				try {
					String name = (new Scanner(System.in).nextLine());
					
					String id = student.validID();
					// checks if the id is already in the list
					while (findPerson(list, id) != null) {
						System.out.println("ID is not unique, please try again.");
						id = student.validID();
					}
					
					System.out.print("\tGPA: ");
					Double gpa = (new Scanner(System.in).nextDouble());
					
					System.out.print("\tCredit hours: ");
					int creditHours = (new Scanner(System.in).nextInt());
					
					// Sets all information for a student
					student = new Student(name, id, gpa, creditHours);					
					list.add(student);
					System.out.println("Student added!");
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
			}
			// finds a student in the list
			else if(selection == 3) {
				Person temp = new Student();
				System.out.print("Enter the student's id: ");
				String id = (new Scanner(System.in).nextLine());
				temp = findPerson(list, id);
				if(temp != null) {
					System.out.println("Here is the tuition invoice for "+temp.getName()+":");
					temp.print();
				}
				else {
					System.out.println("No students matched!");
				}
			}			
			// Finds a faculty member in the list
			else if(selection == 4) {
				Person temp = new Faculty();
				System.out.print("\tEnter the Faculty's id: ");
				String facultyID = (new Scanner(System.in).next());
				temp = findPerson(list, facultyID);
				if (temp != null) {
					temp.print();
				}
				else {
					System.out.println("No faculty matched!");
				}
			}
			// Adds a staff member
			else if(selection == 5) {
				Staff staff = new Staff();
				System.out.print("\tName of the staff member: ");
				try {
					String name = (new Scanner(System.in).nextLine());
					
					String id = staff.validID();
					// checks if the id is already in the list
					while (findPerson(list, id) != null) { 
						System.out.println("ID is not unique, please try again.");
						id = staff.validID();
					}
					
					String Department = staff.validDepartment();					
					String status = staff.validStatus();
					
					// sets the staff member information
					staff = new Staff(name, id, status, Department);
					list.add(staff);
					System.out.println("Staff member added!");
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			// Finds the staff member
			else if(selection == 6) {
				Person temp = new Staff();
				System.out.print("\tEnter the Staff's id: ");
				String staffID = (new Scanner(System.in).nextLine());
				temp = findPerson(list, staffID);
				if (temp != null) {
					temp.print();
				}
				else {
					System.out.println("No staff member matched!");
				}
			}
			// deletes a person from the list
			else if(selection == 7) {
				Person temp;
				System.out.print("Enter the id of the person to delete: ");
				String personID = (new Scanner(System.in).nextLine());
				temp = findPerson(list, personID);
				if(temp != null) {
					list.remove(temp);
					System.out.println(personID +" was removed");
				}
				else {
					System.out.println("Sorry no such person exists.");
				}
			}			
			// testing to check list
//			for(Person element: list)
//				System.out.println(element.getName() +" "+ element.getId());				
		}
		// Ends program and creates report
		if (selection == 8) {
			System.out.print("\n Would you like to create the report?(Y/N)");
			try {
				String report = (new Scanner(System.in).next());				
				if(report.compareToIgnoreCase("Y") == 0) {
					String newFileName = "report.txt";
					PrintWriter writer = new PrintWriter (newFileName);
					
					System.out.print("Would you like to sort your students by descending gpa or name (1 for gpa, 2 for name): ");
					int choice = 0;
					choice = (new Scanner(System.in).nextInt());
					
					// Gets current date
					LocalDate currentDate = LocalDate.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
									
					String formattedDate = currentDate.format(formatter);
					
					writer.print("\t\tReport created on " + formattedDate+ "\n");
					writer.print("****************************\n\n");
					
					// Creates lists holding each subclass
					LinkedList <Faculty> faculty= new LinkedList<>();
					LinkedList <Staff> staff= new LinkedList<>();
					LinkedList <Student> student= new LinkedList<>();
										
					for(Person person: list) {
						if(person instanceof Faculty)
							faculty.add((Faculty)person);
						if(person instanceof Staff)
							staff.add((Staff)person);
						if(person instanceof Student)
							student.add((Student)person);
					}
					
					// Prints the Faculty list onto the report file
					writer.print("Faculty Members\n");
					writer.print("---------------\n");
					int count = 0;
					for(Faculty member: faculty) {
						count++;
						writer.print("\t"+count+". "+member.getName()+"\n");
						writer.print("\tID: "+member.getId()+"\n");
						writer.print("\t"+member.getRank()+","+member.getDepartment()+"\n");
						writer.print("\n");
					}
					
					// Prints the Staff list onto the report file
					writer.println("Staff Members");
					writer.println("---------------");
					count = 0;
					for(Staff member: staff) {
						count++;
						writer.print("\t"+count+". "+member.getName()+"\n");
						writer.print("\tID: "+member.getId()+"\n");
						String stat = member.getStatus();
						// determines if staff is full or part time
						if (stat.compareToIgnoreCase("F") == 0) {
							stat = "Full Time";
						}
						else if (stat.compareToIgnoreCase("P") == 0) {
							stat = "Part Time";
						}
						writer.print("\t"+member.getDepartment()+","+stat+"\n");
						writer.print("\n");
					}
					
					// Prints the list of students onto the report file
					writer.println("Students");
					writer.println("---------------");
					
					// sorts based on GPA
					if (choice == 1) {
						Collections.sort(student, new SortbyGPA());						
					}
					else if(choice == 2) { // Sorts based on name
						Collections.sort(student, new Sortbyname());
					}
					
					// prints list of students
					count = 0;
					for(Student member: student) {
						count++;
						writer.print("\t"+count+". "+member.getName()+"\n");
						writer.print("\tID: "+member.getId()+"\n");
						writer.print("\tGPA: "+member.getGpa()+"\n");
						writer.print("\tCredit Hours: "+member.getCredit_hours()+"\n");
						writer.print("\n");
					}
					writer.close();
					System.out.println("Report created and save on your hard drive!");
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}			
		}		
		System.out.println("Goodbye!");
	}
}
// Descending order of gpa
class SortbyGPA implements Comparator<Student> {

	@Override
	public int compare(Student a, Student b) {
		return Double.compare(b.getGpa(), a.getGpa());
	}
}

class Sortbyname implements Comparator<Student> {
	@Override
	public int compare(Student a, Student b) {
		return a.getName().compareTo(b.getName());
	}
}

abstract class Person {
	private String name;
	private String id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private int isValidID(String checkingID) {		
		int valid = 1;
		
		if(checkingID.length() != 6) {			
			return 0; //valid = 0;
		}
		if(!Character.isLetter(checkingID.charAt(0)) || !Character.isLetter(checkingID.charAt(1))) {
			return 0; //valid = 0;			
		}
		for(int i = 2; i < 6; i++) {
			if(!Character.isDigit(checkingID.charAt(i)))
				return 0; //valid = 0;
		}		
		return valid;
	}
	
	public String validID() {
		int valid = 0;
		String checkingID = null;
		while(valid == 0) {
			System.out.print("\tID: ");
			checkingID = (new Scanner(System.in).next());
			valid = isValidID(checkingID);
			if(valid == 0) {
				System.out.println("\tInvalid ID Format. Must be LetterLetterDigitDigitDigitDigit");
			}
		}
		return checkingID;
	}

	public abstract void print();
	
}

class Student extends Person {
	
	private double gpa;
	private int credit_hours;
	
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public int getCredit_hours() {
		return credit_hours;
	}
	public void setCredit_hours(int credit_hours) {
		this.credit_hours = credit_hours;
	}
	
	public Student() {	}
	
	// Sets all the information for the student
	public Student(String name, String id, double GPA, int creditHours) {
		setName(name);
		setId(id);
		setGpa(GPA);
		setCredit_hours(creditHours);
	}
	
	// prints invoice for student
	@Override
	public void print() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("\n\t"+ getName()+"\t\t"+getId());
		System.out.println("\tCredit Hours: "+getCredit_hours()+"  (236.45/credit hour)");
		System.out.println("\tFees: $52");
		
		double total = invoice();
		double discount = discountTotal(total);
		double discountedTotal = total - discount;
		
		System.out.printf("\tTotal payment (after discount): $%,.2f", discountedTotal);
		System.out.printf("\t($%.2f discount applied)\n",discount);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("\n");
	}
	
	// determines total cost
	public double invoice(){
		double total = 236.45 * (getCredit_hours()) + 52;
		return total;
	}
	
	// determines the total discount
	private double discountTotal(double total) {
		double discount = 0;
		if(getGpa() >= 3.85) {
			discount = total * 25/100;
		}
		return discount;
	}
}

abstract class Employee extends Person {
	private String department;
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	// checks if the inputed department is valid
	private int isValidDepartment(String department) {
		int valid = 0;
		if(department.compareToIgnoreCase("Mathematics") == 0
				|| department.compareToIgnoreCase("Engineering") == 0 
				|| department.compareToIgnoreCase("English") == 0) {
			valid = 1;
		}
		else {
			System.out.println("\t\""+department+"\" is invalid");
		}
		return valid;
	}
	// gets a valid Department
	public String validDepartment() {
		int valid = 0;
		String department = null;
		while(valid == 0) {
			System.out.print("\tDepartment: ");
			department = (new Scanner(System.in).nextLine());
			valid = isValidDepartment(department);
		}
		return department;
	}
	
}

class Faculty extends Employee {
	private String rank;
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	// empty constructor
	public Faculty() {	}
	
	// constructor to set all information for a faculty member
	public Faculty(String name, String id, String Rank, String Department) {
		setName(name);
		setId(id);
		setRank(Rank);
		setDepartment(Department);
	}
	
	// determines if a rank is valid
	private int isValidRank(String rank) {
		int valid = 0;	
		if(rank.compareToIgnoreCase("Professor") == 0 || rank.compareToIgnoreCase("Adjunct") == 0) {
			valid = 1;
		}
		else {
			System.out.println("\t\""+rank+"\" is invalid");
		}
		return valid;
	}
	
	// gets a valid rank
	public String validRank() {
		int valid = 0;
		String rank = null;
		while(valid == 0) {
			System.out.print("\tRank: ");
			rank = (new Scanner(System.in).nextLine());
			valid = isValidRank(rank);
		}
		return rank;
	}
	
	// prints the information about a faculty member
	@Override
	public void print() {
		System.out.println("--------------------------------------------------------------------------------");
		
		System.out.println("\n"+getName()+"\t"+ getId());
		System.out.println(getDepartment() +" Department, "+ getRank());
		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("\n");
	}
}

class Staff extends Employee {
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	// Empty Constructor
	public Staff() {	}
	
	// Constructor that sets all the information about a Staff member
	public Staff(String name, String id, String Status, String Department) {
		setName(name);
		setId(id);
		setStatus(Status);
		setDepartment(Department);
	}
	
	// determines if a status is valid
	private int isValidStatus(String position) {
		int valid = 0;	
		if(position.compareToIgnoreCase("P") == 0 || position.compareToIgnoreCase("F") == 0)
			valid = 1;
		else
			System.out.println("\t\""+position+"\" is invalid");
		return valid;
	}
	
	// return a valid status
	public String validStatus() {
		int valid = 0;
		String position = null;
		while(valid == 0) {
			System.out.print("\tStatus, Enter P for Part Time, or Enter F for full Time: ");
			position = (new Scanner(System.in).nextLine());
			valid = isValidStatus(position);
		}
		return position;
	}

	// prints all the information about a staff member
	@Override
	public void print() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("\n"+getName()+"\t"+ getId());
		
		// determines if a staff member is full or part time
		String stat = null;
		if (getStatus().compareToIgnoreCase("f") == 0)
			stat = "Full Time";		
		else if (getStatus().compareToIgnoreCase("p") == 0)
			stat = "Part Time";
		
		System.out.println(getDepartment() +" Department, "+ stat);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("\n");
	}
}