package com.company;

import com.company.controller.CourseController;
import com.company.controller.PermissionController;
import com.company.controller.StudentController;
import com.company.controller.AccessPeriodController;
import com.company.model.AccessPeriod;
import com.company.model.CourseStatus;
import com.company.model.Professor;
import com.company.model.Student;

import com.company.repository.impl.AccessPeriodRepositoryImpl;
import com.company.repository.impl.CourseRepositoryImpl;
import com.company.repository.impl.ProfessorRepositoryImpl;
import com.company.repository.impl.StudentRepositoryImpl;
import com.company.service.AccessPeriodService;
import com.company.service.impl.AccessPeriodServiceImpl;
import com.company.service.impl.CourseServiceImpl;

import com.company.service.impl.ProfServiceImpl;
import com.company.service.impl.StudentServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		/* For each of the main components (students/professors/courses/access), we have the following
		 * Model level - The data models to store in the database files
		 * Repository level - methods that contains the code to read/write from the database
		 * Service level -  encapsulates application logic/API. Services should be the only ones with access to the repositories
		 * Controller object - gateway between your input and the domain logic, is decides what to do with the input and how to output the response.
		 * 		  
		 * This is called a model - repository - service - controller architecture
		 */
		StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
		ProfessorRepositoryImpl profRepository = new ProfessorRepositoryImpl();
		CourseRepositoryImpl courseRepository = new CourseRepositoryImpl();
		AccessPeriodRepositoryImpl accessPeriodRepository = new AccessPeriodRepositoryImpl();

		StudentServiceImpl studentService = new StudentServiceImpl(studentRepository);
		ProfServiceImpl profService = new ProfServiceImpl(profRepository);
		CourseServiceImpl courseService = new CourseServiceImpl(courseRepository,studentService);
		AccessPeriodServiceImpl accessPeriodService = new AccessPeriodServiceImpl(accessPeriodRepository);

		AccessPeriodController accessPeriodController = new AccessPeriodController(accessPeriodService);
		PermissionController permissionController = new PermissionController(studentService, profService);
		CourseController courseController = new CourseController(studentService, courseService);
		StudentController studentController = new StudentController(studentService, courseService);

		Scanner sc = new Scanner(System.in);
		Object user = null;
		do {
			// ASCII art that says STARS
			System.out.println("   _____ _______       _____   _____ \n" + "  / ____|__   __|/\\   |  __ \\ / ____|\n"
					+ " | (___    | |  /  \\  | |__) | (___  \n" + "  \\___ \\   | | / /\\ \\ |  _  / \\___ \\ \n"
					+ "  ____) |  | |/ ____ \\| | \\ \\ ____) |\n" + " |_____/   |_/_/    \\_\\_|  \\_\\_____/");

			// Error prevention for options, allows 1 or 2 as input only
			System.out.print("Enter 1 for Admin and 2 for Student\n" + "Option: ");
			String optionRaw;
			while (true) {
				optionRaw = sc.next();
				if (optionRaw.charAt(0) >= 49 && optionRaw.charAt(0) <= 50 && optionRaw.length() == 1) {
					break;
				}
				System.out.println("Invalid option, please enter a valid option:");
			}
			Integer option = Integer.parseInt(optionRaw);

			// If option 2, which is student, use the accessPeriodController to check
			// whether the current day is within the allowed period
			if (option == 2) {
				//AccessPeriod accessPeriod = accessPeriodController.getAccessPeriod();
				// the checkAccessPeriod function returns -1 for current day being before the
				// start of access period
				if (accessPeriodController.checkAccessPeriod() == -1) {
					System.out.println("Registration period has not started yet!");
					accessPeriodController.printAccessPeriod();
					break;
					// And returns -2 for current day being after the end of the access period
				} else if (accessPeriodController.checkAccessPeriod() == -2) {
					System.out.println("Registration period is over!");
					accessPeriodController.printAccessPeriod();
					break;
				}
				// and returns 1 for within the access period
			}
			// We create an object called user, which is upcasted from the primitive
			// 'Object' type to either Student or Professor
			user = permissionController.authenticateUser(option);
			// We continue looping while there is no user
		} while (user == null);

		// Initialize some boilerplate code for choice error prevention
		String rawChoice;
		int choice = -1;
		if (user instanceof Student) {
			// If the user object is of type Student, then we explicitly cast the user
			// object to be a Student
			Student student = (Student) user;

			do {
				System.out.print("================================================\n"
						+ "== Choose an option:                          ==\n"
						+ "== (1) Add Course                             ==\n"
						+ "== (2) Drop Course                            ==\n"
						+ "== (3) Print Courses Registered               ==\n"
						+ "== (4) Check Vacancies Available              ==\n"
						+ "== (5) Change Index Number of Course          ==\n"
						+ "== (6) Swap Index Number with Another Student ==\n"
						+ "== (7) Exit and Save                          ==\n"
						+ "================================================\n");
				System.out.print("Enter the number of your choice:");

				// Error prevention for choice, can only choose a number between 1 to 7
				while (true) {
					rawChoice = sc.next();
					if (rawChoice.charAt(0) >= 49 && rawChoice.charAt(0) <= 55 && rawChoice.length() == 1) {
						break;
					}
					System.out.println("Invalid option, please enter a valid option from 1 to 7:");
				}
				choice = Integer.parseInt(rawChoice);

				// Switch board for the choice
				switch (choice) {
				case 1:
					// Add module using the index number of the module
					System.out.print("Index number:");
					String index = sc.next();
					// We first add it student-side, which involves checking for clashes/existing
					// modules and vacancies
					boolean success = studentController.addCourse(index, student);
					// If it's successfully added, we add it course-side
					if (success) {
						courseController.addStudent(index, student);
					} else {
						System.out.println("Add index failed.");
					}
					break;
				case 2:
					// Drop module using the index number of the module
					System.out.print("Index number:");
					index = sc.next();
					// We first drop it student-side, which involves resetting the number of AUs,
					// removal of the index from timetable, etc
					success = studentController.dropCourse(index, student);
					// If it's successfully removed, we remove it course-side
					if (success) {
						courseController.dropStudent(index, student);
					} else {
						System.out.println("Drop index failed.");
					}
					break;
				case 3:
					// Print the currently registered/wait-listed modules for the student
					studentController.printCourses(student);
					break;
				case 4:
					// Check for vacancy using the index number of the module
					System.out.print("Index number:");
					index = sc.next();
					courseController.checkVacancy(index);
					break;
				case 5:
					// Swap index numbers from an existing registered/wait-listed module to another
					// index of the same module
					System.out.print("Old index number:");
					String oldIndex = sc.next();
					System.out.print("");
					System.out.print("New index number:");
					String newIndex = sc.next();
					// We first swap it student-side, which involves checking for clash for the new
					// timings and seeing if either index will be wait-listed
					success = studentController.changeIndex(student, student, oldIndex, newIndex);
					// If it's successfully swapped, we swap it course-side
					if (success) {
						courseController.changeIndex(student, student, oldIndex, newIndex);
						System.out.println("Index number " + oldIndex + " has been changed to " + newIndex);
					} else {
						System.out.println("Indexes swap failed.");
					}
					break;
				case 6:
					// Swap index numbers with a peer. Just like the actual STARS, the peer needs to
					// log in as well
					System.out.print("Please let your peer log in:\n");
					// We create a peer 'Student' object and authenticate it
					Student peer = (Student) permissionController.authenticateUser(2);
					if (peer.getMatricNumber() == student.getMatricNumber()) {
						System.out.print("ERROR: Peer is the same user as current user.");
						break;
					}
					System.out.print("Original user's index number to swap: ");
					String myIndex = sc.next();
					System.out.print("Peer's index number to swap: ");
					String peerIndex = sc.next();
					// We first swap it student-side, which involves checking for clash for the new
					// timings for both students and seeing if either index will be wait-listed
					success = studentController.changeIndex(student, peer, myIndex, peerIndex);
					if (success) {
						courseController.changeIndex(student, peer, myIndex, peerIndex);
						System.out.println("Indexes swapped!");
					} else {
						System.out.println("Indexes swap failed.");
					}
					break;
				case 7:
					// When exiting, we save all current data from the student and course objects
					// into binary .DAT files
					studentController.updateStudentDAT();
					courseController.updateCourseDAT();
					System.out.println("Changes update successfully");
					break;
				default:
					break;
				}
			} while (choice < 7);
		} else if (user instanceof Professor) {
			// Professor professor = (Professor) user;
			do {
				System.out.print("===============================================\n"
						+ "== (1) Edit student access period            ==\n"
						+ "== (2) Add Student                           ==\n"
						+ "== (3) Add Module                            ==\n"
						+ "== (4) Update Module                         ==\n"
						+ "== (5) Check Vacancies Available             ==\n"
						+ "== (6) Print student list by index number    ==\n"
						+ "== (7) Print student list by course          ==\n"
						+ "== (8) Exit and Save                         ==\n"
						+ "===============================================\n");
				System.out.print("Enter the number of your choice:");
				// Error prevention for choice, can only choose a number between 1 to 8
				while (true) {
					rawChoice = sc.next();
					if (rawChoice.charAt(0) >= 49 && rawChoice.charAt(0) <= 56 && rawChoice.length() == 1) {
						break;
					}
					System.out.println("Invalid option, please enter a valid option from 1 to 8:");
				}
				choice = Integer.parseInt(rawChoice);

				// Switch board for the choice
				switch (choice) {
				case 1:
					// Edit the student access period
					LocalDate today = accessPeriodController.getCurrentDate();
					// Show what today is in proper format
					System.out.println("Today is " + today);
					// Show the current access period
					accessPeriodController.printAccessPeriod();
					// Set a new access period
					accessPeriodController.setAccessPeriod();
					break;
				case 2:
					// Add student into the database
					studentController.addStudentIntolist();
					break;
				case 3:
					// Add course into the database
					courseController.addCourseIntoList();
					break;
				case 4:
					// Update course in database, specify the index and class type
//					System.out.print("Index number:");
//					String index = sc.next();
//					System.out.print("Class Type:");
//					String classType = sc.next();
					courseController.changeCourseInList();
					break;
				case 5:
					// Check vacancies by index number
					System.out.print("Index number:");
					String index = sc.next();
					courseController.checkVacancy(index);
					break;
				case 6:
					// Print student list by index number
					System.out.print("Index number:");
					index = sc.next();
					courseController.printStudentsByIndex(index);
					break;
				case 7:
					// Print student list by course code
					System.out.print("Code number:");
					String code = sc.next();
					courseController.printStudentsByCode(code);
					break;
				case 8:
					// Save everything once exiting
					studentController.updateStudentDAT();
					courseController.updateCourseDAT();
					System.out.println("Changes update successfully");
					break;
				default:
					break;
				}
			} while (choice < 8);
		}
	}
}
