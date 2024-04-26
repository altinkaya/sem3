import './App.css'
import upper, {text1,text2, text3} from "./file1";
import obj, {males, females}from './file2';
import MultiWelcome from './file3';
import JokeComponent from './file4';


const person = obj;
const {firstName, lastName} = person;

const allPeople = [...males,...females]; //Syntax for at lægge to arrays sammen
console.log(allPeople);

const person2 = {email: person.email, name: person.firstName, friends: allPeople, gender: person.gender, lastName: person.lastName, phone: 12345678}
console.log(person2);

function App() {
  return (
    <>
      <p>{upper("please uppercase me")}</p>
      <p>{upper(text1)}</p>
      <p>{upper(text2)}</p>
      <p>{upper(text3)}</p>
      <h2>{firstName} {lastName}</h2>
      <h2>E3</h2>
      <MultiWelcome/>
      <JokeComponent/>
    </>
  )
}

export default App