package com.cse360.medicalproject.projectphaseii;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Receptionist {
	
	private DataAccessObject dao;
	private PatientInfoForm infoForm;
	private String currPatientID;
	
	public Receptionist(DataAccessObject dao, PatientInfoForm infoForm){
		this.dao = dao;
		this.infoForm = infoForm;
		this.currPatientID = createPatientID();
	}
	
	public Receptionist(DataAccessObject dao, PatientInfoForm infoForm, String currPatientID){
		this.dao = dao;
		this.infoForm = infoForm;
		this.currPatientID = currPatientID;
	}
	
    private String createPatientID() {
    	Random randID = new Random();
		int min = 10000000;
		int max = 99999999;
    	int newID;
    	do {
    		// Create new id for the patient
    		newID = randID.nextInt((max-min) + 1) + min;
    	// Continue creating an id until a unique id is generated
    	} while( (dao.isDoctorIdValid(String.valueOf(newID)) || (dao.isPatientIdValid(String.valueOf(newID)) )));
    	return String.valueOf(newID);
    }

    protected  void createPatientFile() {
    	try {
    		// Create new patient file name
    		String infoFileName = currPatientID + ".txt";
    		// Store new patient file in data folder
    		File storeFile = new File(dao.getDataFolderPath() + "/" + infoFileName);
    		
    		if(!storeFile.exists()) {
    			storeFile.createNewFile();
           		// Write patient info form data into the file
        		FileWriter writeInfo = new FileWriter(storeFile);
        		writeInfo.write(infoForm.toString());
        		writeInfo.close();
    		}
    		else {
    			// Delete old file
    			storeFile.delete();
    			// Update file
    			File fileNew = new File(dao.getDataFolderPath() + "/" + infoFileName);
        		FileWriter writeInfo = new FileWriter(storeFile);
        		writeInfo.write(infoForm.toString());
        		writeInfo.close();
    		}
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    protected String getCurrPatientID() {
		return currPatientID;
	}
    
	//needs implementation
    public static void createProviderID() {

    }

    public static HealthcareProvider createHealthcareProvider() {
        return null;
    }
}
