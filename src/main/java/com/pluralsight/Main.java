package com.pluralsight;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Dealership dealership = DealShipFileManager.getDealership();
//        //created an instance and call its display method
     UserInterface uInterface = new UserInterface(dealership);
//
     uInterface.display();

//       Vehicle vehicleToSell = new Vehicle(123, 2012, "vmake", "vmodel", "vtype", "red", 30000, 15000);
////
//       SalesContract sales = new SalesContract("10-21-2023","Jesica mtl","matuljesica@gmail.com",vehicleToSell,true);
////
//            sales.setFinance(true);
////        System.out.println(sales);
//
//        ContractFileManager.saveContract(sales);
//
//        Vehicle vehicleToLease = new Vehicle(456, 2018, "vmake2", "vmodel2", "vtype2", "red", 3000, 45000);
//
//        LeaseContract lease = new LeaseContract("12-21-2021","Evelin ne","evelinttk@gmail.com",vehicleToLease);
//        System.out.println("\n " + lease);
//
//        ContractFileManager.saveContract(lease);



//        System.out.println("----------------- Sales contract------------");
//        System.out.println("Vehicle price: $" + sales.getVehiclePrice());
//        System.out.println("Total price: $" + sales.getTotalPrice());
//        System.out.println("Monthly payment: $" + sales.getMonthlyPayment());

    }
}