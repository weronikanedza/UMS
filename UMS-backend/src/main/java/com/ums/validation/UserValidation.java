package com.ums.validation;

import com.ums.dto.UserDTO;
import com.ums.exception.OperationException;
import org.springframework.http.HttpStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class UserValidation {

    public static void validate(UserDTO userDTO) throws OperationException{
        isValidDate(userDTO.getDate());
        checkAge(userDTO.getDate());
        correctPassword(userDTO.getPassword());
        passwordConfirmation(userDTO.getPassword(),userDTO.getPasswordConfirm());
    }

    private static void passwordConfirmation(String password, String passwordConfirm) throws OperationException {
        if(!password.equals(passwordConfirm))
            throw new OperationException(HttpStatus.NOT_ACCEPTABLE,"Passwords are not the same");
    }

    private static void isValidDate(String date) throws OperationException{
        if (!date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
            throw new OperationException(HttpStatus.NOT_ACCEPTABLE,"Wrong date format");
    }

    private static void correctPassword(String password) throws OperationException{
        if(!password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}"))
            throw new OperationException(HttpStatus.NOT_ACCEPTABLE,"Password must contain :numer, uppercase letter," +
                    " lowercase letter and 8-16 characters");
    }

    private static void checkAge(String date) throws OperationException{
        DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        Date dateOfBirth=null;

        try {
            dateOfBirth=format.parse(date);
        } catch (ParseException e) {
            throw new OperationException(HttpStatus.NOT_ACCEPTABLE,"Incorrect date of birth.");
        }

        LocalDate today=LocalDate.now();
        LocalDate birthday=dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age=Period.between(birthday, today).getYears();
        if(age<18 || age>100)
            throw new OperationException(HttpStatus.NOT_ACCEPTABLE,"Incorrect date of birth.You have to be adult");
    }
}
