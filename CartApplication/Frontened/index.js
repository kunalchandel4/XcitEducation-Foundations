const main = document.getElementById("main");

let append = async () => {
  let user_data = `http://localhost:9090/getProduct`;
  let user_response = await fetch(user_data, {
    method: "GET",
  });
  let data = await user_response.json();
  console.log(data.length);
  showmovie(data);
};
var i = 0;

function showmovie(data) {
  main.innerHTML = null;

  data.forEach((el) => {
    let movieE2 = document.createElement("div");
    let movieE1 = document.createElement("div");
    movieE1.classList.add("movie");
    movieE1.innerHTML = `
      <h4 class="dd">${el.discountPercentage}% off</h4>
              <img  id="image_con" src="${el.images[0]}" alt="${el.title}">
                     
                     
              <div class="movie-info">
                  <h3>Product Name: ${el.title}</h3>
                  <br>
                  <h4>Price: <s>₹${el.price}</s> ₹${Math.floor(
      el.price - (el.price * el.discountPercentage) / 100
    )}</h4>   
                   <br>
                  <h4>Rating : ${el.rating}</h4>
                
                  
             `;
    let div = document.createElement("div");

    div.innerHTML = `
            <button id="btn"onclick="order()">Order</button>
            `;

    movieE2.append(movieE1, div);
    main.appendChild(movieE2);
  });
  item();
}

let order = () => {
  let data = JSON.parse(localStorage.getItem("login"));
  if (data != true) {
    alert("Please login to continue");
  } else {
    window.location.href = "checkout.html";
  }
};

let item = () => {
  const append = document.getElementById("append");
  append.innerText = `Item ${i} added to cart`;
};
