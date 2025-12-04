package com.pluralsight.userInterface;

import com.pluralsight.persistance.DataManager;
import com.pluralsight.persistance.LeaseDao;
import com.pluralsight.persistance.SalesDao;
import org.apache.commons.dbcp2.BasicDataSource;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/dealership");
        ds.setUsername("root");
        ds.setPassword("yearup24");

        DataManager dm = new DataManager(ds);
        SalesDao sd = new SalesDao(ds);
        LeaseDao ld = new LeaseDao(ds);

        UserInterface ui = new UserInterface(dm, sd, ld);
        ui.display();

    }
}