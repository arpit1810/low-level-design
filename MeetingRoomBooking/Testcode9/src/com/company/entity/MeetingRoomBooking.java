package com.company.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class MeetingRoomBooking {
    String bookingId;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    String meetingRoomId;
    MeetingStatus meetingStatus;

    public MeetingRoomBooking(String bookingId, LocalDate date, LocalTime startTime, LocalTime endTime, String meetingRoomId, MeetingStatus meetingStatus) {
        this.bookingId = bookingId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingRoomId = meetingRoomId;
        this.meetingStatus = meetingStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(String meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public MeetingStatus getMeetingStatus() {
        return meetingStatus;
    }

    public void setMeetingStatus(MeetingStatus meetingStatus) {
        this.meetingStatus = meetingStatus;
    }

    @Override
    public String toString() {
        return "MeetingRoomBooking{" +
                "bookingId='" + bookingId + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", meetingRoomId='" + meetingRoomId + '\'' +
                ", meetingStatus=" + meetingStatus +
                '}';
    }
}
