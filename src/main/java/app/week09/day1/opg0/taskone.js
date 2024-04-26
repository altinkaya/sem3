// Task 0.1
const arr = ["Ahmad","Lars", "Jan", "Peter", "Bo", "Frederik"];
const filteredArr1 = arr.filter(string => string.length <= 3);
console.log("Original array:");
for (const string of arr){
    console.log(string);
}

console.log("Filtered array with names shorter than length 3:");
for (const string of filteredArr1){
    console.log(string);
}

// Task 0.2
console.log("Filtered array with names all uppercase:");
const filteredArr2 = arr.map((name)=>{
    return name.toUpperCase();
});
for (const string of filteredArr2){
    console.log(string);
}

// Task 0.3
console.log("Filtered HTML string:");
const liArr = arr.map ( (name) => {
    return "<li>"+name+"</li>";
});
const htmlString = "<ul>" + liArr.join('') + "</ul>";
console.log(htmlString);

// Task 0.4
const cars = [
    { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
    { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
    { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
    { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
    { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];
console.log("Cars newer than 1999:");
const filteredArr3 = cars.filter(car => car.year >= 1999);
for (const car of filteredArr3){
    console.log(car);
}
console.log("Only volvo cars:");
const filteredArr4 = cars.filter(car => car.make === 'Volvo');
for (const car of filteredArr4){
    console.log(car);
}
console.log("Cars with price below 5000:");
const filteredArr5 = cars.filter(car => car.price < 5000);
for (const car of filteredArr5){
    console.log(car);
}
console.log("SQL For Function:");
function generateSQLInsert(cars) {
    const sqlStatements = cars.map(car => {
        return `INSERT INTO cars (id, year, make, model, price) VALUES (${car.id}, ${car.year}, '${car.make}', '${car.model}', ${car.price});`;
    });
    return sqlStatements.join('\n');
}
const sqlInsert = generateSQLInsert(cars);
console.log(sqlInsert);