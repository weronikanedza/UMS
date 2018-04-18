function validation(user){
    if(isValidDate(user.date) && correctAge(user.date) && correctPassword(user.password)
                              && passwordConfirmation(user.password,user.passwordConfirm)) 
         return true;
    else
         return false;
}

function isValidDate(date){
    const pattern =/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;

    if(pattern.test(date)) return true;
    else{
        showErrorMessage("Wrong date format ex.DD/MM/YYYY");
        return false;
    }
}

function passwordConfirmation(pwd,pwdConfirm){
    if(pwd===pwdConfirm) return true;
    else{
        showErrorMessage("Passwords are not the same");
        return false;
    }
}

function correctPassword(password){
    const pattern=/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}/;
    if(pattern.test(password)) return true;
    else{
        showErrorMessage("Password must contain :numer, uppercase letter, lowercase letter and 8-16 characters");
        return false;
    }
}

function correctAge(date){
    const parts=date.split('/');
    const dateOfBirth=new Date(parts[2],parts[1]-1,parts[0]);
    if(calculateAge(dateOfBirth)>18 || calculateAge(dateOfBirth)<100)  return true;
    else {
        showErrorMessage("Incorrect date of birth. You have to be adult.")
        return false;
    }
}

function calculateAge(dateOfBirth){
    var today=new Date();
    var age=today.getFullYear()-dateOfBirth.getFullYear();
    var month=today.getMonth()-dateOfBirth.getMonth();
    if (month < 0 || (month === 0 && today.getDate() < dateOfBirth.getDate())) {
        age--;
    }
    return age;
}