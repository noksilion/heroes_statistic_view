function test() {
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.open("GET", "http://localhost:8080/castles/get_all", true);
    xhr.responseType = "json";
    xhr.onload = function() {
        console.log(xhr.response);
    };
    xhr.send();
}