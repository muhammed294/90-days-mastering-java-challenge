package edu.haramaya.cosc3053.day9;

import java.util.Locale;

public class PayrollEngine {

    public static abstract class Employee {
        private final String id;
        private final String name;

        public Employee(String id, String name) { this.id = id; this.name = name; }

        public String getId() { return id; }
        public String getName() { return name; }

        public abstract double calculatePay();
    }

    // TODO 9.1: FullTimeEmployee (monthlySalary) extending Employee
    public static class FullTimeEmployee extends Employee {
        private final double monthlySalary;

        public FullTimeEmployee(String id, String name, double monthlySalary) {
            super(id, name);
            if (!(monthlySalary >= 0.0) || !Double.isFinite(monthlySalary)) {
                throw new IllegalArgumentException("Monthly salary must be a non-negative number.");
            }
            this.monthlySalary = monthlySalary;
        }

        public double getMonthlySalary() { return monthlySalary; }

        @Override
        public double calculatePay() {
            return monthlySalary;
        }
    }

    // TODO 9.2: ContractEmployee (hourlyRate, hoursWorked) extending Employee
    public static class ContractEmployee extends Employee {
        private final double hourlyRate;
        private final double hoursWorked;

        public ContractEmployee(String id, String name, double hourlyRate, double hoursWorked) {
            super(id, name);
            if (!(hourlyRate >= 0.0) || !Double.isFinite(hourlyRate)) {
                throw new IllegalArgumentException("Hourly rate must be a non-negative number.");
            }
            if (!(hoursWorked >= 0.0) || !Double.isFinite(hoursWorked)) {
                throw new IllegalArgumentException("Hours worked must be a non-negative number.");
            }
            this.hourlyRate = hourlyRate;
            this.hoursWorked = hoursWorked;
        }

        public double getHourlyRate() { return hourlyRate; }
        public double getHoursWorked() { return hoursWorked; }

        @Override
        public double calculatePay() {
            return hourlyRate * hoursWorked;
        }
    }

    // TODO 9.3: Polymorphic payroll processor looping over Employee[]
    public static double processPayroll(Employee[] employees) {
        double total = 0.0;
        for (Employee e : employees) {
            if (e == null) continue;                 // skip empty slots
            double pay = e.calculatePay();           // dynamic method dispatch happens here
            System.out.println(String.format(Locale.US, "[%s] %s (%s) -> $%.2f",
                    e.getId(), e.getName(), e.getClass().getSimpleName(), pay));
            total += pay;
        }
        return total;
    }

    // Extra demo: run it by passing the program argument "demo"
    private static void runDemo() {
        System.out.println("--- Payroll run ---");
        Employee[] staff = {
                new FullTimeEmployee("E-001", "Abebe Bekele", 12000.00),
                new ContractEmployee("E-002", "Kebede Lemma", 25.50, 120),
                new FullTimeEmployee("E-003", "Almaz Tesfaye", 8500.00)
        };
        double total = processPayroll(staff);
        System.out.println(String.format(Locale.US, "TOTAL PAYROLL: $%.2f", total));

        System.out.println("--- Guard test ---");
        try {
            new FullTimeEmployee("E-004", "Bad Data", -100);
        } catch (IllegalArgumentException ex) {
            System.out.println("Caught: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Day 09 Payroll Hierarchy Challenge");
        if (args.length > 0 && args[0].equals("demo")) {
            runDemo();
        }
    }
}
