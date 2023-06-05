function getPatients(){

    var http = new XMLHttpRequest();

    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    http.open("GET", "http://localhost:3000/Farmacia/ServPatients?mail="+mail+"&session="+session, true);
    http.send();

    http.onreadystatechange = function(){

        if (http.readyState == 4 && http.status == 200){

            var html = "";
            let jsonResponse = JSON.parse(http.responseText); 

            jsonResponse.forEach(element =>
                html += "<option value='"+element+"'>" + element + "</option>"
            );

            document.getElementById("listPatients").innerHTML = html;

        }

    }

}


function getMedicine(){

    var http = new XMLHttpRequest();

    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    http.open("GET", "http://localhost:3000/Farmacia/ServMedicines?mail="+mail+"&session="+session, true);
    http.send();

    http.onreadystatechange = function(){

        if (http.readyState == 4 && http.status == 200){

            var html = "";
            let jsonResponse = JSON.parse(http.responseText);  

            jsonResponse.forEach(element =>
                html += "<option value='"+element.id+"'>" + element.name + "</option>"
            );
        
            document.getElementById("listMedicine").innerHTML = html;
        }

    }

}


function enviar() {
    var http = new XMLHttpRequest();

    // Datos que sacamos del html
    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");
    let idXip = document.getElementById("idXip").value;
    let mailP = document.getElementById("listPatients").value;
    let idMed = document.getElementById("listMedicine").value;
    let date = document.getElementById("fechaLimite").value;


    http.open("POST", "http://localhost:3000/Farmacia/Release", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("mail="+mail+"&session="+session+"&idXip="+idXip+"&mailP="+mailP+"&idMed="+idMed+"&date="+date);
}

function reset() {
    document.getElementById("idXip").value = "";
    document.getElementById("listPatients").value = "";
    document.getElementById("listMedicine").value = "";
    document.getElementById("fechaLimite").value = "";

}