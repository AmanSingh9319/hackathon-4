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
import java.util.Iterator;
import java.util.List;
// create a ticketDataImpl class.......

public class TicketDataImpl {
    public static void main(String[] args) {

        //create a object ticketDataImpl class....
        TicketDataImpl ticketData = new TicketDataImpl();

        // make a Filename variable String type and provide the CSV file path
        String filename = "src/main/resources/sample.csv";
        List<TicketData> list = ticketData.readFile(filename);
        System.out.println("-----------------------------------------");
        System.out.println(" Befor sorting");
        System.out.println("------------------------------------------");

        // making a foreach loop
        for (TicketData ticket : list) {
            System.out.println(ticket);
        }
        System.out.println("-------------------------------------------");
        System.out.println(" After sorting");
        System.out.println("--------------------------------------------");
        List<TicketData> list1 = ticketData.sortDataByDistance(list);
        for (TicketData ticket : list1) {
            System.out.println(ticket);
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("collection amount = " + ticketData.CollectionAmount(list));
        System.out.println("-----------------------------------------------------------");
    }

    public List<TicketData> readFile(String filename) {
        List<TicketData> ticktes = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] datalist = line.split(",");
                String schedule_no = datalist[0];
                String rout_no = datalist[1];
                int ticket_from_stop_id = Integer.parseInt(datalist[2]);
                int ticket_from_stop_seq_no = Integer.parseInt(datalist[3]);
                int ticket_till_stop_id = Integer.parseInt(datalist[4]);
                int ticket_till_stop_seq_no = Integer.parseInt(datalist[5]);
                String ticket_date = datalist[6];
                String ticket_time = datalist[7];
                double total_ticket_amount = Double.parseDouble(datalist[8]);
                double travelled_KM = Double.parseDouble(datalist[9]);


                ticktes.add(new TicketData(schedule_no, rout_no, ticket_from_stop_id,
                        ticket_from_stop_seq_no
                        , ticket_till_stop_id, ticket_till_stop_seq_no, ticket_date,
                        ticket_time, total_ticket_amount
                        , travelled_KM));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {

        }
        return ticktes;
    }

    public List<TicketData> sortDataByDistance(List<TicketData> tickets) {
        tickets.sort((o1, o2) -> {
            if (o1.getTravelled_KM() == o2.getTravelled_KM()) {
                return 0;
            } else if (o1.getTravelled_KM() < o2.getTravelled_KM()) {
                return 1;
            } else {

                return -1;
            }

        });

        return tickets;
    }


    public double CollectionAmount(List<TicketData> ticketData) {
        CollectionAmountCalculate collectionAmountCalculate = ticketData1 -> {
            int sum = 0;
            Iterator<TicketData> ticketDataIterator = ticketData1.iterator();
            while (ticketDataIterator.hasNext()) {
                TicketData ticketData2 = ticketDataIterator.next();
                sum += ticketData2.getTotal_ticket_amount();
            }

            return sum;
        };

        return collectionAmountCalculate.collectionAmount(ticketData);
    }

}
