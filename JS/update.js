const formulario = document.querySelector("form");
const botao = document.querySelector("button");

const numRifa = document.querySelector("#num_rifa");
const nomeRifa = document.querySelector("#nome_rifa");
const dataSorteio = document.querySelector("#data_sorteio");
const preco_numero = document.querySelector("#preco_numero");
const id = document.querySelector("#id_rifa");

function AtualizarRifa(ID) {
  fetch(`http://localhost:8080/Rifas/${ID}`, {
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    method: "PUT",
    body: JSON.stringify({
      rifaNumber: numRifa.value,
      rifaNome: nomeRifa.value,
      rifaDataSorteio: dataSorteio.value,
      rifaPreco: preco_numero.value,
    }),
  })
    .then(function (res) {
      let log = document.getElementById("log");
      if (res.ok) {
        log.style.color = "black";
        console.log("Rifa atualizada com sucesso.");
        log.innerHTML = `Rifa atualizada com sucesso.`;
      } else {
        console.log("Erro ao atualizar a rifa.");
        log.style.color = "red";
        log.innerHTML = `Erro ao atualizar a rifa`;
      }
    })
    .catch(function (err) {
      console.log(err);
    });
}

function limpar() {
  numRifa.value = "";
  nomeRifa.value = "";
  dataSorteio.value = "";
  preco_numero.value = "";
}

formulario.addEventListener("submit", function (event) {
  event.preventDefault();
  AtualizarRifa(id.value);
  limpar();
});
