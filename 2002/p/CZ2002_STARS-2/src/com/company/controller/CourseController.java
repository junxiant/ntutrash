package com.company.controller;

import com.company.model.Student;
import com.company.model.Course;
import com.company.model.CourseStatus;
import com.company.model.Time;
import com.company.service.CourseService;
import com.company.service.StudentService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class CourseController {
	// Gateway between user input and service layer, decides what to do with the
	// input and how to output the response.
	// Input validation is done here

	private StudentService studentService;
	private CourseService courseService;

	public CourseController(StudentService studentService, CourseService courseService) {
		this.studentService = studentService;
		this.courseService = courseService;
	}

	// Public method to add student to course, this is the course-side of
	// addModule()
	public void addStudent(String index, Student student) {
		// Validation 1: By right already checked in student, but just in case, check to
		// see that there are indeed classes for this index
		if (courseService.findCourseByIndex(index).size() == 0) {
			System.out.println("No classes in index or index does not exist!");
			return;
		}
		courseService.addStudent(index, student);
	}

	// Public method to drop student. This is the course-side of dropStudent()
	public void dropStudent(String index, Student student) {
		// Validation 1: By right already checked in student, but just in case, check to
		// see that there are indeed classes for this index
		if (courseService.findCourseByIndex(index).size() == 0) {
			System.out.println("No classes in index!");
			return;
		}
		courseService.dropStudent(index, student);
	}

	// Public method to drop student. This is the course-side of dropStudent()
	public void checkVacancy(String index) {
		ArrayList<Course> classes = courseService.findCourseByIndex(index);
		if (classes.size() == 0) {
			System.out.println("No classes in index!");
			return;
		}
		System.out.println(classes.get(0).getVacancy() + "/" + classes.get(0).getSize() + " [Vacancies/Total Spots]");
		System.out.println("Waitlist: " + classes.get(0).getWaitList().size() + " students.");
	}

	// Public method to change index
	public void changeIndex(Student student1, Student student2, String oldIndex, String newIndex) {
		// If solo swap
		if (student1 == student2) {
			courseService.soloSwap(student1, oldIndex, newIndex);
		}
		// If peer swap
		else {
			courseService.peerSwap(student1, student2, oldIndex, newIndex);
		}
	}

	// Public method to change attributes of index and its classes
	public void changeCourseInList() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the index number of the module you want to change:");
		String index = sc.next();
		ArrayList<Course> classes = courseService.findCourseByIndex(index);
		System.out.println("Do you want to change all classes in this index or a particular class in this index?");
		System.out.print("Enter 1 to change all classes and 2 to change a particular class:");
		String response = sc.next();
		int responseInt;
		// Validation 1: Check if response is 1 or 2
		while (true) {
			try {
				responseInt = Integer.parseInt(response);
				if (!(responseInt == 1 || responseInt == 2)) {
					throw new Exception();
				}
				break;
			} catch (Exception e) {
				System.out.println("ERROR: Invalid response! Please enter either 1 or 2:");
				response = sc.next();
			}
		}
		sc.nextLine();
		// Change all classes in index
		// Can change School, Code, Index, Class Size, AU
		if (responseInt == 1) {
			Course sampleClass = classes.get(0);
			System.out.println("Current attributes are:");
			System.out
					.println(" _________________________________________\n| School | Code | Index | Class Size | AU |");
			System.out.println(" | " + sampleClass.getSchool() + " | " + sampleClass.getCode() + " | "
					+ sampleClass.getIndex() + " | " + sampleClass.getSize() + " | " + sampleClass.getAu() + " | ");
			System.out.println(
					"Please enter the fields above with the new values to be changed, with each field separated by a comma");
			String line = sc.nextLine();

			StringTokenizer star = new StringTokenizer(line, ",");
			String newSchool = star.nextToken().trim();
			String newCode = star.nextToken().trim();
			String newIndex = star.nextToken().trim();
			String newSize = star.nextToken().trim();
			String newAu = star.nextToken().trim();

			int sizeInt;
			int auInt;

			// Validation 2: Check if index is unique
			while (true) {
				// If the new index isnt the old index and theres some class with that index

				if (!newIndex.equals(index) && courseService.findCourseByIndex(newIndex).size() > 0) {
					System.out.println("ERROR: There is already a module in database with the same index.");
					System.out.println("Re-enter index field:");
					newIndex = sc.next();
				} else {
					break;
				}
			}
			// Validation 3: Check if size is an integer
			while (true) {
				try {
					sizeInt = Integer.parseInt(newSize);
					break;
				} catch (Exception e) {
					System.out.println("ERROR: Invalid 'Size' field! Please enter integers for it.");
					System.out.println("Re-enter Size field:");
					newSize = sc.next();
				}
			}
			// Validation 4: Check if AU is an integer
			while (true) {
				try {
					auInt = Integer.parseInt(newAu);
					break;
				} catch (Exception e) {
					System.out.println("ERROR: Invalid 'AU' field! Please enter integers for it.");
					System.out.println("Re-enter AU field:");
					newAu = sc.next();
				}
			}
			// Change the attributes
			for (Course i : classes) {
				i.setSchool(newSchool);
				i.setCode(newCode);
				i.setIndex(newIndex);
				i.setSize(sizeInt);
				i.setAu(auInt);
			}
			System.out.println("Attributes changed!");
		}
		// Change specific class in index
		// Can change Time, Class Type, Day, Venue, Group
		else if (responseInt == 2) {
			System.out.println(
					"Enter these existing fields for the class you want to change.\nClass Type, Day, Venue, Group");
			String line = sc.nextLine();
			StringTokenizer star = new StringTokenizer(line, ",");

			String oldType = star.nextToken().trim();
			String oldDay = star.nextToken().trim();
			String oldVenue = star.nextToken().trim();
			String oldGroup = star.nextToken().trim();
			boolean flag = false;

			for (Course i : classes) {
				if (i.getDay().equals(oldDay) && i.getGroup().equals(oldGroup) && i.getVenue().equals(oldVenue)
						&& i.getClassType().equals(oldType)) {
					flag = true;
					System.out.println(
							"Enter the new values for the following fields for the class you want to change.\nTime,Class Type, Day, Venue, Group");

					String newLine = sc.nextLine();
					System.out.println(newLine);
					// sc.nextLine();
					StringTokenizer newStar = new StringTokenizer(newLine, ",");
					String newTimeString = newStar.nextToken().trim();
					String newType = newStar.nextToken().trim();
					String newDay = newStar.nextToken().trim();
					String newVenue = newStar.nextToken().trim();
					String newGroup = newStar.nextToken().trim();
					Set<String> VALIDDAYS = Set.of("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");

					// Validation 5: Check if days is actual day
					while (true) {
						if (!VALIDDAYS.contains(newDay)) {
							System.out.println(
									"ERROR: Invalid 'Day' field! Please enter MON/TUE/WED/THU/FRI/SAT/SUN only.");
							System.out.println("Re-enter Day field:");
							newDay = sc.next();
						} else {
							break;
						}
					}
					// Validation 4: Check if time is legit time
					Time time = new Time();
					while (true) {

						try {
							time.setStartHour(Integer.parseInt(newTimeString.substring(0, 2)));
							time.setEndHour(Integer.parseInt(newTimeString.substring(5, 7)));
							time.setStartMinute(Integer.parseInt(newTimeString.substring(2, 4)));
							time.setEndMinute(Integer.parseInt(newTimeString.substring(7, 9)));
							break;
						} catch (Exception e) {
							System.out.println("ERROR: Invalid 'Time' field! Please enter in HHMM-HHMM format!");
							newTimeString = sc.next();
						}
					}

					i.setDay(newDay);
					i.setTime(time);
					i.setClassType(newType);
					i.setVenue(newVenue);
					i.setGroup(newGroup);
				}
			}
			if (flag == false) {
				System.out.println("ERROR: Cannot find a class that matches the details entered!");
			}

		}

	}

	// Public method to add new index and its classes into database
	public void addCourseIntoList() {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Please enter the following details about this module, with each field separated by a comma\nSchool,Code,Index,Class Size,AU");
		String line = sc.nextLine();

		StringTokenizer star = new StringTokenizer(line, ",");
		String school = star.nextToken().trim();
		String code = star.nextToken().trim();
		String index = star.nextToken().trim();
		String size = star.nextToken().trim();
		String au = star.nextToken().trim();

		int sizeInt;
		int auInt;
		Set<String> VALIDDAYS = Set.of("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");
		// Validation 1: Check if index is unique
		while (true) {
			if (!courseService.findCourseByIndex(index).isEmpty()) {
				System.out.println("ERROR: There is already a module in database with the same index.");
				System.out.println("Re-enter index field:");
				index = sc.next();
			} else {
				break;
			}
		}
		// Validation 2: Check if size is an integer
		while (true) {
			try {
				sizeInt = Integer.parseInt(size);
				break;
			} catch (Exception e) {
				System.out.println("ERROR: Invalid 'Size' field! Please enter integers for it.");
				System.out.println("Re-enter Size field:");
				size = sc.next();
			}
		}
		// Validation 3: Check if AU is an integer
		while (true) {
			try {
				auInt = Integer.parseInt(au);
				break;
			} catch (Exception e) {
				System.out.println("ERROR: Invalid 'AU' field! Please enter integers for it.");
				System.out.println("Re-enter AU field:");
				au = sc.next();
			}
		}

		System.out.println("How many classes are in this module for this index?");
		// Validation 4: Check if numClasses is an integer
		int numClasses;
		while (true) {
			String input = sc.next();
			try {
				numClasses = Integer.parseInt(input);
				break;
			} catch (Exception e) {
				System.out.println("Please enter an integer");
			}
		}

		System.out.println(
				"Please enter the following details for each class with each field separated by a comma.\nTime, Class Type, Day, Venue, Group");
		sc.nextLine();
		for (int i = 1; i <= numClasses; i++) {
			System.out.println("Class :" + (i));
			String classLine = sc.nextLine();

			StringTokenizer classStar = new StringTokenizer(classLine, ",");
			String timeString = classStar.nextToken().trim();
			String classType = classStar.nextToken().trim();
			String day = classStar.nextToken().trim();
			String venue = classStar.nextToken().trim();
			String group = classStar.nextToken().trim();
			// sc.nextLine();
			// Validation 5: Check if days is actual day
			while (true) {
				if (!VALIDDAYS.contains(day)) {
					System.out.println("ERROR: Invalid 'Day' field! Please enter MON/TUE/WED/THU/FRI/SAT/SUN only.");
					System.out.println("Re-enter Day field:");
					day = sc.next();
				} else {
					break;
				}
			}
			// Validation 4: Check if time is legit time
			Time time = new Time();
			while (true) {

				try {
					time.setStartHour(Integer.parseInt(timeString.substring(0, 2)));
					time.setEndHour(Integer.parseInt(timeString.substring(5, 7)));
					time.setStartMinute(Integer.parseInt(timeString.substring(2, 4)));
					time.setEndMinute(Integer.parseInt(timeString.substring(7, 9)));
					break;
				} catch (Exception e) {
					System.out.println("ERROR: Invalid 'Time' field! Please enter in HHMM-HHMM format!");
					timeString = sc.next();
				}
			}
			Course course = new Course(school, code, index, time, classType, day, venue, group, sizeInt, auInt);
			if (courseService.addCourseIntoList(course)) {
				System.out.println("Added " + course.getCode() + " " + course.getClassType() + " into database!");
			} else {
				System.out
						.println("Failed to add " + course.getCode() + " " + course.getClassType() + " into database!");
			}
		}

	}
	
	// Public method to print course
	public void printCourse(Course course) {
		Time time = course.getTime();
		System.out.println("School, Code, Index, Time, Class Type, Day, Venue, Group, Size, AU");
		System.out.print(course.getSchool() + ", " + course.getCode() + ", " + course.getIndex() + ", ");
		System.out.print(
				time.getStartHour() + "" + time.getStartMinute() + "-" + time.getEndHour() + time.getEndMinute());

		System.out.print(", " + course.getClassType() + ", " + course.getDay() + ", " + course.getVenue() + ", "
				+ course.getGroup() + ", " + course.getSize() + ", " + course.getAu() + "\n");
	}
	
	// Public method to print students by index
	public void printStudentsByIndex(String index) {
		ArrayList<Course> classes = courseService.findCourseByIndex(index);
		// Validation 1: Ensure classes for this index actually exist
		if (classes.size() == 0) {
			System.out.println("No such index!");
			return;
		}
		
		Course course = classes.get(0);
		ArrayList<Student> registeredList = course.getRegisteredList();
		ArrayList<Student> waitList = course.getWaitList();
		
		
		
		// If no one is registered
		if (registeredList.size()==0) {
			System.out.println("No students have registered for this index");
		}
		
		// If someone is registered
		if (registeredList.size()>0) {
			System.out.println("Registered Students\n ____________________________________________________\n" + "| Name | Gender | Nationality | Matriculation Number |");
			for (Student student : registeredList) {
				String name = student.getName();
				String gender = student.getGender();
				String nationality = student.getNationality();
				String matric = student.getMatricNumber();
				System.out.println(name + " | " + gender + " | " + nationality + " | " + matric);
			}
		}

		// If there is a waiting list
		if (waitList.size()>0) {
			System.out.println("Waitlist Students\n ____________________________________________________\n" + "| Name | Gender | Nationality | Matriculation Number |");
			for (Student student : waitList) {
				String name = student.getName();
				String gender = student.getGender();
				String nationality = student.getNationality();
				String matric = student.getMatricNumber();
				System.out.println(name + " | " + gender + " | " + nationality + " | " + matric);
			}
		
		}

	}

	public void printStudentsByCode(String code) {
		// Look up all the indexes for this course code
		ArrayList<String> indexes = courseService.findIndexesByCode(code);
		// For each index, we print the students
		for (int i = 0; i < indexes.size(); i++) {
			System.out.println(" ================================================= ");
			String index = indexes.get(i);
			System.out.println(code + " Index " + index + ":");
			printStudentsByIndex(index);
			
		}
	}

	public void updateCourseDAT() {
		courseService.updateCourseDAT();
	}
}
