package ua.training.vehicle_fleet.controller;

public interface RegexConstants {

    //check users first name and last name
    String NAME_UK = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']{1,20}$";
    // Latin name
    String NAME_EN = "^[A-Z][a-z]{1,20}$";
    //check login
    String LOGIN = "^[A-Za-z0-9_-]{4,30}";
    //check email address
    String EMAIL = "^(|(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6})$";

}
