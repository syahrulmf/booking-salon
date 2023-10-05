package com.booking.service;

import com.booking.models.Customer;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.models.Employee;

import java.text.DecimalFormat;
import java.util.List;

public class PrintService {
    public static void printMenu(String title, String[] listMenu) {
        int number = 1;
        String formatTabel = "| %-3s | %-48s |%n";
        System.out.println(title);
        System.out.println("==========================================================");
        System.out.printf(formatTabel, "No", "Menu");
        System.out.println("==========================================================");

        for (String menu : listMenu) {
            if (number == listMenu.length) {
                System.out.printf(formatTabel, 0, menu);
            }else {
                System.out.printf(formatTabel, number, menu);
            }
            number++;
        }
        System.out.println("==========================================================");
    }

    public static String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    public static void showRecentReservation(String title, List<Reservation> reservationList){
      int number = 1;
      String formatTable = "| %-4s | %-10s | %-15s | %-35s | %-15s | %-12s | %-12s | %n";
      System.out.println("=============================================================================================================================");
      System.out.format("| %-121s | %n", title);
      System.out.println("=============================================================================================================================");
      System.out.printf(formatTable, "No", "ID", "Nama Customer", "Service", "Total Biaya", "Petugas", "Workstage");
      System.out.println("=============================================================================================================================");

      for (Reservation reservation : reservationList) {
        if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
          System.out.printf(formatTable, number, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), formatCurency(reservation.getReservationPrice()), reservation.getEmployee().getName(), reservation.getWorkstage());
          number++;
        }
      }
      System.out.println("=============================================================================================================================");
    }

    public static void showAllCustomer(String title, List<Person> listData){
      int number = 1;
      String formatTabel = "| %-3s | %-12s | %-12s | %-10s | %-10s | %-12s | %n";

      System.out.println("==============================================================================");
      System.out.format("| %-74s |%n", title);
      System.out.println("==============================================================================");
      System.out.printf(formatTabel, "No", "ID", "Nama", "Alamat", "Membership", "Uang");
      System.out.println("==============================================================================");

//     iterates over the list
      for(Person data: listData){
        if (data instanceof Customer) {
          System.out.format(formatTabel, number, data.getId(), data.getName(), data.getAddress(), ((Customer)data).getMember().getMembershipName(), formatCurency(((Customer)data).getWallet()));
          number++;
        }
      }
      System.out.println("==============================================================================");
      }

    public static void showAllEmployee(String title, List<Person> listData){
      int number = 1;
      String formatTabel = "| %-3s | %-12s | %-12s | %-10s | %-10s | %n";

      System.out.println("===============================================================");
      System.out.format("| %-60s |%n", title);
      System.out.println("===============================================================");
      System.out.printf(formatTabel, "No", "ID", "Nama", "Alamat", "Pengalaman");
      System.out.println("===============================================================");

//     iterates over the list
      for(Person data: listData){
        if (data instanceof Employee) {
          System.out.format(formatTabel, number, data.getId(), data.getName(), data.getAddress(), ((Employee)data).getExperience());
          number++;
        }
      }
      System.out.println("===============================================================");
      }

    public static void showAllServices(String title, List<Service> listData) {
      int number = 1;
      String formatTabel = "| %-3s | %-12s | %-20s | %-10s | %n";

      System.out.println("==========================================================");
      System.out.format("| %-54s |%n", title);
      System.out.println("==========================================================");
      System.out.printf(formatTabel, "No", "ID", "Nama", "Harga");
      System.out.println("==========================================================");

//     iterates over the list
      for(Service data: listData){
        System.out.format(formatTabel, number, data.getServiceId(), data.getServiceName(), formatCurency(data.getPrice()));
        number++;
      }
      System.out.println("==========================================================");
    }

    public void showHistoryReservation(){

    }

    public static String formatCurency(double currency) {
      DecimalFormat df = new DecimalFormat("#,###.00");

      return df.format(currency);
    }
}
