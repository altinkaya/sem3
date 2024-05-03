import casual from 'casual';

// Create an object for config file
let db = { persons: [] };

for (let i = 1; i <= 200; i++) {
    let person = {};

    // ID
    person.id = i;

    // Age between 0 and 100
    person.age = casual.integer(0, 100);

    // Name
    person.fullname = casual.first_name + ' ' + casual.last_name;

    // Gender
    person.gender = casual.random_element(['Man', 'Woman', 'Nonbinary']);

    // Email
    person.email = casual.email;

    // Add the person to the persons array
    db.persons.push(person);
}

console.log(JSON.stringify(db));