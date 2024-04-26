import React, { useEffect } from 'react';
import './App.css';

function Map() {
  useEffect(() => {
    const handleCountryClick = (event) => {
      if (event.target.classList.contains('country')) {
        const countryCode = event.target.getAttribute('countrycode');
        fetch(`https://restcountries.com/v3.1/alpha/${countryCode}`)
          .then(response => response.json())
          .then(data => {
            console.log(data);
          })
      }
    };
    const map = document.getElementById('map');
    map.addEventListener('click', handleCountryClick);
    return () => {
      map.removeEventListener('click', handleCountryClick);
    };
  }, []);

  return (
    <div id="map">
      <div className="country" countrycode="dk">Denmark</div>
      <div className="country" countrycode="de">Germany</div>
      <div className="country" countrycode="fr">France</div>
    </div>
  );
}

function fetchDataById(id) {
  const url = `https://restcountries.com/v3.1/alpha/${id}`;
  fetch(url)
    .then(response => response.json())
    .then(data => updateGUI(data));
}

function updateGUI(data) {
  console.log(data);
}

function App() {
    fetchDataById("dk");
  return (
    <>
      <h3>Click on a country's name to get information</h3>
      <Map />
    </>
  );
}
export default App;