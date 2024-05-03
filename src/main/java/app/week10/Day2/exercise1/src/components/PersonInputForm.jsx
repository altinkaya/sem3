import React, {useState} from 'react'

const PersonInputForm = ({updated, setUpdated, person, setPerson}) => {

    const isEditing = person.id !== undefined;
    
    const handleChange = (event) => {
        setPerson({...person, [event.target.id]: event.target.value})
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        const url = isEditing ? `http://localhost:3001/persons/${person.id}` : 'http://localhost:3001/persons';

        fetch(url, {´´
            method: isEditing ? 'PUT' : 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(person),
        })
            .then((response) => response.json())
            .then(() => {
                setUpdated(!updated);
                setPerson({ age: '', fullname: '', gender: '', email: '' });
            }   );
    };

    return (
        <div>
            <h3>Upload new person</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="age">Age:</label>
                    <input type="number" className="form-control" value={person.age} id="age" placeholder="Enter age" onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor="fullname">Fullname:</label>
                    <input type="text" className="form-control" value={person.fullname} id="fullname" placeholder="Enter fullname" onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor="gender">Gender:</label>
                    <select className="form-control" value={person.gender} id="gender" onChange={handleChange}>
                        <option value="" disabled hidden>Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Woman">Woman</option>
                        <option value="Nonbinary">Nonbinary</option>
                    </select>
                </div>
                <div>
                    <label htmlFor="email">Email:</label> 
                    <input type="text" className="form-control" value={person.email} id="email" placeholder="Enter email" onChange={handleChange} />
                </div>
                <button type="submit" className="btn btn-primary mt-2">{isEditing ? 'Edit person' : 'Add person'}</button>
            </form>
        </div>
    );
};

{/*mt-2 er med til at sætte spacing foroven*/}
{/*form-control sørger for pæne indput bokse*/}
{/*btn btn-primary sørger for en pæn knap*/}
export default PersonInputForm
