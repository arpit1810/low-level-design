package com.company.entity;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

public class MeetingManager {
    HashMap<String, MeetingRoom> meetingRooms;
    HashMap<String, MeetingRoomBooking> meetingRoomBookings;
    HashMap<LocalDate, List<String>>  dateBookings;

    public MeetingManager() {
        meetingRooms = new HashMap<>();
        meetingRoomBookings = new HashMap<>();
        dateBookings = new HashMap<>();
    }

    public void addMeetingRoom(MeetingRoom meetingRoom)
    {
        meetingRooms.put(meetingRoom.getId(), meetingRoom);
    }

    public void addMeetingRoomBooking(String bookingId, String meetingRoomId, LocalDate date, LocalTime startTime, LocalTime endTime)
    {
        meetingRoomBookings.put(bookingId, new MeetingRoomBooking(bookingId, date, startTime, endTime, meetingRoomId,MeetingStatus.Scheduled));
        dateBookings.putIfAbsent(date, new ArrayList<>());
        dateBookings.get(date).add(bookingId);
    }

    public Set<String> search(LocalDate date)
    {
        HashSet<String> occupiedRooms = new HashSet<>();
        Set<String> allRooms = new HashSet<>(meetingRooms.keySet());

        if(dateBookings.get(date) == null)
        {
            return allRooms;
        }

        for(String bookingId : dateBookings.get(date))
        {
            if(meetingRoomBookings.get(bookingId).getMeetingStatus() == MeetingStatus.Cancelled)
                continue;
            occupiedRooms.add(meetingRoomBookings.get(bookingId).getMeetingRoomId());
        }

        allRooms.removeAll(occupiedRooms);
        return allRooms;
    }

    public Set<String> search(LocalDate date, LocalTime startTime, LocalTime endTime)
    {
        HashSet<String> occupiedRooms = new HashSet<>();
        Set<String> allRooms = meetingRooms.keySet();

        System.out.println(startTime);
        System.out.println(endTime);
        if(dateBookings.get(date) == null)
        {
            return allRooms;
        }

        for(String bookingId : dateBookings.get(date))
        {
            if(meetingRoomBookings.get(bookingId).getMeetingStatus() == MeetingStatus.Cancelled)
                continue;

          //  System.out.println(meetingRoomBookings.get(bookingId));

            //System.out.println((startTime.isAfter(meetingRoomBookings.get(bookingId).getStartTime()) &&
             //       startTime.isBefore(meetingRoomBookings.get(bookingId).getEndTime())));
            //System.out.println((endTime.isAfter(meetingRoomBookings.get(bookingId).getStartTime()) &&
            //        endTime.isBefore(meetingRoomBookings.get(bookingId).getEndTime())));

            if((startTime.isAfter(meetingRoomBookings.get(bookingId).getStartTime()) &&
                    startTime.isBefore(meetingRoomBookings.get(bookingId).getEndTime())) || (endTime.isAfter(meetingRoomBookings.get(bookingId).getStartTime()) &&
                    endTime.isBefore(meetingRoomBookings.get(bookingId).getEndTime())))
                occupiedRooms.add(meetingRoomBookings.get(bookingId).getMeetingRoomId());
        }

        //System.out.println(occupiedRooms);
        //System.out.println(meetingRooms);
        allRooms.removeAll(occupiedRooms);
        return allRooms;
    }

    public Set<String> search(LocalDate date, LocalTime startTime, LocalTime endTime, Features features)
    {
        Set<String> allRooms = search(date, startTime, endTime);
        HashSet<String> newRooms = new HashSet<>();
        for (String room : allRooms)
        {
            if(meetingRooms.get(room).getFeatures().contains(features))
                newRooms.add(room);
        }
        return newRooms;
    }


    public void cancelBooking(String bookingId)
    {
        meetingRoomBookings.get(bookingId).setMeetingStatus(MeetingStatus.Cancelled);
    }


    public void updateBooking(String bookingId, LocalTime startTime, LocalTime endTime)
    {
        MeetingRoomBooking meetingRoomBooking = meetingRoomBookings.get(bookingId);
        cancelBooking(bookingId);
        Set<String> allRooms = search(meetingRoomBooking.getDate(), startTime, endTime);
        if(allRooms.contains(meetingRoomBooking.getMeetingRoomId()))
        {
            meetingRoomBooking.setStartTime(startTime);
            meetingRoomBooking.setEndTime(endTime);
            System.out.println("Update timings");
        }
        else
        {
            System.out.println("Not possible to update timings");
        }
        meetingRoomBooking.setMeetingStatus(MeetingStatus.Scheduled);
    }

    @Override
    public String toString() {
        return "MeetingManager{" +
                "meetingRooms=" + meetingRooms +
                ", meetingRoomBookings=" + meetingRoomBookings +
                ", dateBookings=" + dateBookings +
                '}';
    }
}
