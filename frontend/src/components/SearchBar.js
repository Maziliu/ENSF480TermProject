import React, { useState, useEffect } from 'react';

const SearchBar = ({handleSetMovieList, query, setQuery}) => {
  
  useEffect(() => {
    console.log('Searching for:', query)
    const url = query
      ? `http://localhost:8080/browse/movies/search?movieName=${encodeURIComponent(query)}`
      : 'http://localhost:8080/browse/movies';

    fetch(url, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('API call failed');
        }
      })
      .then((data) => handleSetMovieList(data))
      .catch((error) => console.error('Error fetching movies:', error));
  }, [query]);

  return (
    <div>
      <input type="text" value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search movies..." />
    </div>
  );
};

export default SearchBar;
