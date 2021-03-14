package com.company.service.impl;

import java.util.ArrayList;

import com.company.model.Course;
import com.company.model.CourseStatus;
import com.company.model.Student;
import com.company.repository.StudentRepository;
import com.company.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Student findStudentByMatric(String matricNumber) {
		return studentRepository.findStudentByMatricNumber(matricNumber);
	}

	@Override
	public ArrayList<Student> addStudentIntolist(String name, String gender, String matricNumber, String nationality,
			String password) {
		return studentRepository.addStudentIntolist(name, gender, matricNumber, nationality, password);
	}

	@Override
	public void updateStudentDAT() {
		studentRepository.updateStudentDAT();
	}

	@Override
	public void addCourseIntoTimeTable(int vacancy, Student student, Course course) {
		// If no vacancies, put into wait list
		if (vacancy == 0) {
			ArrayList<CourseStatus> timeTable = student.getTimetable();
			CourseStatus courseStatus = new CourseStatus(course.getIndex(), "wait");
			timeTable.add(courseStatus);

			System.out.println("Added " + course.getCode() + " " + course.getClassType() + " into wait list!");
		} else {
			// Else, we just add it as registered
			ArrayList<CourseStatus> timeTable = student.getTimetable();
			CourseStatus courseStatus = new CourseStatus(course.getIndex(), "registered");
			timeTable.add(courseStatus);

			System.out.println("Registered " + course.getCode() + " successfully!");
		}

		// Update the AUs for the student
		student.setTotalAu(student.getTotalAu() + course.getAu());
		System.out.println("You now have " + student.getTotalAu() + " AUs registered!");
	}

	@Override
	public void dropCourseFromTimeTable(Student student, Course course) {
		// Get the timetable of the student
		ArrayList<CourseStatus> timetable = student.getTimetable();
		// Get the index of the module
		String index = course.getIndex();

		// Remove all entries in the timetable that has a matching index
		for (int i = 0; i < timetable.size(); i++) {
			if (index.equals(timetable.get(i).getIndex())) {
				timetable.remove(i);
			}
		}
		System.out.println("Dropped " + course.getCode() + " successfully!");

		// Update the AUs for the student
		student.setTotalAu(student.getTotalAu() - course.getAu());
		System.out.println("You now have " + student.getTotalAu() + " AUs registered!");
	}

	// Solo Swap public method
	@Override
	public boolean soloSwap(Student student1, Course course1, Course course2, String oldIndex, String newIndex) {
		ArrayList<CourseStatus> timeTable1 = student1.getTimetable();
		for (int i = 0; i < timeTable1.size(); i++) {
			// For each entry in timetable, compare with the old index
			if (oldIndex.equals(timeTable1.get(i).getIndex())) {
				// If it matches with old index, we update the index to the new index
				timeTable1.get(i).setIndex(newIndex);
				// If the new index is for waitlist, set the status to wait, since default is registered
				if (course2.getVacancy() < 1) {
					timeTable1.get(i).setStatus("wait");
				}

			}
		}
		return true;
	}
	
	// Peer Swap public method
	@Override
	public boolean peerSwap(Student student1, Student student2, String oldIndex, String newIndex) {
		ArrayList<CourseStatus> timeTable1 = student1.getTimetable();
		ArrayList<CourseStatus> timeTable2 = student2.getTimetable();
		// Same code as solo swap function above, but repeated for both original user and peer
		for (int i = 0; i < timeTable1.size(); i++) {
			if (oldIndex.equals(timeTable1.get(i).getIndex())) {
				timeTable1.get(i).setIndex(newIndex);
			}
		}
		for (int i = 0; i < timeTable2.size(); i++) {
			if (newIndex.equals(timeTable2.get(i).getIndex())) {
				timeTable2.get(i).setIndex(oldIndex);
			}
		}

		return true;
	}

}
