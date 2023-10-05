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

  public static void editReservationWorkstage(){

  }

  // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
