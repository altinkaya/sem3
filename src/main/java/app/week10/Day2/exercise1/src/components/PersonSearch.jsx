import React, { useState } from 'react'

const PersonSearch = () => {
  const [searchId, setSearchId] = useState('');
  const [searchResult, setSearchResult] = useState(null);

  const handleReadById = (event) => {
    event.preventDefault();
    fetch(`http://localhost:3001/persons/${searchId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('No person found');
            }
        })
        .then(data => {
            setSearchResult(data);
        })
        .catch(error => {
            setSearchResult('No person found. ', error);
        });
};


return (
    <div>
    <h3>Search for a person by ID</h3>
    <div className="row justify-content-center">
        <div>
            <form>
                <div className="input-group">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Enter ID"
                        value={searchId}
                        onChange={(e) => setSearchId(e.target.value)}
                    />
                    <div>
                        <button
                            className="btn btn-primary"
                            type="button"
                            onClick={handleReadById}
                        >
                            Search
                        </button>
                    </div>
                </div>
            </form>
            {searchResult !== null ? (
                <div>
                    <h2>Search Result</h2>
                    {typeof searchResult === 'string' ? (
                        <p>{searchResult}</p>
                    ) : (
                        <>
                            <p>ID: {searchResult.id}</p>
                            <p>Age: {searchResult.age}</p>
                            <p>Fullname: {searchResult.fullname}</p>
                            <p>Gender: {searchResult.gender}</p>
                            <p>Email: {searchResult.email}</p>
                        </>
                    )}
                </div>
            ) : null}
        </div>
    </div>
    </div>
);
};

{/*row justify-content-center sørger for at det hele ligger i midten*/}
{/*input-group sørger for at søgeknappen er til højre for inputfeltet*/}
{/*form-control gør input feltene pæne*/}
{/*btn btn-primary giver charme til knappen*/}

export default PersonSearch