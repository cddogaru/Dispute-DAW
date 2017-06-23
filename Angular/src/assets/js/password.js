$(document).ready(function () {
    $('#name1').keyup(function () {
        $('#name1').css("borderColor", "green");
        $('#name1').css("borderWidth", "0.2em");
        $(".name2").css("color", "#80ff70");
    });
    $('#username1').keyup(function () {
        $('#username1').css("borderColor", "green");
        $('#username1').css("borderWidth", "0.2em");
        $('.username2').css("color", "#80ff70");
    });
    $('#pass1').keyup(function () {
        var secur = 0;
        if (($('#pass1').val().length < 8)) {
            secur = 0;
        } else {
            if (($('#pass1').val() != $('#pass1').val().toLowerCase()) || (/\d/.test($('#pass1').val()))) {
                secur = 1;
            }
            if (($('#pass1').val() != $('#pass1').val().toLowerCase()) && (/\d/.test($('#pass1').val()))) {
                secur = 2;
            }
        }

        if (secur === 0) {
            $('#pass1').css("borderColor", "red");
            $('#pass1').css("borderWidth", "0.2em");
            $('.pass12').css("color", "#f73134");
        } else if (secur === 1) {
            $('#pass1').css("borderColor", "orange");
            $('#pass1').css("borderWidth", "0.2em");
            $('.pass12').css("color", "#f4f73b");
        } else {
            $('#pass1').css("borderColor", "green");
            $('#pass1').css("borderWidth", "0.2em");
            $('.pass12').css("color", "#80ff70");
        }
    });
    $('#pass2').keyup(function () {
        if (($('#pass1').val()) === ($('#pass2').val())) {
            $('#pass2').css("borderColor", "green");
            $('#pass2').css("borderWidth", "0.2em");
            $('.pass22').css("color", "#80ff70");
        } else {
            $('#pass2').css("borderColor", "red");
            $('#pass2').css("borderWidth", "0.2em");
            $('.pass22').css("color", "#f73134");
        }
    });
    $('#email1').keyup(function () {
        var ismail = false;
        if (($('#email1').val().includes("@")) && (($('#email1').val().includes(".")))) {
            ismail = true;
        }
        if (ismail === true) {
            $('#email1').css("borderColor", "green");
            $('#email1').css("borderWidth", "0.2em");
            $('.email2').css("color", "#80ff70");
        } else {
            $('#email1').css("borderColor", "red");
            $('#email1').css("borderWidth", "0.2em");
            $('.email2').css("color", "#f73134");
        }
    });
});
