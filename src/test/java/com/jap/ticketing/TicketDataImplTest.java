package com.jap.ticketing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TicketDataImplTest {
    TicketDataImpl ticketDataImpl = null;
    TicketData ticketData;
    String fileName = "src/main/resources/sample.csv";


    @Before
    public void SetUp() {
        ticketDataImpl = new TicketDataImpl();
    }

    @After
    public void tearDown() {
        ticketDataImpl = null;
        ticketData = null;
    }

    @Test
    public void givenwrongdataNumberFormat() {
        ticketDataImpl.readFile(fileName);
    }

    @Test
    public void readFile() {
        List<TicketData> output = ticketDataImpl.readFile(fileName);
        assertEquals("sales record", 49, output.size());

    }

    @Test
    public void sortDataByDistance() {
        List<TicketData> output = ticketDataImpl.readFile(fileName);
        assertEquals(49.5, ticketDataImpl.sortDataByDistance(output).get(0).getTravelled_KM(), 0);
    }

    @Test
    public void CollectionAmount() {
        List<TicketData> output = ticketDataImpl.readFile(fileName);
        assertEquals(10348.0, ticketDataImpl.CollectionAmount(output), 0);
    }
}