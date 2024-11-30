import React from 'react';
import '../styles/Header.css';
import Navigation from './header/Navigation';
import Logo from './header/Logo';
import CTA from './header/CTA';

const Header = () => {
  return (
    <header className='header'>
      <Navigation />
      <Logo />
      <CTA />
    </header>
  );
};

export default Header;