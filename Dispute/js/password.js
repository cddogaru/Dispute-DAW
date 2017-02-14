$(document).ready(function () {
    $("#pass2").focusout(function () {
        var pass1 = document.getElementById("pass1").value;
        var pass2 = document.getElementById("pass2").value;
        var ok = true;
        console.log('test');
        if (pass1 != pass2) {
            //alert("Passwords Do not match");
            document.getElementById("pass2").style.borderColor = "#E34234";
            ok = false;
        } else {

        }
        return ok;
    });
});
