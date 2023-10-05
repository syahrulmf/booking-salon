package com.booking.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.workstage = workstage;

        calculateReservationPrice();
    };

    private void calculateReservationPrice(){
        double price = 0;

        for (Service data : services) {
            price += data.getPrice();
        }

        if (customer.getMember().getMembershipName().equalsIgnoreCase("silver")) {
            price = price - (price * 0.05);
        } else if (customer.getMember().getMembershipName().equalsIgnoreCase("gold")) {
            price = price - (price * 0.1);
        }

        setReservationPrice(price);
    }
}
