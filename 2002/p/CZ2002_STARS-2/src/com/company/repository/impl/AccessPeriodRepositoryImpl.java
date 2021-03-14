package com.company.repository.impl;

import com.company.repository.AccessPeriodRepository;
import com.company.model.AccessPeriod;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AccessPeriodRepositoryImpl implements AccessPeriodRepository {
	/* Repository level - methods that contains the code to read/write from the database
	*  This is the base level of the stack which interacts directly with the access.txt data file.
	*/
	
	// We create a private accessPeriod object
    private AccessPeriod accessPeriod = new AccessPeriod();

    // Constructor which calls loadAccessPeriod immediately when object is initialized
    public AccessPeriodRepositoryImpl() {
        loadAccessPeriod();
    }

    // To load the access period from the access.txt file
    @Override
    public void loadAccessPeriod() {
    	// Use a scanner to read the access.txt file
        try (Scanner scanner = new Scanner(new FileInputStream("com/company/file/access.txt"))) {
            while (scanner.hasNextLine()) {
            	/* Parse the line accordingly. We assume it is properly formatted since the only way for user 
                *  to update is through this application and not manually, so no need for error prevention at this level.
                */
                String nextLine = scanner.nextLine();
                StringTokenizer star = new StringTokenizer(nextLine, ",");
                String startDateString = star.nextToken().trim();
                String endDateString = star.nextToken().trim();
                LocalDate startDate = LocalDate.parse(startDateString);
                LocalDate endDate = LocalDate.parse(endDateString);
                accessPeriod = new AccessPeriod(startDate, endDate);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Getter method
    @Override
    public AccessPeriod getAccessPeriod() {
        return accessPeriod;
    }

    // Save the updated Access Period to the access.txt file
    @Override
    public void updateAccessPeriod() {
    	// The accessPeriod object is already updated using the controller
        StringBuilder st = new StringBuilder();
        st.append(accessPeriod.getStartDate().toString().trim());
        st.append(",");
        st.append(accessPeriod.getEndDate().toString().trim());
        try (PrintWriter out = new PrintWriter(new FileWriter("com/company/file/access.txt"))) {
            out.println(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
