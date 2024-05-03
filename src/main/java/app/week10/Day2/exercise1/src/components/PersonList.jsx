import React, { useEffect,useState } from 'react'

const PersonList = ({updated,setUpdated, person, setPerson, handleUpdatePerson}) => {
  const [persons, setPersons] = useState([]);

  useEffect(() => {
    fetch("http://localhost:3001/persons")
    .then(response => response.json())
    .then(data => setPersons(data))
  }, [updated])

  const handleDelete = (e) => {
    fetch("http://localhost:3001/persons/" + e.target.id, {
        method: "DELETE"
    }).then(() => {
            setUpdated(!updated);
    })
  }

  const handleUpdate = (person) => {
    handleUpdatePerson(person);
  };

  return (
    
    <div className="row justify-content-center">
      <h3>Table of persons</h3>
      <table className="table">
        <thead>
          <tr>
            <th><span style={{ color: 'black' }}>ID</span></th>
            <th><span style={{ color: 'pink' }}>Age</span></th>
            <th><span style={{ color: 'green' }}>Fullname</span></th>
            <th><span style={{ color: 'blue' }}>Gender</span></th>
            <th><span style={{ color: 'lightblue' }}>Email</span></th>
            <th><span style={{ color: 'red' }}>Delete</span></th>
            <th><span style={{ color: 'blue' }}>Update</span></th>
          </tr>
        </thead>
        <tbody>
          {persons.map((person) => (
            <tr key={person.id}>
              <td style={{ color: 'black' }}>{person.id}</td>
              <td style={{ color: 'red' }}>{person.age}</td>
              <td style={{ color: 'green' }}>{person.fullname}</td>
              <td style={{ color: 'blue' }}>{person.gender}</td>
              <td style={{ color: 'lightblue' }}>{person.email}</td>
              <td><button className="btn btn-danger" id={person.id} onClick={handleDelete}>Delete person</button></td>
              <td><button className="btn btn-info" onClick={() => handleUpdate(person)}>Update person</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

{/*row justify-content-center sørger for at ens element er i midten*/}
{/*table giver ens tabel charme*/}
{/*btn btn-danger gør ens knap rød*/}
{/*btn btn-info gør ens knap blå*/}

export default PersonList
