package edu.haramaya.cosc3053.day1;

public class EnvironmentDiagnostic {
        public static void main(String[] args) {
            System.out.println("=========================================");
            System.out.println(" JAVA ENVIRONMENT DIAGNOSTIC TRACE ");
            System.out.println("=========================================");
            System.out.println(" Java Version : " + System.getProperty("java.version"));
            System.out.println(" Java Vendor : " + System.getProperty("java.vendor"));
            System.out.println(" OS Name : " + System.getProperty("os.name"));
            System.out.println(" OS Arch : " + System.getProperty("os.arch"));
            System.out.println(" User Name : " + System.getProperty("user.name"));
            System.out.println("=========================================");
        }
}
