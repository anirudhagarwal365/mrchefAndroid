package com.mrchef.login;

/**
 * Created by PrashantKumar on 7/10/17.
 */

public class User {
  public String empId;
  public String name;
  public String dateJoined;

  public User(String empId, String name, String dateJoined) {
    this.empId = empId;
    this.name = name;
    this.dateJoined = dateJoined;
  }
}
