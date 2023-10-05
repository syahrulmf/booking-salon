package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class MenuService {
  public static List<Person> personList = PersonRepository.getAllPerson();
  public static List<Service> serviceList = ServiceRepository.getAllService();
  public static List<Reservation> reservationList = new ArrayList<>();

  public static void mainMenu() {
    String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
    boolean isLooping = true;

    do {
      PrintService.printMenu("Main Menu", mainMenuArr);
      int pilih = ValidationService.validateNumberWithRange("Pilih Menu: ", "Pilihan Tidak Dimengerti", ValidationService.regexNumber, 3, 0);

      switch (pilih) {
        case 1:
          showDataMenu();
          break;
        case 2:
          PrintService.showAllCustomer("List Data Customer", personList);
          String customerID = ValidationService.validateInput("Silahkan Masukan Customer ID: ", "Input Tidak Dimengerti, Pastikan Customer ID valid!", ValidationService.regexID);
          PrintService.showAllEmployee("List Data Employee", personList);
          String employeeID = ValidationService.validateInput("Silahkan Masukan Employee ID: ", "Input Tidak Dimengerti, Pastikan Employee ID valid!", ValidationService.regexID);
          PrintService.showAllServices("List Data Service", serviceList);

          List<Service> services = new ArrayList<>();
          boolean isService = true;

          do {
            String serviceID = ValidationService.validateInput("Silahkan Masukan Service ID: ", "Input Tidak Dimengerti, Pastikan Employee ID valid!", ValidationService.regexID);
            services.add(ReservationService.getServiceByServiceId(serviceID));
            isService = ValidationService.validateService("Ingin Pilih Service Yang Lain (Y/T)? ");
          } while (isService);

          ReservationService.createReservation(customerID, employeeID, services);

          isLooping = ValidationService.validateMenu("Press 0 for Back To Main Menu: ");
          break;
        case 0:
          isLooping = false;
          break;
      }
    } while(isLooping);
  }

  public static void showDataMenu() {
    String[] dataMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "History Reservation + Total Keuntungan", "Back to main menu"};
    boolean isLooping = true;

    do {
      PrintService.printMenu("Data Booking Salon", dataMenuArr);
      int pilih = ValidationService.validateNumberWithRange("Pilih Menu: ", "Pilihan Tidak Dimengerti", ValidationService.regexNumber, 4, 0);

      switch (pilih) {
        case 1:
          PrintService.showRecentReservation("Recent Reservation", reservationList);
          isLooping = ValidationService.validateMenu("Press 0 for Back To Main Menu: ");
          break;
        case 2:
          PrintService.showAllCustomer( "List Data Customer", personList);
          isLooping = ValidationService.validateMenu("Press 0 for Back To Main Menu: ");
          break;
        case 3:
          PrintService.showAllEmployee("List Data Employee", personList);
          isLooping = ValidationService.validateMenu("Press 0 for Back To Main Menu: ");
          break;
        case 4:
          PrintService.showHistoryReservation("History Reservation", reservationList);
          isLooping = ValidationService.validateMenu("Press 0 for Back To Main Menu: ");
          break;
        case 0:
          isLooping = false;
          break;
      }
    } while(isLooping);
  }

}
