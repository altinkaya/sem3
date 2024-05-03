import React, { useState } from 'react'

const PersonInputForm = ({update,setUpdate,person,setPerson}) => {
    const isEditing = person.id !== undefined;
   
    const handleChange = (event) => {
        setPerson({...person, [event.target.id]:event.target.value});

    };
    const handleSubmit = (event) => {
        event.preventDefault();
        const url = isEditing ? `http://localhost:3001/persons/${person.id}` : 'http://localhost:3001/persons';

        fetch(url, {
            method: isEditing ? 'PUT' : 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(person)
        })
        .then(() => {
            setPerson({
                age: '',
                fullname: '',
                gender: '',
                email: ''
            })
            setUpdate(!update)
        })

    } ;
  return (
    <>

        <form onSubmit={handleSubmit}>
            <input type='number' id='age' value={person.age} placeholder='enter age' onChange={handleChange}/>
            <input type='text' id='fullname' value={person.fullname} placeholder='enter fullname' onChange={handleChange}/>

            <select id='gender' value={person.gender} onChange={handleChange}>
                <option value='' disabled hidden>Select gender</option>
                <option value='Man'>Man</option>
                <option value='Woman'>Woman</option>
                <option value='Nonbinary'>Nonbinary</option>
            </select>
           
            <input type='text' id='email' value={person.email} placeholder='enter email' onChange={handleChange}/>
            <button type='submit'>{isEditing ? 'Save edit':'Save person'}</button>
        </form>
        <hr/>
      
    </>
  )
}

export default PersonInputForm
