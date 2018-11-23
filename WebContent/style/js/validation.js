function check() {
    var n = document.forms["register"]["fname"];
    var n2 = document.forms["register"]["lname"];


    if(check1(n))
        if(check2(n2))
            return true;
        else
            return false;
    else
        return false;

}





function check1(n) {
    if(n.value == "") {

        alert("Enter name");
        document.getElementById("fname").style.borderColor = "red";
        return false;
    }
}

function check2(n2) {
    if(n2.value == "") {

        alert("Enter last name");
        document.getElementById("lname").style.borderColor = "red";
        return false;
    }
}