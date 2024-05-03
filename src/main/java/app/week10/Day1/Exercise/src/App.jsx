import './App.css'
import Counter from './components/Counter' 
import ListDemo from './components/ListDemo'
import Timer from './components/Timer'

function App() {
  return (
    <>
    <Timer />
    <ListDemo />
    <Counter count = {100} incrementCount = {10} decreaseCount = {5} />
    </>
  )
}

export default App