package utilities;

import samples.core.model.Registration;
import samples.core.model.User;
import samples.core.model.enums.Gender;


public class TestData {

    public static UserBuilder newValidUser() {
        return new UserBuilder();
    }

    public static RegistrationBuilder newValidRegistration() {
        return new RegistrationBuilder();
    }

    public static class UserBuilder {

        private String username = "username";
        private String password = "123456";

        private UserBuilder() {
            // do direct construction
        }

        public UserBuilder withUsername(String name) {
            this.username = name;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(username, password);
        }

    }

    public static class RegistrationBuilder {

        private String username = "username";
        private String password = "123456";
        private String repeatPassword = "123456";

        private Gender gender = Gender.NONE;
        private boolean newsletter;
        private boolean robot;

        public RegistrationBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public RegistrationBuilder withoutUsername() {
            return withUsername("");
        }

        public RegistrationBuilder withPasswords(String password) {
            return withPassword(password).withRepeatPassword(password);
        }

        public RegistrationBuilder withoutPasswords() {
            return withPasswords("");
        }

        public RegistrationBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public RegistrationBuilder withoutPassword() {
            return withPassword("");
        }

        public RegistrationBuilder withRepeatPassword(String repeatPassword) {
            this.repeatPassword = repeatPassword;
            return this;
        }

        public RegistrationBuilder withoutRepeatPassword() {
            return withRepeatPassword("");
        }

        public RegistrationBuilder with(Gender gender) {
            this.gender = gender;
            return this;
        }

        public RegistrationBuilder withoutGender() {
            return with(Gender.NONE);
        }

        public RegistrationBuilder withNewsletter() {
            this.newsletter = true;
            return this;
        }

        public RegistrationBuilder withoutNewsletter() {
            this.newsletter = false;
            return this;
        }

        public RegistrationBuilder asRobot() {
            this.robot = true;
            return this;
        }

        public RegistrationBuilder asNonRobot() {
            this.robot = false;
            return this;
        }

        public Registration build() {
            Registration registration = new Registration();
            registration.setUsername(username);
            registration.setPassword(password);
            registration.setRepeatPassword(repeatPassword);
            registration.setGender(gender);
            registration.setNewsletter(newsletter);
            registration.setRobot(robot);
            return registration;
        }

    }

}
