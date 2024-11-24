import React, { useState } from 'react';

const SearchBar = ({ onSearch }) => {
  const [query, setQuery] = useState('');

  const handleSearch = () => {
    onSearch(query);
    console.log('Searching for:', query);
  };

  return (
    <div>
      <input type="text" value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search movies..." />
      <button onClick={handleSearch}>Search</button>
    </div>
  );
};

export default SearchBar;