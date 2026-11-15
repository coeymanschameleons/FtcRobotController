package org.firstinspires.ftc.teamcode;

/**
 * This is where contact info constants go
 */
public class ContactChameleon {
    protected static String teamName = "Coeymans Chameleons";
    protected static int teamNumber = 32497;
    protected static String copyrightInfo = "Licensed under the GNU Affero General Public License version 3";
    protected static String email = "ravenainnovationstation@gmail.com";

    ContactChameleon(){}

    public String getTeamName() {
        return teamName;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getCopyrightInfo() {
        return String.format("Copyright (c) 2026 FIRST, Ravena Innovation Station, %s. %s.", this.getTeamName(), copyrightInfo);
    }

    public String getEmail() {
        return email;
    }

    public String getContactInfo(){
        return String.format("%s\nTeam number: %s\nTeam email: %s", this.getCopyrightInfo(), this.getTeamNumber(), this.getEmail());
    }

}
