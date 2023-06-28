const formulario = document.querySelector("form");
const botao = document.querySelector("button");
const id = document.querySelector("#id_rifa");

function DeletarRifa(ID) {
  let log = document.getElementById("log");
  fetch(`http://localhost:8080/Rifas/${ID}`, {
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    method: "DELETE",
  })
    .then(function (res) {
      if (res.ok) {
        log.style.color = "black";
        log.innerHTML = "Rifa deletada com sucesso!";
        return res.json();
      } else {
        console.log("Erro ao obter a rifa");
        log.style.color = "red";
        log.innerHTML = "Rifa não encontrada";
      }
    })
    .catch(function (err) {
      console.log(err);
    });
}

function limpar() {
  id.value = "";
}

formulario.addEventListener("submit", function (event) {
  event.preventDefault();
  DeletarRifa(id.value);
  limpar();
});
