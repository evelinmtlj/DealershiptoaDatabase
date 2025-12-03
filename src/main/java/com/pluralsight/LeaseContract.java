package com.pluralsight;

import java.time.LocalDate;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;


    public LeaseContract( String customerName, String customerEmail, Vehicle vehicleSold) {
        super(LocalDate.now().toString(), customerName, customerEmail, vehicleSold);
    }
 //getters and setters for vehicle price

    public double getExpectedEndingValue() {
        return getVehicleSold().getPrice() * 0.5;
    }

    public double getLeaseFee() {
        return getVehicleSold().getPrice() * .07;
    }

    //formula
    public double formulaMonthlyPayment(double principal,double annualRate,int months){

        //convert annual interest rate to monthly decimal rate
        double monthlyInterestRate = (annualRate /12) ;


        //calculate EMI
        double payment  =
                principal * monthlyInterestRate * Math.pow(1 +  monthlyInterestRate,months) /
                        (Math.pow(1 + monthlyInterestRate, months)-1);


        return payment;
    }

    @Override
    public double getTotalPrice(){
return getVehicleSold().getPrice() + getLeaseFee()  - getExpectedEndingValue();
    }

    @Override
    public double getMonthlyPayment(){
double principal = getTotalPrice();
double annualRate = .04;
int months = 36;

return formulaMonthlyPayment(principal,annualRate,months);

    }
A
    @Override
    public String toString() {
                "Customer Email: " + getCustomerEmail() + "\n" +
                "Vehicle Sold: " + getVehicleSold() + "\n" +
                "Lease Fee: $" + String.format("%.2f", getLeaseFee()) + "\n" +
                "Expected Ending Value: $" + String.format("%.2f", getExpectedEndingValue()) + "\n" +
                "Total Price: $" + String.format("%.2f", getTotalPrice()) + "\n" +
                "Monthly Payment: $" + String.format("%.2f", getMonthlyPayment());
    }

    public String getContractLine(){
        return "LEASE|" + getDateOfContract() + "|" + getCustomerName()
                + "|" + getCustomerEmail() + "|" + getVehicleSold().getVin() + "|"
                + getVehicleSold().getYear() + "|" + getVehicleSold().getMake() + "|"
                + getVehicleSold().getModel() + "|" + getVehicleSold().getVehicleType()
                + "|" + getVehicleSold().getColor() + "|" + getVehicleSold().getOdometer() +
                "|" + getVehicleSold().getPrice() + "|" + getExpectedEndingValue() + "|" + getLeaseFee()
                + "|" +String.format("%.2f",getTotalPrice())  + "|" + String.format("%.2f",getMonthlyPayment());

    }
    //save contract to file



}
