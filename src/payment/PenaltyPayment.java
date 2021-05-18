/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import entities.Rent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import model.BankSoftwareSimulation;
import utilityClasses.XpressMachineConstantes;

/**
 *
 * @author raelg
 */
public class PenaltyPayment implements Payment {

    // charge penalty for late devolution
    private LocalTime finishDayTime = LocalTime.of(20, 00);

    @Override
    public boolean pay(Rent rent) {
        LocalDate duoDate = rent.getRentDate().plusDays(XpressMachineConstantes.MAX_RENT_DAY);
        LocalDate returnDay = LocalDate.now();
        int rentalTime = (int) ChronoUnit.DAYS.between(duoDate, returnDay);

        if (LocalTime.now().isAfter(finishDayTime)) {
            rentalTime++;
        }
        double chargeValue = rentalTime * FINE_VALUE;

        return BankSoftwareSimulation.receiveFromClient(chargeValue);
    }

}
