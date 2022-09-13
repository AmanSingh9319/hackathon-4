/*
 *Author Name: Aman Singh
 *Date:13-09-2022
 *Created With: Intellij IDEA Community Edition
 */
package com.jap.ticketing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketDataImpl {
    public static void main(String[] args) {
        TicketDataImpl ticketData = new TicketDataImpl();
        String filename = "src/main/resources/sample.csv";

    }

    public List<TicketData> readFile(String filename) {
        List<TicketData> ticktes = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                String schedule_no = strings[0];
                String rout_no = strings[1];
                int ticket_from_stop_id = Integer.parseInt(strings[2]);
                int ticket_from_stop_seq_no = Integer.parseInt(strings[3]);
                int ticket_till_stop_id = Integer.parseInt(strings[4]);
                int ticket_till_stop_seq_no = Integer.parseInt(strings[5]);
                String ticket_date = strings[6];
                String ticket_time = strings[7];
                double total_ticket_amount = Double.parseDouble(strings[8]);
                double travelled_KM = Double.parseDouble(strings[9]);


                ticktes.add(new TicketData(schedule_no, rout_no, ticket_from_stop_id, ticket_from_stop_seq_no
                        , ticket_till_stop_id, ticket_till_stop_seq_no, ticket_date, ticket_time, total_ticket_amount
                        , travelled_KM));

            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return ticktes;
    }

}
