package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {


    //store the name of the file
        public ContractFileManager(String fileName){
        }


//save any contract to the file

    public void saveContract(Contract contract){
            String line = ""; // the line to write

        //check the type of contract
        if(contract instanceof SalesContract sale) {
            line = sale.getContractLine();
        } else if (contract instanceof LeaseContract lease){
            line = lease.getContractLine();
        } else {
            System.out.println("No contract type found");
            return;
        }
        //append the line to the file
        try(FileWriter fw = new FileWriter("contracts.csv",true)){
            fw.write(line + "\n");
            System.out.println("Contract has been saved");
        } catch (IOException e) {
            System.out.println("Error saving to contracts file" + e.getMessage());
        }
    }

//        System.out.println(contract.getVehicleSold().getVin());
//        System.out.println(contract.getVehicleSold().getPrice());
//        System.out.println(contract.getTotalPrice());
//        System.out.println(contract.getCustomerName());


}
