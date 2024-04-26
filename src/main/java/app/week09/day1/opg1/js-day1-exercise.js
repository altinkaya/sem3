// Task 1.1.1
const names = ["Lars", "Peter", "Jan", "Bo"];

// Task 1.1.2
function myFilter(array, callback) {
    const filteredArray = [];
    for (const element of array) {
        if (callback(element)) {
            filteredArray.push(element);
        }
    }
    return filteredArray;
}
const filteredArr = myFilter(names, (string) => string.length <= 3);
console.log(filteredArr);

// Task 1.1.3
function myMap(array, callback) {
    const mappedArray = [];
    for (const element of array) {
        mappedArray.push(callback(element));
    }
    return mappedArray;
}
const mappedArr = myMap(names, (name) => name.toUpperCase());
console.log(mappedArr);

// Task 1.2
Array.prototype.myFilter = function(callback) {
    const filteredArray = [];
    for (const element of this) {
        if (callback(element)) {
            filteredArray.push(element);
        }
    }
    return filteredArray;
};

Array.prototype.myMap = function(callback) {
    const mappedArray = [];
    for (const element of this) {
        mappedArray.push(callback(element));
    }
    return mappedArray;
};
const filteredNames = names.myFilter(name => name.length <= 3);
console.log(filteredNames);
const mappedNames = names.myMap(name => name.toUpperCase());
console.log(mappedNames);

// Task 1.3.1
const divs = document.getElementsByTagName('div');
const colors = ['blue', 'green', 'red'];
for (let i = 0; i < divs.length; i++) {
    divs[i].style.backgroundColor = colors[i];
}

// Task 1.3.2
function getRandomColor() {
return colors[Math.floor(Math.random() * colors.length)];
}

function changeColors() {
    // Get individual divs
    const divs = document.getElementsByTagName('div');

    // Convert HTMLCollection to array and apply random colors to each div
    Array.from(divs).forEach(div => {
        div.style.backgroundColor = getRandomColor();
    });
}
document.getElementById('changeColorButton').addEventListener('click', changeColors);

// Task 1.4.3
function addClickHandlers() {
    const firstDiv = document.getElementById('firstDiv');
    const secondDiv = document.getElementById('secondDiv');
    
    // Add click event listener to firstDiv
    firstDiv.addEventListener('click', function() {
        console.log("Hi from " + this.id);
    });

    // Add click event listener to secondDiv
    secondDiv.addEventListener('click', function() {
        console.log("Hi from " + this.id);
    });
}

// Task 1.4.5
function handleClick1(event) {
    if (event.target.classList.contains('mydiv')) {
        console.log("Hi from " + event.target.id);
    }
}

// Task 1.4.6
function handleClick(event) {
    if (event.target.classList.contains('mydiv')) {
        const output = document.getElementById('output');
        output.innerText = "Hi from " + event.target.id;
    }
}
// Get the outer div element
const outerDiv = document.getElementById('outer');
// Add click event listener to the outer div
outerDiv.addEventListener('click', handleClick);

// Task 1.5.1 + 1.5.2
const arr = ["Ahmad","Lars", "Jan", "Peter", "Bo", "Frederik"];
const form = document.getElementById('nameForm');
const listContainer = document.getElementById('listContainer');
const removeFirstBtn = document.getElementById('removeFirst');
const removeLastBtn = document.getElementById('removeLast');

function addToList(name) {
    const li = document.createElement('li');
    li.textContent = name;
    listContainer.appendChild(li);
}

arr.forEach(function(name) {
    addToList(name);
});

form.addEventListener('submit', function(event) {
    event.preventDefault();
    const nameInput = document.getElementById('nameInput');
    const name = nameInput.value;
    nameInput.value = '';
    addToList(name);
});

// Task 1.5.3
removeFirstBtn.addEventListener('click', function() {
    const firstItem = listContainer.querySelector('li'); // Får fat i den første fra listen
    if (firstItem) {
        firstItem.remove();
    }
});

removeLastBtn.addEventListener('click', function() {
    const lastItem = listContainer.querySelector('li:last-child'); // Får fat i den sidste fra listen
    if (lastItem) {
        lastItem.remove();
    }
});

// Task 1.6.1 + Task 1.6.2
const cars = [
    { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
    { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
    { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
    { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
    { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

// Variable to store the currently filtered cars
let filteredCars = cars;

function generateTableRows(cars) {
    return cars.map(function(car) {
        return `<tr>
                    <td>${car.id}</td>
                    <td>${car.year}</td>
                    <td>${car.make}</td>
                    <td>${car.model}</td>
                    <td>${car.price}</td>
                </tr>`;
    }).join('');
}

function updateTable(cars) {
    const tableContainer = document.getElementById('tableContainer');
    const htmlTable = `
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Year</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                ${generateTableRows(cars)}
            </tbody>
        </table>`;
    tableContainer.innerHTML = htmlTable;
}

updateTable(filteredCars);

document.getElementById('filterButton').addEventListener('click', function() {
    const priceInput = document.getElementById('priceInput').value;
    const price = parseFloat(priceInput);

    filteredCars = cars.filter(function(car) {
        return car.price < price;
    });

    updateTable(filteredCars);
});

// Task 1.7
document.getElementById('buttons').addEventListener('click', function(event) {
    const display = document.getElementById('display');
    const buttonText = event.target.innerText;

    if (buttonText !== '=' && buttonText !== 'C') { 
        display.innerText += buttonText;
    }
});

document.getElementById('calculate').addEventListener('click', function(event) {
    event.stopPropagation(); // Prevent click event from bubbling up

    const display = document.getElementById('display');
    const expression = display.innerText;

    // Evaluate the expression and update the display
    display.innerText = eval(expression);
});

document.getElementById('reset').addEventListener('click', function() {
    const display = document.getElementById('display');
    display.innerText = ''; // Clear the display
});