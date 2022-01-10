package com.company.test;

import com.company.entity.Features;
import com.company.entity.MeetingManager;
import com.company.entity.MeetingRoom;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashSet;

public class Testcases {
    MeetingManager meetingManager;
    public Testcases() {
        meetingManager = new MeetingManager();

        EnumSet<Features> features = EnumSet.allOf(Features.class);
        MeetingRoom m1 = new MeetingRoom("11", 4, features);
        meetingManager.addMeetingRoom(m1);
        MeetingRoom m2 = new MeetingRoom("22", 5, features);
        meetingManager.addMeetingRoom(m2);

        meetingManager.addMeetingRoomBooking("12", "11", LocalDate.now(), LocalTime.now(), LocalTime.now().plusMinutes(70));
        meetingManager.addMeetingRoomBooking("13", "11", LocalDate.now(), LocalTime.now().plusMinutes(100), LocalTime.now().plusMinutes(170));

        System.out.println(meetingManager);

        System.out.println("\n\n");

        System.out.println(meetingManager.search(LocalDate.now()));

        System.out.println(meetingManager.search(LocalDate.now(), LocalTime.now().plusMinutes(200), LocalTime.now().plusMinutes(250)));

        meetingManager.cancelBooking("12");
        meetingManager.cancelBooking("13");
        System.out.println(meetingManager.search(LocalDate.now()));

    }

    @Test
    public void test1()
    {
        System.out.println("Running testcases");
    }
}
