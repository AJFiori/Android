package com.example.anthonyjfiori.lab3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anthony J. Fiori on 8/3/2016.
 */
public class Order implements Parcelable {
    private String fName, lName,typeOfChocolate;
    private int nofBars;
    private boolean typeOfShipment;

    //Gets Order for Array List
    public Order(String fName,String lName, String typeOfChocolate, int nofBars, boolean typeOfShipment){
        this.fName = fName;
        this.lName = lName;
        this.typeOfChocolate = typeOfChocolate;
        this.nofBars = nofBars;
        this.typeOfShipment = typeOfShipment;
    }

    //Gets & Sets first Name
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    //Gets & Sets last Name
    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    //Gets & Sets Type of Chocolate
    public String getTypeOfChocolate() {
        return typeOfChocolate;
    }

    public void setTypeOfChocolate(String typeOfChocolate) {
        this.typeOfChocolate = typeOfChocolate;
    }

    //Gets & Sets number of bars
    public int getNofBars() {
        return nofBars;
    }

    public void setNofBars(int nofBars) {
        this.nofBars = nofBars;
    }

    //Gets & Sets Type of Shipment
    public boolean isTypeOfShipment() {
        return typeOfShipment;
    }

    public void setTypeOfShipment(boolean typeOfShipment) {
        this.typeOfShipment = typeOfShipment;
    }

    // Parcelling part
    public Order(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.fName = data[0];
        this.lName = data[1];
        this.typeOfChocolate = data[2];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.fName,
                this.lName,
                this.typeOfChocolate});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}

