package com.pluralsight.models;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;


    public SalesContract(String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(LocalDate.now().toString(), customerName, customerEmail, vehicleSold);
        this.finance = finance;
    }

    public double getSalesTaxAmount() {
        return getVehicleSold().getPrice() * .05;
    }

    public double getRecordingFee() {
        return 100;
    }

    public double getProcessingFee() {
        if(getVehicleSold().getPrice() < 10000) {
           return 295;
        } else {
           return 495;
        }

    }


    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getTotalPrice(){
return getVehicleSold().getPrice() + getSalesTaxAmount() + getRecordingFee() + getProcessingFee();
    }

    public double formulaMonthlyPayment(double principal,double annualRate,int months){

        //convert annual interest rate to monthly decimal rate
double monthlyInterestRate = (annualRate /12) ;
        return    principal * monthlyInterestRate * Math.pow(1 +  monthlyInterestRate,months) /
                (Math.pow(1 + monthlyInterestRate, months)-1);
    }

    @Override
    public double getMonthlyPayment(){
        //check if customer did not finance

        if(!finance){
            return 0;
        }
        double principal = getTotalPrice();
        double annualRate;
        int months;

        if(getVehicleSold().getPrice() >= 10000) {
            annualRate = .0425;
            months = 48;
        } else {
            annualRate = .0525;
            months = 24;
        }
        return formulaMonthlyPayment(principal,annualRate,months);
    }

    @Override
    public String toString() {
        return "Sales Contract:\n" +
                "Customer Name: " + getCustomerName() + "\n" +
                "Customer Email: " + getCustomerEmail() + "\n" +
                "Vehicle Sold: " + getVehicleSold() + "\n" +
                "Total Price: $" + getTotalPrice() + "\n" +
                "Monthly Payment: $" + String.format("%.2f", getMonthlyPayment()) + "\n" +
                "Financed: " + (finance ? "Yes" : "No");
    }

    public String getContractLine(){
        return "Sale|" + getDateOfContract() + "|" + getCustomerName() + "|" + getCustomerEmail()
                + "|" + getVehicleSold().getVin()
                + "|" +  getVehicleSold().getYear()
                + "|" + getVehicleSold().getMake() +
                "|"+ getVehicleSold().getModel() +"|" +
                getVehicleSold().getVehicleType() + "|" + getVehicleSold().getColor() + "|" + getVehicleSold().getOdometer() + "|"
        + getVehicleSold().getPrice() + "|" + getSalesTaxAmount() + "|" + getRecordingFee() +
                "|" + getProcessingFee() +"|" + String.format("%.2f",getTotalPrice()) + "|" + (finance ? "Yes" : "NO") + "|" +String.format("%.2f",getMonthlyPayment());


    }



}
