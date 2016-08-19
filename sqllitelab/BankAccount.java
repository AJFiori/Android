package com.example.anthonyjfiori.sqllitelab;

/**
 * Created by Anthony J. Fiori on 8/18/2016.
 */
public class BankAccount {
    //private variables
    int _id;
    String _name;
    String Type;

    // Empty constructor
    public BankAccount(){

    }

    // constructor
    public BankAccount(int id, String name, String Type){
        this._id = id;
        this._name = name;
        this.Type = Type;
    }

    // constructor
    public BankAccount(String name, String Type){
        this._name = name;
        this.Type = Type;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting Type of account
    public String getType(){
        return this.Type;
    }

    // setting Type of account
    public void setType(String Type){
        this.Type = Type;
    }
}

