package com.example.helloworld.resources;

/**
 * Created by vbathula on 10/1/16.
 */

public class Mobile {

    String deviceName;
    String modelName;
    String imeiNumber;
    String firmwareVersion;
    String status;
    String employeeNumber;
    String issuedDate;
    String damageDescription;


    public Mobile() {

    }

    public Mobile(String deviceName, String modelName, String imeiNumber, String firmwareVersion, String status, String employeeNumber, String issuedDate, String damageDescription) {
        this.deviceName = deviceName;
        this.modelName = modelName;
        this.firmwareVersion = firmwareVersion;
        this.imeiNumber = imeiNumber;
        this.status = status;
        this.employeeNumber = employeeNumber;
        this.issuedDate = issuedDate;
        this.damageDescription = damageDescription;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getModelName() {
        return modelName;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public String getImeiNumber() {
        return imeiNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public void setImeiNumber(String imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    @Override
    public String toString() {

        return getDeviceName() + "," + getModelName() + "," + getImeiNumber() + "," +getFirmwareVersion() + "," + getEmployeeNumber() + "," + getIssuedDate() + getDamageDescription() + "\n";
    }
}
