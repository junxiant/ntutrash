package com.company.controller;

import com.company.model.Course;
import com.company.model.CourseStatus;
import com.company.model.Student;
import com.company.service.CourseService;
import com.company.service.StudentService;
import com.company.service.impl.CourseServiceImpl;
import com.company.utils.PasswordUtil;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudentController {
	// Gateway between user input and service layer, decides what to do with the
	// input and how to output the response.
	// Input validation is done here

	private StudentService studentService;
	private CourseService courseService;

	public StudentController(StudentService studentService, CourseService courseService) {
		this.studentService = studentService;
		this.courseService = courseService;
	}

	// Public method to add module to timetable for student, only for student-side.
	// Course-side will be done after this function is executed successfully.
	public boolean addCourse(String index, Student student) {

		// We first get the classes that this new module has
		ArrayList<Course> classes = courseService.findCourseByIndex(index);

		// Validation 1: Checks if such an index exists, which is checking if there are
		// classes with such an index
		if (classes.size() == 0) {
			System.out.println("No such index exists.");
			return false;
		}
		// Validation 2: Checks if the student already has the module
		// registered/wait-listed
		if (!checkCourse(student, classes.get(0).getCode())) {
			System.out.println("Course already registered!");
			return false;
		}
		// Validation 3: Checks if the student's current timetable will clash with the
		// new index's classes
		if (!checkTimeClash(classes.get(0).getIndex(), student)) {
			return false;
		}
		// Validation 4: Checks if adding the new module will cause the student to go
		// over allowed AUs load
		if (!checkAU(classes.get(0).getAu(), student)) {
			System.out.println("Unable to add module as you will go over your Normal AU Load!");
			return false;
		}

		// Validation 5: Checks the vacancies of the new module
		int vacancy = classes.get(0).getVacancy();
		if (vacancy < 1) {
			Scanner sc = new Scanner(System.in);
			System.out.println("There are no vacancies for this module!\nWould you like to be added to the waitlist?");
			String rawResponse;
			int response;
			while (true) {
				rawResponse = sc.next();
				if (rawResponse.charAt(0) >= 49 && rawResponse.charAt(0) <= 50 && rawResponse.length() == 1) {
					break;
				}
				System.out.println("Invalid option, please enter 1 for Yes and 0 for No:");
			}
			response = Integer.parseInt(rawResponse);
			if (response == 0) {
				return false;
			}
		}

		// Add the module into the student's timetable
		// As this is not input validation, we defer this function to the service layer
		studentService.addCourseIntoTimeTable(vacancy, student, classes.get(0));

		return true;
	}

	// Public method to drop module to timetable for student, only for student-side.
	// Course-side will be done after this function is executed successfully.
	public boolean dropCourse(String index, Student student) {

		ArrayList<Course> classes = courseService.findCourseByIndex(index);

		// Validation 1: Check if input index is even inside student's timetable
		if (!checkIndex(index, student)) {
			System.out.println("Index not found in timetable");
			return false;
		}

		// Drop the module from the student's timetable
		// As this is not input validation, we defer this function to the service layer
		studentService.dropCourseFromTimeTable(student, classes.get(0));

		return true;
	}

	// Public utility method to print out the timetable
	public void printCourses(Student student) {
		// Get the timetable
		ArrayList<CourseStatus> timeTable = student.getTimetable();
		// If empty timetable
		if (timeTable.size() == 0) {
			System.out.println("No course in time table!");
			return;
		}
		// Timetable template print
		System.out.println(" _______________________________________________________________________\n"
				+ "|  Code  | Index | AUs | Class Type |     Time     |  Day  |   Status   |\n"
				+ " =======================================================================");

		for (int i = 0; i < timeTable.size(); i++) {
			String index = timeTable.get(i).getIndex();
			String status = timeTable.get(i).getStatus();
			ArrayList<Course> classes = courseService.findCourseByIndex(index);
			for (Course course : classes) {
				String startTime = course.getTime().getStartHour() + "" + course.getTime().getStartMinute();
				String endTime = course.getTime().getEndHour() + "" + course.getTime().getEndMinute();
				System.out.println("| " + course.getCode() + " | " + index + " |  " + course.getAu() + "  |     "
						+ course.getClassType() + "    | " + startTime + " to " + endTime + " |  " + course.getDay()
						+ "  | " + status + " |");
			}

		}
	}

	// Public method to change/swap index, handles both swapping between two
	// students or just a solo swap with an unregistered index, only for
	// student-side
	// Course-side will be done after this function is executed successfully.
	public boolean changeIndex(Student student1, Student student2, String oldIndex, String newIndex) {
		ArrayList<Course> classes1 = courseService.findCourseByIndex(oldIndex);
		ArrayList<Course> classes2 = courseService.findCourseByIndex(newIndex);

		// Validation 1: Check if the indexes exist
		if (classes1.size() == 0) {
			System.out.println("Index " + oldIndex + " does not exist.");
			return false;
		}
		if (classes2.size() == 0) {
			System.out.println("Index " + newIndex + " does not exist.");
			return false;
		}
		// Validation 2: Check if the two indexes are for the same module
		if (!classes1.get(0).getCode().equals(classes2.get(0).getCode())) {
			System.out.print("Not the same course for the two indexes.");
			return false;
		}
		// Validation 3: Check if the original user has the index to be swapped from
		// inside his timetable
		if (!checkIndex(oldIndex, student1)) {
			System.out.println("Index to be changed from is not found in time table of " + student1.getName());
			return false;
		}
		Course course1 = classes1.get(0);
		Course course2 = classes2.get(0);
		// Validation 4: Check if index to be swapped to by original user is clashing
		if (!checkTimeClash(course2.getIndex(), student1)) {
			System.out.println("Index to be changed is clashing with other registered classes");
			return false;
		}
		// Validation 5 and 6 are for peer swap. We assume no need to check waitlist
		// since peers would have discussed beforehand
		if (student1 != student2) {
			// Validation 5: If it's a peer swap, check if index to be swapped to is inside
			// peer timetable
			if (!checkIndex(newIndex, student2)) {
				System.out.print("Index to be changed to not found in time table of " + student2.getName());
				return false;
			}
			// Validation 6: If it's a peer swap, check if index to be swapped to is clashes
			// with peer timetable
			if (!checkTimeClash(course1.getIndex(), student2)) {
				System.out.println("Index to be changed is clashing with other classes for peer");
				return false;
			}
		}
		// Validation 7: If it's a solo swap, check vacancy for the index to be swapped
		// to and ask user to confirm entering waitlist if needed
		if (classes2.get(0).getVacancy() < 1 && student1 == student2) {
			System.out.print("Index to be changed to does not have any vacancies\n");
			System.out.println("Would you like to be added to the waitlist?\nEnter 1 for Yes and 2 for No");
			Scanner sc = new Scanner(System.in);
			int result = sc.nextInt();
			if (result == 2) {
				return false;
			}
		}

		// Solo swap API call to service layer since input validation is all done
		if (student1 == student2) {
			return studentService.soloSwap(student1, course1, course2, oldIndex, newIndex);
		}

		// Peer swap API call to service layer since input validation is all done
		else {
			return studentService.peerSwap(student1, student2, oldIndex, newIndex);
		}

	}

	// Private utility function to check whether index is in student's timetable
	private boolean checkIndex(String index, Student student) {
		ArrayList<CourseStatus> timeTable = student.getTimetable();

		for (int i = 0; i < timeTable.size(); i++) {

			if (index.equals(timeTable.get(i).getIndex())) {

				return true;
			}
		}
		return false;
	}

	// Private utility function to check whether course is in student's timetable
	private boolean checkCourse(Student student, String code) {
		ArrayList<CourseStatus> timeTable = student.getTimetable();
		for (int i = 0; i < timeTable.size(); i++) {
			if (code.equals(courseService.findCourseByIndex(timeTable.get(i).getIndex()).get(0).getCode())) {
				return false;
			}

		}
		return true;
	}

	// Private utility function to check whether adding more AUs would lead to going
	// over allowed load, which is set to 21
	private boolean checkAU(int au, Student student) {
		int max = 21;
		int total_au = student.getTotalAu() + au;
		if (total_au > max) {
			return false;
		}
		return true;
	}

	// Private utility function to check whether module to be added clashes with
	// student's timetable
	private boolean checkTimeClash(String index, Student student) {
		// Get the current timetable for the student
		ArrayList<CourseStatus> timeTable = student.getTimetable();

		// We create a temporary new Course object with the index provided
		ArrayList<Course> newClasses = courseService.findCourseByIndex(index);

		// We iterate through each of the classes inside the newClasses ArrayList to
		// check for clashes with current timetable
		for (Course newCourse : newClasses) {

			// We get the day of the new course
			String newDay = newCourse.getDay();

			// We get the start and end hours/minutes of the course
			int newStartHour = newCourse.getTime().getStartHour();
			int newEndHour = newCourse.getTime().getEndHour();
			int newStartMinute = newCourse.getTime().getStartMinute();
			int newEndMinute = newCourse.getTime().getEndMinute();
			int newStart = newStartHour * 60 + newStartMinute;
			int newEnd = newEndHour * 60 + newEndMinute;

			// Iterate through each of the modules currently in the timetable
			for (int i = 0; i < timeTable.size(); i++) {

				// Create an ArrayList of classes for the i-th module in timetable
				ArrayList<Course> oldClasses = courseService.findCourseByIndex(timeTable.get(i).getIndex());

				// For each of the classes in the i-th module in timetable
				for (Course oldCourse : oldClasses) {

					// Error Prevention 1: If current checking class is the same code as the new
					// class to be added, continue to next class
					// This only happens when we do a solo swap. Adding duplicate class is prevented
					// by other validation checks.
					if (oldCourse.getCode().equals(newCourse.getCode())) {
						continue;
					}
					// Error Prevention 2: If the current checking class is the same index as the
					// new class to be added, continue to next class
					// This will only happen when we are swapping indexes. Adding duplicate class is
					// prevented by other check functions.
					if (oldCourse.getIndex().equals(newCourse.getIndex())) {
						continue;
					}
					// Error Prevention 3: If the current checking class is not the same day as the
					// new course to be added, continue to next class
					if (!oldCourse.getDay().equals(newDay)) {
						continue;
					}

					// Get the start and end hour
					int oldStartHour = oldCourse.getTime().getStartHour();
					int oldEndHour = oldCourse.getTime().getEndHour();
					int oldStartMinute = oldCourse.getTime().getStartMinute();
					int oldEndMinute = oldCourse.getTime().getEndMinute();
					int oldStart = oldStartHour * 60 + oldStartMinute;
					int oldEnd = oldEndHour * 60 + oldEndMinute;

					// Clash Scenario 1: If new == old or new is surrounded by old
					if (newStart >= oldStart && newEnd <= oldEnd) {
						System.out.println(
								"Index to be added clashes with " + oldCourse.getCode() + " " + oldCourse.getClassType()
										+ "!\nNew index's timetable slot is exactly or within the existing slot.");
						return false;
					}
					// Clash Scenario 2: If old is surrounded by new
					if (newStart < oldStart && newEnd > oldEnd) {
						System.out.println(
								"Index to be added clashes with " + oldCourse.getCode() + " " + oldCourse.getClassType()
										+ "!\nExisting timetable slot is within the new index's slot.");
						return false;
					}
					// Clash Scenario 3: If new start before old ends
					if (newStart < oldEnd && newEnd >= oldEnd && oldStart < newStart) {
						System.out.println(
								"Index to be added clashes with " + oldCourse.getCode() + " " + oldCourse.getClassType()
										+ "!\nNew index's timetable slot starts before the existing slot ends.");
						return false;
					}
					// Clash Scenario 4: If new start before old ends
					if (newEnd < oldEnd && newStart <= oldStart && newEnd > oldStart) {
						System.out.println(
								"Index to be added clashes with " + oldCourse.getCode() + " " + oldCourse.getClassType()
										+ "!\nExisting timetable slot starts before the new index's slot ends.");
						return false;
					}
				}
			}
		}
		return true;
	}
	
	// Public method to add student to database
	public void addStudentIntolist() {
		System.out.println(
				"Please enter the following in a single line, separated by commas for each field:\nName,Gender (M/F),Matriculation Number,Nationality,Password");

		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();

		StringTokenizer star = new StringTokenizer(line, ",");
		String name = star.nextToken().trim();
		String gender = star.nextToken().trim();
		String matricNumber = star.nextToken().trim();
		String nationality = star.nextToken().trim();
		String password = star.nextToken().trim();
		
		// The only fields we can check are gender and matriculation number, as the others can be arbitrary 
		// Validation 1: Check gender field to ensure entry is only M or F
		while (!(gender.equals("M") || gender.equals("F"))) {
			System.out.println("Invalid gender field, please input gender again:");
			gender = sc.next();
		}
		// Validation 2: Check Matriculation Number field to ensure number starts with a U and is 9 letters long
		while (matricNumber.charAt(0) != 'U' || matricNumber.length() != 9) {
			System.out.println("Invalid matriculation number field, please input matriculation number again:");
			matricNumber = sc.next();
		}
		// Validation 3: Check if matriculation number for new student belongs to an existing student, since only matriculation number is unique amongst all fields
		while (studentService.findStudentByMatric(matricNumber)!= null ) {
			System.out.println("Existing student has same matriculation number as input, please input matriculation number again:");
			matricNumber = sc.next();
		}
		// Encrypt the password
		password = PasswordUtil.caesarCipherEncrypt(password);
		
		// API call to the service layer since input processing and validation is all done
		ArrayList<Student> allStudents = studentService.addStudentIntolist(name, gender, matricNumber, nationality, password);
		
		// Print out all students in the list as required by assignment task sheet
		System.out.println("Student has been added to the system!\n\n");
		System.out
				.println("           Updated Student List\n" + "=====================================================\n"
						+ "|   Name   |Gender| Matriculation No. | Nationality | \n"
						+ "=====================================================\n");
		for (Student i : allStudents) {

			System.out.println("|   " + i.getName() + "   |  " + i.getGender() + "  |     " + i.getMatricNumber()
					+ "     |  " + i.getNationality() + "  |\n");
		}
	}
	
	// Method to be called when exiting, to save all data for students
	public void updateStudentDAT() {
		studentService.updateStudentDAT();
	}
}
