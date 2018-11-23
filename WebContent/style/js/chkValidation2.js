function formValidate() {

    var number = document.forms["payment"]["number"].value;
    var month = document.forms["payment"]["month"].value;
    var year = document.forms["payment"]["year"].value;
    var code = document.forms["payment"]["code"].value;

    //Card Numnber validations
    if (number == "") {

        alert("Plaese Enter your Card Number");
        return false;
    }
    if (isNaN(number)) {

            alert("Enter Only Numbers!");
            return false;
    }
    if (number.length != 16) {

        alert("Number must have 16 digits");
        return false;
    }

    // Month Validations
    if (month == "") {

        alert("Month Empty!");
        return false;
    }
    if(isNaN(month)){

        alert("Enter Only Numbers!");
        return false;
    }
    if (month.length != 2) {

        alert("Please Enter Standard Type Ex :- 01,05,12!!");
        return false;
    }
    if(month < 1 || month > 12){
        alert("Invalid Month!");
        return false;
    }
    //Year Validation
    if(year == "") {

        alert("Year Can't Be Empty!");
        return false;
    }
    if(isNaN(year)){

        alert("Enter Only Numbers!");
        return false;
    }
    if (year.length != 2 ) {

        alert("Please Enter Standard Type Ex :- 01,05,12!!");
        return false;
    }

    //Code Validation
    if(code == "") {

        alert("Please Enter Security Code!");
        return false;
    }
    if(isNaN(code)){

        alert("Enter Only Numbers!");
        return false;
    }
    if (code.length != 3 ) {
        alert("Invalid Code!");
        return false;
    }

}

