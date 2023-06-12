package com.solvd.hospitalpatientrecoverymodel.app;
import com.solvd.hospitalpatientrecoverymodel.exceptions.AddPatientException;
import com.solvd.hospitalpatientrecoverymodel.exceptions.CardNumberException;
import com.solvd.hospitalpatientrecoverymodel.hospital.Hospital;
import com.solvd.hospitalpatientrecoverymodel.humans.*;
import com.solvd.hospitalpatientrecoverymodel.payment.PaymentDetails;
import com.solvd.hospitalpatientrecoverymodel.utils.HospitalPatientUtils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationPatient {

   private static Logger log = LogManager.getLogger(ApplicationPatient.class.getName());

    public PaymentDetails createPaymentDetails(String name, String cardNumber, String dateOfExp, String pinCode) {

        try {
            PaymentDetails paymentDetails = new PaymentDetails();
            checkCardNumber(name, cardNumber);
            paymentDetails.setName(name);
            paymentDetails.setCardNumber(cardNumber);
            paymentDetails.setDateOfExp(dateOfExp);
            paymentDetails.setPinCode(pinCode);
            return paymentDetails;
        } catch (CardNumberException e) {
            System.out.println(e.getMsg());
            return null;

        }
    }



    public void checkCardNumber(String name, String cardNumber) throws CardNumberException {
        if (name.equals("American Express") && cardNumber.length() != 15) {

            log.error("Greska kod broja kartice");
            throw new CardNumberException("American Express must have 15 digits.");
        } else if (cardNumber.length() != 16) {
            throw new CardNumberException("Card number must have 16 digits.");
        }

    }

    public Nurse createNurse(String firstName, String lastName, List<Patient> patientList){
        log.info("Creating a nurse");
        Nurse nurse = new Nurse();
        nurse.setFirstName(firstName);
        nurse.setLastName(lastName);
        nurse.setMaxNumberOfPatientPerEmployee(HospitalPatientUtils.MAX_NUM_OF_PATIENTS_PER_NURSE);
        nurse.setListOfPatients(patientList);
        return nurse;
    }

    public Patient createPatient(String firstName, String lastName, String healthCareIdentifier, String bloodType, String city) {
        log.info("Creating a patient.");
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setHealthCareIdentifier(healthCareIdentifier);
        patient.setBloodType(bloodType);
        patient.setCity(city);
        return patient;
    }

    public Hospital createHospital(String city, String state, String address, int zipCode, String phoneNumber, int established, int capacity, List<Patient> patientList) {
        Hospital hospital = new Hospital(city, state, address, zipCode, phoneNumber, established, capacity);
        for(Patient patient : patientList){
            try{
                hospital.addPatient(patient);

            }
            catch(AddPatientException addPatient){
                log.error("Error.");
            }
        }

        return hospital;
    }

    public Patient getPatientbyID(int id, Nurse nurse) {
        try {
            Patient patient = nurse.getListOfPatients().get(id);
            System.out.println("The patient with requested ID is here.");
            return patient;


        } catch (Exception exception) {
            System.out.println("The patient with requested ID doesn't exist.");
            return null;
        }
        finally {
            int numberOfPatients = nurse.getListOfPatients().size();
            String numPatients = String.format("number of patients %s",numberOfPatients);
            System.out.println(numPatients);
        }

    }
    public Patient updatePatient(String healthCareIdentification, int index, Hospital hospital) {
        try {
            Patient patient = hospital.getListOfPatients().get(index);
            checkHealthCareIdentifier(healthCareIdentification);
            return patient;
        } catch (ArrayIndexOutOfBoundsException outOfIndex) {
            System.out.println("Out of index" + outOfIndex.getMessage());
            return null;
        } catch (CardNumberException exc) {
            System.out.println("Lack of digits" + " " + exc.getMsg()); // ne razumem ovo
            return null;
        }

    }
    public void checkHealthCareIdentifier(String healthCareIdentification) throws CardNumberException{
        if(healthCareIdentification.length() != 6){
            throw new CardNumberException("Digits number doesn't match. ");
        }
    }
    public void addPatientToHospital(Patient patient, Hospital hospital){
        try {
            hospital.addPatient(patient);
        } catch (AddPatientException addPatient) {
            System.out.println(addPatient.getMsg());
        }
    }
}

