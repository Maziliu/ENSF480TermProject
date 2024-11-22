import React from 'react';
import { Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import Homepage from './pages/HomePage';
import Testing from './pages/TestingPage';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/Home" element={<Homepage />} />
        <Route path="/Testing" element={<Testing />} />
      </Routes>
    </div>
  );
}

export default App;
