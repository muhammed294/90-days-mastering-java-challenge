package edu.haramaya.cosc3053.day9;

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
            this.monthlySalary = monthlySalary;
        }

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
            this.hourlyRate = hourlyRate;
            this.hoursWorked = hoursWorked;
        }

        @Override
        public double calculatePay() {
            return hourlyRate * hoursWorked;
        }
    }

    // TODO 9.3: Polymorphic payroll processor looping over Employee[] array
    public static double processPayroll(Employee[] employees) {
        double total = 0.0;
        for (Employee employee : employees) {
            double pay = employee.calculatePay();
            System.out.printf("%s (%s): $%.2f%n", employee.getName(), employee.getId(), pay);
            total += pay;
        }
        return total;
    }

    public static void main(String[] args) {
        Employee[] staff = {
                new FullTimeEmployee("E-1", "Abebe", 5000.0),
                new ContractEmployee("E-2", "Kebede", 20.0, 80.0)
        };
        System.out.println("Total: $" + processPayroll(staff));
        System.out.println("Day 09 Payroll Hierarchy Challenge");
    }
}