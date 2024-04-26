import React, { useState, useEffect } from 'react';

function JokeComponent() {
    // State to store the joke
    const [joke, setJoke] = useState('');

    // useEffect to fetch the joke
    useEffect(() => {
        fetch('https://api.chucknorris.io/jokes/random')
            .then(response => response.json())
            .then(data => setJoke(data.value))
            .catch(error => console.error('Error fetching joke:', error));
    
            fetchJoke();
            // Set interval to fetch joke every 10 seconds
            const intervalId = setInterval(fetchJoke, 10000);
            // Cleanup function to clear interval
            return () => clearInterval(intervalId);
        }, []);
     // Empty dependency array means this runs once on mount

    const fetchJoke = () => {
        fetch('https://api.chucknorris.io/jokes/random')
            .then(response => response.json())
            .then(data => setJoke(data.value))
    };

    return (
        <div>
            <p>{joke}</p>
            <button onClick={fetchJoke}>New Joke</button>
        </div>
    );
}

export default JokeComponent;