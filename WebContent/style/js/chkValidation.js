function formValidate() {

    var number = document.forms["payment"]["number"].value;
    var month = document.forms["payment"]["month"].value;
    var year = document.forms["payment"]["year"].value;
    var code = document.forms["payment"]["code"].value;

    if(isEmpty(number))
        if(isEmpty(month))
            if(isEmpty(year))
                 if(isEmpty(code))
                     // if(isNumeric(number))
                     //     if(isNumeric(month))
                     //         if(isNumeric(year))
                     //             if(isNumeric(code))
                     //                 if(cardValidation(number))
                     //                     if(monthValidation(month))
                     //                         if(yearValidation(year))
                     //                             if(codeValidation(code))
                                                     return true;
                                                    else
                                                        return false;
                                             else
                                                 return false;
                                         else
                                             return false;
                                     else
                                         return false;
    //                              else
    //                                  return false;
    //                          else
    //                              return false;
    //                      else
    //                          return false;
    //                  else
    //                      return false;
    //              else
    //                  return false;
    //         else
    //             return false;
    //     else
    //         return false;
    // else
    //     return false;


}

function isEmpty(elemValue,field) {
    if(elemValue == "" || elemValue == null){

        alert("You cannot have " + field + " field empty");
    }
    else
        return false;

//
// function isNumeric(elemValue,field) {
//     var exp = /^[0-9]+$/;
//     if(!isEmpty(elemValue,field))
//     {
//         if(elemValue.match(exp))
//         {
//             return true;
//         }
//         else
//         {
//             alert("Enter Numbers Only");
//             return false;
//         }
//     }
//     else
//     {
//         return false;
//     }
// }
//
// function cardValidation(elemValue) {
//     if(!isEmpty(elemValue,"number")){
//
//         if(elemValue.length > 15 && elemValue.length <= 16 ) {
//
//             return true;
//         }
//         else{
//
//             alert("Invalid Card Number");
//             return false;
//         }
//
//     }
//
// }
//
// function monthValidation(elemValue) {
//     if(!isEmpty(elemValue,"month")){
//
//         if (elemValue > 0 && elemValue <= 12){
//
//             return true;
//         }
//         else {
//
//             alert("Enter valid month");
//             return false;
//         }
//     }
// }
//
// function yearValidation(elemValue) {
//     if(!isEmpty(elemValue,"year")){
//
//         if(elemValue.length == 2){
//             return true;
//         }
//         else {
//
//             alert("Enter Valid Year");
//             return false;
//         }
//     }
// }
//
// function codeValidation(elemValue) {
//     if (!isEmpty(elemValue,"code")){
//
//         if(elemValue.length > 2 && elemValue.length <= 3){
//
//             return true;
//         }
//     }
//     else{
//         alert("Enter Valid Security Code");
//         return false;
//
//     }

}