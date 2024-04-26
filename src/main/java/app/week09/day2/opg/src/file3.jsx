import React from 'react';
import {persons} from './file2';

function Welcome(props) {
    return <h1>Hello, {props.name}</h1>;
}

function WelcomePerson(props){
    return (
        <>
        <p>-----</p>
        <h2>Firstname: {props.person.firstName}</h2>
        <h2>Lastname: {props.person.lastName}</h2>
        <h2>Gender: {props.person.gender}</h2>
        <h2>Email: {props.person.email}</h2>
        <h2>Phone: {props.person.phone}</h2>
        </>
    );
}

function MultiWelcome() {
    return (
        <>
            <div>
                <Welcome name="Sara" />
                <Welcome name="Cahal" />
                <Welcome name="Edith" />
                <WelcomePerson person={persons[0]}  />
                <WelcomePerson person={persons[1]}  />
                <WelcomePerson person={persons[2]}  />
                {persons.map((person, index) => (
                    <WelcomePerson key={index} person={person} />
                ))}
            </div>
        </>
    );
}

export default MultiWelcome;