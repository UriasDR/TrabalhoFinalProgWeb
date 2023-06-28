const formulario = document.querySelector("form");
const botao = document.querySelector("button");

ObterRifas();
function ObterRifas() {
  fetch("http://localhost:8080/Rifas", {
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    method: "GET",
  })
    .then(function (res) {
      return res.json();
    })
    .then(function (data) {
      let table = document.getElementById("table");
      let rifa = data.content;
      for (let i = 0; i < rifa.length; i++) {
        let row = `<tr>
                        <td>${rifa[i].rifaNumber}</td>
                        <td>${rifa[i].rifaNome}</td>
                        <td>${rifa[i].rifaDataSorteio}</td>
                        <td>R$ ${rifa[i].rifaPreco}</td>
                   </tr>`;
        table.innerHTML += row;
      }
    })
    .catch(function (err) {
      console.log(err);
    });
}
