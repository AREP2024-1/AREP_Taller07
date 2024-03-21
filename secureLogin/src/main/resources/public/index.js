function cadenaLog(){
    let inputUserName = document.getElementById("username");
    let inputPassword = document.getElementById("password");
    let endPoint = "/login";
    let peticion = solicitarEndPoint(endPoint, {
        username: inputUserName.value,
        password: inputPassword.value
    });
    peticion.then(respuesta =>{
        let divResLogin = document.getElementById("resLogin");
        
        let resp = new Boolean(respuesta);
        divResLogin.innerHTML = (resp)?"Bienvenido 😊 " + inputUserName.value :"Usuario o contraseña incorrectos 😔";
    })
}

async function solicitarEndPoint(endPoint, body){
    return await fetch(window.location.origin + endPoint, {
        headers: {
            "Content-Type": "application/json"
        },
        method: "POST",
        body: body
    })
    .then(respuesta => {
        if(!respuesta.ok){
            throw new Error();
        }
        return respuesta.text();
    })
    .catch(error => alert(error));
}