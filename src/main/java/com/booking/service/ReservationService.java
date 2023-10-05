package com.booking.service;

import com.booking.models.*;

import java.util.List;

public class ReservationService {
  private static int idNumber = 1;

  public static String formatID(int number) {
    if (number < 10) {
      return "00" + number;
    } else if (number < 100) {
      return "0" + number;
    } else {
      return Integer.toString(number);
    }
  }

  public static void createReservation(String customerID, String employeeID, List<Service> listService) {
    Customer dataCustomer = getCustomerByCustomerId(customerID);
    Employee dataEmployee = getEmployeeByEmployeeId(employeeID);

    if (dataCustomer.getId() != null && dataEmployee.getId() != null && !listService.isEmpty()) {
      Reservation rev = new Reservation("Rsv-" + formatID(idNumber), dataCustomer, dataEmployee, listService, "In Process");
      // add reservation data to reservation list
      MenuService.reservationList.add(rev);
      // set new wallet customer
      dataCustomer.setWallet(dataCustomer.getWallet() - rev.getReservationPrice());
      idNumber++;
      System.out.println("\nBooking Berhasil!!\n");
      System.out.println("Total Biaya Booking : Rp. " + PrintService.formatCurency(rev.getReservationPrice()));
    } else {
      System.out.println("\nBooking Gagal, Pastikan Input Sesuai dengan List yang Tersedia!!\n");
    }
  }

  public static Customer getCustomerByCustomerId(String customerID){
    Customer dataCustomer = new Customer();
    for (Person data : MenuService.personList) {
      if (data instanceof Customer) {
        if (data.getId().equalsIgnoreCase(customerID))
          dataCustomer = (Customer) data;
      }
    }

    return dataCustomer;
  }

  public static Employee getEmployeeByEmployeeId(String employeeID){
    Employee dataEmployee = new Employee();
    for (Person data : MenuService.personList) {
      if (data instanceof Employee) {
        if (data.getId().equalsIgnoreCase(employeeID))
          dataEmployee = (Employee) data;
      }
    }

    return dataEmployee;
  }

  public static Service getServiceByServiceId(String serviceID){
    Service dataService = new Service();
    for (Service data : MenuService.serviceList) {
      if (data.getServiceId().equalsIgnoreCase(serviceID)) {
          dataService = data;
      }
    }

    return dataService;
  }

  public static Reservation getReservationById(String reservationID) {
    Reservation rev = new Reservation();

    for (Reservation data : MenuService.reservationList) {
      if (data.getReservationId().equalsIgnoreCase(reservationID)) {
        rev = data;
      }
    }

    return rev;
  }

  public static void editReservationWorkstage(String reservationID, String reservationType){
    Reservation dataReservation = getReservationById(reservationID);
    String result = "";

    if (dataReservation.getReservationId() != null) {
      Customer dataCustomer = getCustomerByCustomerId(dataReservation.getCustomer().getId());

      if (reservationType.equalsIgnoreCase("Finish")) {
        // mengubah workstage reservation ke finish
        dataReservation.setWorkstage("Finish");
        result = "Reservasi dengan ID " + reservationID + " Sudah Finish";

      } else if (reservationType.equalsIgnoreCase("Cancel")) {
        // mengubah workstage reservation ke cancel
        dataReservation.setWorkstage("Cancel");
        result = "Reservasi dengan ID " + reservationID + " Sudah Cancel";
        // jika reservasi cancel, uang customer dikembalikan
        dataCustomer.setWallet(dataCustomer.getWallet() + dataReservation.getReservationPrice());

      } else {
        result = "Reservasi Gagal diselesaikan, Pastikan data yang anda inputkan valid!";
      }
    } else {
      result = "Reservasi Gagal diselesaikan, Pastikan data yang anda inputkan valid!";
    }

    System.out.println("\n" + result + "\n");
  }

}
