package samples.core.model;

import samples.core.model.enums.Gender;


public class Registration {

    private String username;
    private String password;
    private String repeatPassword;

    private Gender gender;
    private Boolean newsletter;
    private Boolean robot;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    public Boolean getRobot() {
        return robot;
    }

    public void setRobot(Boolean robot) {
        this.robot = robot;
    }

}
