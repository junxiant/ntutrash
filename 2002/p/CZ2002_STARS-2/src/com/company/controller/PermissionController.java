package com.company.controller;

import java.io.Console;
import com.company.model.AccessPeriod;
import com.company.model.CourseStatus;
import com.company.model.Student;
import com.company.service.StudentService;
import com.company.model.Professor;
import com.company.service.ProfService;
import com.company.utils.PasswordUtil;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Scanner;

public class PermissionController {
	/*
	 * Gateway between user input and service layer, decides what to do with the
	 * input and how to output the response. Input validation is done here.
	 * 
	 * This is a special controller which uses both the student and professor
	 * services to do authentication.
	 */

	private StudentService studentService;
	private ProfService profService;

	// Public Constructor that takes in both the studentService and profService
	// objects
	public PermissionController(StudentService studentService, ProfService profService) {
		this.studentService = studentService;
		this.profService = profService;
	}

	public Object authenticateUser(int option) {
		Scanner sc = new Scanner(System.in);
		switch (option) {
		// If professor/admin mode
		case 1:
			// Input validation for professor
			while (true) {
				System.out.print("Please enter your email:");
				String account = sc.nextLine();
				// String account = "abob@ntu.edu.sg";
				// Check if such an admin user with the email exists
				Professor professor = profService.findProfessorByName(account);
				if (professor == null) {
					// If no such admin account is found, we just send the user back to the options
					// menu
					System.out.print("No such admin account!\n");
					break;
				}
				// If there is indeed such a professor, we go onto password validation
				while (true) {
					// Password input masking
					Console console = System.console();
					String password = new String(console.readPassword("Please enter your password: "));
					// If password is an empty line, we go back to entering the email
					if (password.equals("")) {
						break;
					}
					// String password = "123456";

					// We encrypt the password using a caesar cipher
					password = PasswordUtil.caesarCipherEncrypt(password);

					// If the encrypted password matches the encrpyted password we have in the
					// system, then authentication is done
					if (password.equals(professor.getPassword())) {
						System.out.print("Login successful!\n");
						System.out.println("Welcome " + professor.getName() + "!\n");
						return professor;
					} else {
						// Else, go back to entering a password
						System.out.print(
								"Wrong password! Please try again, or input an empty line to login using another email.\n");
					}
				}
			}
			break;
		// If student mode
		case 2:
			// Input validation for student
			while (true) {
				System.out.print("Please enter your matriculation number: ");
				String account = sc.nextLine();
				// String account = "U1920416C";

				// Check if such a student user with the matric number exists
				Student student = studentService.findStudentByMatric(account);
				if (student == null) {
					// If no such student account is found, we just send the user back to the
					// options menu
					System.out.print("No such student exists!\n");
					break;
				}

				// If there is indeed such a student, we go onto password validation
				while (true) {
					// Password input masking
					Console console = System.console();
					String password = new String(console.readPassword("Please enter your password: "));
					// If password is an empty line, we go back to entering the matric number
					if (password.equals("")) {
						break;
					}
					// String password = "123456";
					// We encrypt the password using a caesar cipher
					password = PasswordUtil.caesarCipherEncrypt(password);

					// If the encrypted password matches the encrpyted password we have in the
					// system, then authentication is done
					if (password.equals(student.getPassword())) {
						System.out.print("Login successful!\n");
						System.out.println("Welcome " + student.getName() + "!\n");
						return student;
					} else {
						System.out.print(
								"Wrong password! Please try again, or input an empty line to login using another matriculation number.\n");
					}
				}
			}
		default:
			break;
		}
		return null;
	}

}
