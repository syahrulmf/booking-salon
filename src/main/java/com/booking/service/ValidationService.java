package com.booking.service;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

import java.util.List;
import java.util.Scanner;

public class ValidationService {
  public static String regexNumber = "^[0-9]+$";
  public static String regexHuruf = "^[a-zA-Z ]+$";
  public static String regexID = "^[A-Za-z0-9\\-]+$";
  private static Scanner input = new Scanner(System.in);

  public static String validateInput(String question, String errorMessage, String regex) {
      String result;
      boolean isLooping = true;
      do {
          System.out.print(question);
          result = input.nextLine();

          if (result.matches(regex)) {
              isLooping = false;
          }else {
              System.out.println(errorMessage);
          }
      } while (isLooping);

      return result;
  }

  public static int validateNumberWithRange(String question, String errorMessage, String regex, int max, int min) {
    int result;
    boolean isLooping = true;

    do {
      result = Integer.valueOf(validateInput(question, errorMessage, regex));
      if (result >= min && result <= max) {
          isLooping = false;
      }else {
          System.out.println(errorMessage);
      }
    } while (isLooping);

    return result;
  }

  public static String validateCustomerId(String question, String errorMessage, String regex) {
    String input;
    boolean isLooping = true;

    do {
      input = validateInput(question, errorMessage, regex);
      Customer dataCustomer = ReservationService.getCustomerByCustomerId(input);
      if (dataCustomer.getId() != null) {
        isLooping = false;
      } else {
        System.out.println(errorMessage);
      }
    } while (isLooping);

    return input;
  }

  public static String validateEmployeeId(String question, String errorMessage, String regex) {
    String input;
    boolean isLooping = true;

    do {
      input = validateInput(question, errorMessage, regex);
      Employee dataEmployee = ReservationService.getEmployeeByEmployeeId(input);
      if (dataEmployee.getId() != null) {
        isLooping = false;
      } else {
        System.out.println(errorMessage);
      }
    } while (isLooping);

    return input;
  }

  public static Service validateCustomerChoiceService(Service dataService, List<Service> listServices) {
    Service serv = new Service();

    for (Service data : listServices) {
      if (data.equals(dataService)) {
        serv = data;
      }
    }

    return serv;
  }

  public static String validateServiceId(String question, String errorMessage, String regex, List<Service> listServices) {
    String input;
    boolean isLooping = true;

    do {
      input = validateInput(question, errorMessage, regex);
      Service dataService = ReservationService.getServiceByServiceId(input);
      Service cutomerChoiceService = validateCustomerChoiceService(dataService, listServices);

      if (dataService.getServiceId() != null && cutomerChoiceService.getServiceId() == null) {
        isLooping = false;

      } else if (cutomerChoiceService.getServiceId() != null) {
        System.out.println("Service Sudah dipilih");

      } else {
        System.out.println(errorMessage);
      }
    } while (isLooping);

    return input;
  }

  public static String validateReservationId(String question, String errorMessage, String regex) {
    String input;
    boolean isLooping = true;

    do {
      input = validateInput(question, errorMessage, regex);
      Reservation dataReservation = ReservationService.getReservationById(input);
      if (dataReservation.getReservationId() != null) {
        isLooping = false;
      } else {
        System.out.println(errorMessage);
      }
    } while (isLooping);

    return input;
  }

  public static boolean validateMenu(String question) {
      boolean isLooping = false;

      int pilihan = validateNumberWithRange(question, "Hanya menerima inputan angka 0!", regexNumber, 0, 0);
      if (pilihan == 0) {
          isLooping = true;
      }

      return isLooping;
  }

  public static boolean validateService(String question, List<Service> services) {
    boolean isLooping = false;

    if (services.size() != MenuService.serviceList.size()) {
      String pilihan = validateInput(question, "Input Tidak Dimengerti, Pastikan Employee ID valid!", regexHuruf);

      if (pilihan.equalsIgnoreCase("Y")) {
        isLooping = true;
      }
    }

    return isLooping;
  }
}
