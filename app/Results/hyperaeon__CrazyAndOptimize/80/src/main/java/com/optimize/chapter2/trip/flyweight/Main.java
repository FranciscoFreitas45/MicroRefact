package com.optimize.chapter2.trip.flyweight;
 public class Main {


public void main(String[] args){
    ReportManagerFactory rmf = new ReportManagerFactory();
    IReportManager rm = rmf.getFinancialReportManager("A");
    System.out.println(rm.createReport());
}


}