package com.company.controller;

import com.company.model.AccessPeriod;
import com.company.service.AccessPeriodService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AccessPeriodController {
	// Gateway between user input and service layer, decides what to do with the input and how to output the response.
	// Input validation is done here

	private AccessPeriodService accessPeriodService;
	private AccessPeriod accessPeriod;

	// Constructor method, immediately gets the access period from the service
	// object to store
	public AccessPeriodController(AccessPeriodService accessPeriodService) {
		this.accessPeriodService = accessPeriodService;
		this.accessPeriod = getAccessPeriod();
	}

	// Public utility method
	public LocalDate getCurrentDate() {
		return java.time.LocalDate.now();
	}

	// Private getter method
	private AccessPeriod getAccessPeriod() {
		return accessPeriodService.getAccessPeriod();
	}

	// Public setter method, but this only takes in input within the function and
	// not before so that input validation is done at this level
	public void setAccessPeriod() {

		Scanner sc = new Scanner(System.in);
		LocalDate startDate;
		LocalDate endDate;
		while (true) {
			// startDate input validation
			while (true) {
				try {
					System.out.println("Start date?(YYYY-MM-DD)");
					String startDateString = sc.next();
					startDate = LocalDate.parse(startDateString);
					break;
				} catch (Exception e) {
					System.out.println("ERROR: Invalid date format!");
				}
			}
			// endDate input validation
			while (true) {
				try {
					System.out.println("End date?(YYYY-MM-DD)");
					String endDateString = sc.next();
					endDate = LocalDate.parse(endDateString);
					break;
				} catch (Exception e) {
					System.out.println("ERROR: Invalid date format!");
				}
			}
			// access period validation to ensure input startDate is before input endDate
			if (startDate.isAfter(endDate)) {
				System.out.println("ERROR: Start date is after end date!\nPlease enter the dates again!");
			}
			else {
				break;
			}
		}
		this.accessPeriod.setStartDate(startDate);
		this.accessPeriod.setEndDate(endDate);
		// Call the service so that service will call the repo to update the file
		accessPeriodService.updateAccessPeriod();
		System.out.println("Access Period Updated!");
	}

	// Public utility method to print current period
	public void printAccessPeriod() {
		System.out.print("Access Period is: From ");
		System.out.print(this.accessPeriod.getStartDate().toString());
		System.out.print(" to ");
		System.out.println(this.accessPeriod.getEndDate().toString());
	}
	
	// Public utility method to compare current date with the access period date
	// Although this can be considered as permission checking, by putting it in this controller instead of PermissionController,
	// we can have the accessPeriod and getAccessPeriod() be private instead of public to encapsulate and protect further.
	public int checkAccessPeriod() {
        LocalDate today = java.time.LocalDate.now();
        LocalDate startDate = accessPeriod.getStartDate();
        LocalDate endDate = accessPeriod.getEndDate();
        if (startDate.isAfter(today)) {
        	return -1;
        }
        else if (endDate.isBefore(today)) {
        	return -2;
        }
        
        return 1;
    }
}
