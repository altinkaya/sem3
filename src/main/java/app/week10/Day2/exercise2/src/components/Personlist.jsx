import React, {useEffect, useState} from 'react'

const Personlist = ({update,setUpdate,person,setPerson}) => {
    const [persons,setPersons] = useState([]);
    
    
    useEffect(()=>{
        fetch("http://localhost:3001/persons")
        .then(response => response.json())
        .then(data => setPersons(data))
    },[update])
    const handleDelete = (event) => {
        fetch("http://localhost:3001/persons/" + event.target.id,{
            method: "DELETE"
        }).then(()=>{
            setUpdate(!update);
        })
    }
    const handleEdit = (person) => {
        setPerson(person);

    }
  return (
    <>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Age</th>
                <th>Fullname</th>
                <th>Gender</th>
                <th>Email</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
        </thead>
        <tbody>
            {persons.map((person) =>(
            <tr key = {person.id}>
            <td>Id: {person.id}</td>
            <td>Age: {person.age}</td>
            <td>Fullname: {person.fullname}</td>
            <td>Gender: {person.gender}</td>
            <td>Email: {person.email}</td>
            <td><button id={person.id} onClick={handleDelete}>Delete</button></td>
            <td><button onClick={() => handleEdit(person)}>Edit</button></td>
            </tr>
            ))}
        </tbody>
    </table>
    
    </>

  )
}

export default Personlist
