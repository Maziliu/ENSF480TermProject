import React, { useState, useEffect } from 'react';
import { useSelectionContext } from '../contexts/SelectionContext';
import '../styles/SearchBar.css';
import searchIcon from '../images/search.png';

const SearchBar = ({handleSetMovieList, query, setQuery}) => {
  const { handleSelectTheatre } = useSelectionContext();
  
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
          handleSelectTheatre('','');
          return response.json();
        } else {
          throw new Error('API call failed');
        }
      })
      .then((data) => handleSetMovieList(data))
      .catch((error) => console.error('Error fetching movies:', error));
  }, [query]);

  return (
    <div className='search'>
      <form className='search-bar'>
        <input type="text" value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search movies..." />
        <button className='search-button'>
          <img src={searchIcon} alt="🔍" className='search-icon'/>
        </button>
      </form>
    </div>
  );
};

export default SearchBar;
