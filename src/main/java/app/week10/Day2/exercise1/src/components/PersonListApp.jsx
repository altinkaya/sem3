import React, {useState} from 'react'
import PersonList from './PersonList'
import PersonInputForm from './PersonInputForm';
import PersonSearch from './PersonSearch';

function PersonListApp() {
  const [updated, setUpdated] = useState(false); 
  const [person, setPerson] = useState({
    age: '',
    fullname: '',
    gender: '',
    email: ''
  });

  const handleUpdatePerson = (updatedPerson) => {
    setPerson(updatedPerson);
  };

  return (
    <div className="container mt-4 mb-4">

            <PersonSearch />
            <p></p>
            <hr></hr>

            <PersonInputForm updated={updated} setUpdated={setUpdated} person={person} setPerson={setPerson} />
            <hr></hr>

        <PersonList updated={updated} setUpdated={setUpdated} person={person} setPerson={setPerson} handleUpdatePerson={handleUpdatePerson} />
    </div>
);
}

{/*container mt-4 mb-4 sørger for at der er margin på toppen og bunden*/}
{/*row justify-content-center sørger for at elementtet er i midten*/}
export default PersonListApp
