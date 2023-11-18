package app;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Program {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter department name: ");
        String departmentName = sc.nextLine();

        System.out.println("Enter worker data:");
        System.out.print("Name: ");
        String workerName = sc.nextLine();
        System.out.print("Level: ");
        String workerLevel = sc.nextLine();
        System.out.print("Base salary: ");
        double baseSalary = sc.nextDouble();

        Worker worker = new Worker(workerName,
                WorkerLevel.valueOf(workerLevel),
                baseSalary,
                new Department(departmentName));

        System.out.print("How many contracts to this worker? ");
        int workerContracts = sc.nextInt();
        sc.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat(
                "dd/MM/yyyy");

        for (int i = 0; i < workerContracts; i++) {
            System.out.printf("Enter contract #%d data: %n",
                    i + 1);

            System.out.print("Date (DD/MM/YYYY): ");
            String unformattedDate = sc.nextLine();
            Date contractDate = sdf.parse(unformattedDate);

            System.out.print("Value per hour: ");
            double valuePerHour = sc.nextDouble();
            sc.nextLine();

            System.out.print("Duration (hours): ");
            int hours = sc.nextInt();
            sc.nextLine();

            worker.addContract(new HourContract(contractDate, valuePerHour, hours));
        }

        System.out.print("Enter month and year to calculate income " +
                "(MM/YYYY): ");
        String incomeMonthAndYear = sc.nextLine();

        int month = Integer.parseInt(incomeMonthAndYear.substring(0, 2));
        int year = Integer.parseInt(incomeMonthAndYear.substring(3));

        double income = worker.income(year, month);

        System.out.print(worker);
        System.out.println("Income for " + incomeMonthAndYear
                + ": " + String.format("%.2f", income));

        sc.close();
    }
}
