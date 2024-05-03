import React, {useState} from 'react'
import Personlist from './Personlist'
import PersonInputForm from './PersonInputForm'
import PersonSearch from './PersonSearch'

const PersonApp = () => {
    const [update,setUpdate] = useState(false);
    const [person,setPerson] = useState({
        age: '',
        fullname: '',
        gender: '',
        email: ''
    });
  return (
    <>
        <PersonSearch />
        <PersonInputForm update={update} setUpdate={setUpdate} person={person} setPerson={setPerson}/>
        <Personlist update={update} setUpdate={setUpdate} person={person} setPerson={setPerson}/>
    </>
  )
}

export default PersonApp
