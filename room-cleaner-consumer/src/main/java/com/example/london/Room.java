package com.example.london;

/**
 * @author Sergii Biliaiev
 * Created on 04/05/2019.
 */
public class Room {
    private long id;
    private String name;
    private String number;
    private String bedInfo;

    public Room() {
    }

    public Room(long id, String name, String number, String bedInfo) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.bedInfo = bedInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", bedInfo='" + bedInfo + '\'' +
                '}';
    }
}
