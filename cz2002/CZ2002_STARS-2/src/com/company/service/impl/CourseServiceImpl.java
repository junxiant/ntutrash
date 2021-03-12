package com.company.service.impl;

import com.company.model.Course;
import com.company.model.CourseStatus;
import com.company.model.Student;
import com.company.repository.CourseRepository;
import com.company.service.CourseService;
import com.company.service.StudentService;

import java.util.ArrayList;

public class CourseServiceImpl implements CourseService {
	private CourseRepository courseRepository;
	private StudentService studentService;

	public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService) {
		this.studentService = studentService;
		this.courseRepository = courseRepository;
	}

	@Override
	public ArrayList<Course> findCourseByIndex(String Index) {
		return courseRepository.findCourseByIndex(Index);
	}
	
	@Override
	public boolean addCourseIntoList(Course course) {
		return courseRepository.addCourseIntoList(course);
	}

	@Override
	public void changeCourseInList(String classtype, String index, String line) {
		courseRepository.changeCourseInList(classtype, index, line);
	}

	@Override
	public ArrayList<String> findIndexesByCode(String code) {
		return courseRepository.findIndexesByCode(code);
	}

	@Override
	public void updateCourseDAT() {
		courseRepository.updateCourseDAT();
	}

	@Override
	public void addStudent(String index, Student student) {
		ArrayList<Course> classes = findCourseByIndex(index);
		// For each class in the module
		for (Course course : classes) {
			// We get the vacancy
			int vacancy = course.getVacancy();
			// We get the registered and wait list for that class
			ArrayList<Student> registeredList = course.getRegisteredList();
			ArrayList<Student> waitList = course.getWaitList();
			// If there's vacancies, we add the student, and decrement vacancy by 1
			if (vacancy > 0) {
				registeredList.add(student);
				vacancy -= 1;
				course.setVacancy(vacancy);
			} else {
				// Else, we add student to waitlist
				waitList.add(student);
			}
		}
	}

	@Override
	public void dropStudent(String index, Student student) {
		ArrayList<Course> classes = findCourseByIndex(index);
		// For each class in the module
		for (Course course : classes) {
			// Get the registered and waitlist
			ArrayList<Student> registeredList = course.getRegisteredList();
			ArrayList<Student> waitList = course.getWaitList();
			// For each student in registered list
			for (int i = 0; i < registeredList.size(); i++) {
				// We check if the i-th student has the same matric number as the student we
				// want to remove
				// We need to do this instead of indexOf() as indexOf() fails when we reload the
				// data from file
				if (student.getMatricNumber().equals(registeredList.get(i).getMatricNumber())) {
					// If it is indeed the same student, we remove him from list
					registeredList.remove(i);
					// If there is a waitlist of students
					if (waitList.size() > 0) {
						// Get the first student in the waitlist
						Student student1 = studentService.findStudentByMatric(waitList.get(0).getMatricNumber());
						// Add him to the registered list
						registeredList.add(student1);
						// Remove him from waitlist
						waitList.remove(0);
						// Get his timetable
						ArrayList<CourseStatus> waitListStudentTimetable = student1.getTimetable();
						// For those classes with this index, change status from 'wait' to 'registered'
						for (CourseStatus j : waitListStudentTimetable) {
							if (j.getIndex().equals(index)) {
								j.setStatus("registered");
							}
						}
						// Just to be absolutely sure, we set the timetable
						student1.setTimetable(waitListStudentTimetable);

					} else {
						// If no waitlist, we just increment the vacancy by 1 after removing student
						course.setVacancy(course.getVacancy() + 1);
					}
				}
			}
			// If the student was not in the registered list, he definitely is in the
			// waitlist
			for (int i = 0; i < waitList.size(); i++) {
				if (student == waitList.get(i)) {
					waitList.remove(student);
				}
			}
		}
	}

	// Solo Swap public method
	@Override
	public boolean soloSwap(Student student1, String oldIndex, String newIndex) {
		// Get the classes of the old and new indexes
		ArrayList<Course> classes1 = findCourseByIndex(oldIndex);
		ArrayList<Course> classes2 = findCourseByIndex(newIndex);
		// For each of these classes
		for (int j = 0; j < classes1.size(); j++) {
			// We get the matching old and new class
			Course course1 = classes1.get(j);
			Course course2 = classes2.get(j);
			// We get the matching registeredLists and waitLists
			ArrayList<Student> registeredList1 = course1.getRegisteredList();
			ArrayList<Student> registeredList2 = course2.getRegisteredList();

			ArrayList<Student> waitList1 = course1.getWaitList();
			ArrayList<Student> waitList2 = course2.getWaitList();

			// Flag to signify if we already removed student from registeredList, if flag is
			// false, means student in waitlist
			boolean flag = false;
			for (int i = 0; i < registeredList1.size(); i++) {
				if (registeredList1.get(i).getMatricNumber().equals(student1.getMatricNumber())) {
					registeredList1.remove(i);
					// Increase vacancy
					course1.setVacancy(course1.getVacancy() + 1);
					flag = true;
					break;
				}
			}
			if (flag == false) {
				for (int i = 0; i < waitList1.size(); i++) {
					if (waitList1.get(i).getMatricNumber().equals(student1.getMatricNumber())) {
						waitList1.remove(i);

						break;
					}
				}
			}

			// If the new index has no vacancy, add to wait list
			if (course2.getVacancy() < 1) {
				waitList2.add(student1);
				course2.setWaitList(waitList2);
			} else {
				// If got vacancy, add to registeredlist
				// Reduce vacancy
				registeredList2.add(student1);
				course2.setRegisteredList(registeredList2);
				course2.setVacancy(course2.getVacancy() - 1);
			}
		}
		return true;
	}

	@Override
	public boolean peerSwap(Student student1, Student student2, String oldIndex, String newIndex) {
		// Get the classes of the old and new indexes
		ArrayList<Course> classes1 = findCourseByIndex(oldIndex);
		ArrayList<Course> classes2 = findCourseByIndex(newIndex);
		// For each of these classes
		for (int j = 0; j < classes1.size(); j++) {
			// We get the matching old and new class
			Course course1 = classes1.get(j);
			Course course2 = classes2.get(j);
			// We get the matching registeredLists and waitLists
			ArrayList<Student> registeredList1 = course1.getRegisteredList();
			ArrayList<Student> registeredList2 = course2.getRegisteredList();

			ArrayList<Student> waitList1 = course1.getWaitList();
			ArrayList<Student> waitList2 = course2.getWaitList();
			boolean flag = false;
			for (int i = 0; i < registeredList1.size(); i++) {
				if (registeredList1.get(i).getMatricNumber().equals(student1.getMatricNumber())) {
					registeredList1.remove(i);
					registeredList1.add(student2);
					flag = true;
					break;
				}
			}
			if (flag == false) {
				for (int i = 0; i < waitList1.size(); i++) {
					if (waitList1.get(i).getMatricNumber().equals(student1.getMatricNumber())) {
						waitList1.remove(i);
						waitList1.add(student2);
						break;
					}
				}
			}
			boolean flag2 = false;
			for (int i = 0; i < registeredList2.size(); i++) {
				if (registeredList2.get(i).getMatricNumber().equals(student2.getMatricNumber())) {
					registeredList2.remove(i);
					registeredList2.add(student1);
					flag2 = true;
					break;
				}
			}
			if (flag2 == false) {
				for (int i = 0; i < waitList2.size(); i++) {
					if (waitList2.get(i).getMatricNumber().equals(student2.getMatricNumber())) {
						waitList2.remove(i);
						waitList2.add(student1);
						break;
					}
				}
			}
		}
		return true;
	}
}
