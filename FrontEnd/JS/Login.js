// Esto es enviarGET
function enviar() {
  var http = new XMLHttpRequest();
  let mail = document.getElementById("mail").value;
  let pass = document.getElementById("pass").value;
  // True indica que es asincrono
  http.open("GET", "http://localhost:3000/Farmacia/Login?mail="+mail+"&pass="+pass, true);
  http.send();
  http.onreadystatechange = function(){
      if (http.readyState == 4 && http.status == 200) {
          let session = http.responseText;
          console.log(this.responseText);
          // Aquí se guarda la respuesta que hacemos con el getWritter de Java
          if (session != "0") {
              window.sessionStorage.setItem("mail", mail);
              window.sessionStorage.setItem("session", session);
              document.getElementById("resultado").innerHTML = "Login correcto";
              irGestion();
          } else {
              document.getElementById("resultado").innerHTML = "<p class='rojo'>Login Incorrecto</p>"
          }
      }
  }
}

function irGestion() {

    window.location.href = "../HTML/Gestion.html";
}

function reset() {
    document.getElementById("mail").value = "";
    document.getElementById("pass").value = "";

}