function irAlta() {
    window.location.href = "../HTML/Alta.html";
}

function logOut() {

    // Borramos el mail y la session de sessionStorage
    sessionStorage.clear

    // Lo redireccionamos al login
    window.location.href = "../HTML/Login.html"
}

function getTable() {

    var http = new XMLHttpRequest();

    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    http.open("GET", "http://localhost:3000/Farmacia/ServXips?mail="+mail+"&session="+session, true);
    http.send();

    http.onreadystatechange = function(){

        if (http.readyState == 4 && http.status == 200) {

            let tabla = http.responseText;
            
            document.getElementById("tabla").innerHTML = tabla;

        }
    }
}