import React, { useState } from 'react'

const PersonSearch = () => {
    const [searchPerson,setSearchPerson] = useState('');
    const [personFound,setPersonFound] = useState(null);
    const handleReadPerson = (event) => {
        event.preventDefault();
        fetch(`http://localhost:3001/persons/${searchPerson}`)
        .then((response) => 
        response.json()
        ) 
        .then(data => (
            setPersonFound(data)
        ))
    }
  return (
    <>
    <form>
        <input type='number' value={searchPerson} placeholder='Search person' onChange={(event) => setSearchPerson(event.target.value)}/>

        <button type='button' onClick={handleReadPerson}>Search</button>
    </form>
    {personFound && (
        <>
        
        <p>Id: {personFound.id}</p>
        <p>Age: {personFound.age}</p>
        <p>Fullname: {personFound.fullname}</p>
        <p>Gender: {personFound.gender}</p>
        <p>Email: {personFound.email}</p>

        </>

    )}
      
    </>
  )
}

export default PersonSearch
