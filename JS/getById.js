const formulario = document.querySelector("form");
const botao = document.querySelector("button");
const id = document.querySelector("#id_rifa");

function ObterRifasPorID(ID) {
  let find = false;
  let log = document.getElementById("log");
  fetch(`http://localhost:8080/Rifas/${ID}`, {
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    method: "GET",
  })
    .then(function (res) {
      if (res.ok) {
        find = true;
        log.innerHTML = "";
        return res.json();
      } else {
        console.log("Erro ao obter a rifa");
        log.style.color = "red";
        log.innerHTML = "Rifa não encontrada";
      }
    })
    .then(function (rifa) {
      if (find) {
        let table = document.getElementById("table");
        let row = `<tr>
                          <td>${rifa.rifaNumber}</td>
                          <td>${rifa.rifaNome}</td>
                          <td>${rifa.rifaDataSorteio}</td>
                          <td>R$ ${rifa.rifaPreco}</td>
                     </tr>`;
        table.innerHTML += row;
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
  ObterRifasPorID(id.value);
  limpar();
});
