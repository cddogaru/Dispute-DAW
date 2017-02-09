window.onload = function () {
    var estado = false;
    document.getElementById('darkMode').onclick = function () {
        if (estado) {
            var content = document.getElementById('darkLight').innerHTML = '';
            estado = false;
        } else {
            var content = document.getElementById('darkLight').innerHTML = '<link href="css/style.css" rel="stylesheet">';
            estado = true;
        }
    }
}
