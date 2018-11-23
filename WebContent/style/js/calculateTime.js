function countDown(endDate,id) {
    // Set the date counting down to
    var end = new Date(endDate).getTime();
    var diff;
    // Update the count down every 1 second
    var x = setInterval(function() {

        // Get todays date and time
        var now = new Date().getTime();

        // Find the distance between now an the count down date
        var timeDiffernce = end - now;

        // Time calculations for days, hours, minutes and seconds
        var days = Math.floor(timeDiffernce / (1000 * 60 * 60 * 24));
        var hours = Math.floor((timeDiffernce % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((timeDiffernce % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((timeDiffernce % (1000 * 60)) / 1000);

        //set output
        diff = days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
        if (timeDiffernce < 0) {
            clearInterval(x);
            if(id == "available"){
            	document.getElementById(id).innerHTML = "Ended ("+endDate+")";
            }else{
            	document.getElementById(id).innerHTML = "Ended";
            }
            /*if product count down 0 and id is available then bid function is disabled*/
            if(id == "available")
            {
            	document.getElementById("placeBid").disabled = true;
            	document.getElementById("bidPrice").disabled = true;
            	var span1 = document.getElementById("qty-up");
                span1.parentNode.removeChild(span1);
                var span2 = document.getElementById("qty-down");
                span2.parentNode.removeChild(span2);
            }
        } else {
            document.getElementById(id).innerHTML = diff;
        }
    }, 1000);
}