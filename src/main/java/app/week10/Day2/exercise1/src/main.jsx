import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'

const rootStyles = {
  backgroundColor: 'lightblue', // Set the background color to blue
};


ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <>
    <div style={rootStyles}>
        <h1 className="row justify-content-center">Welcome to the database of persons in DK</h1>
        <hr></hr>
      <App />
    </div>
    </>
  </React.StrictMode>,
)