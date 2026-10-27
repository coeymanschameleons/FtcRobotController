package org.firstinspires.ftc.teamcode;

/**
 * This is where contact info constants go
 */
public class ContactChameleon {
    protected static String teamName = "Coeymans Chameleons";
    protected static int teamNumber = 32497;
    protected static String copyrightInfo = "Licensed under the BSD 3-Clause Clear License";
    protected static String email = "ravenainnovationstation@gmail.com";

    public static String getTeamName() {
        return teamName;
    }

    public static int getTeamNumber() {
        return teamNumber;
    }

    public static String getCopyrightInfo() {
        return String.format("Copyright (c) 2026 FIRST, Ravena Innovation Station, %s. %s.", teamName, copyrightInfo);
    }

    public static String getEmail() {
        return email;
    }

    public static String getContactInfo(){
        return String.format("%s\nTeam number: %s\nTeam email: %s", getCopyrightInfo(), getTeamNumber(), getEmail());
    }
}
