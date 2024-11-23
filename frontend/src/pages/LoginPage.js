import React from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import LoginForm from '../forms/LoginForm';

function LoginPage() {
  return (
    <div>
      <Header />
      <LoginForm />
      <Footer />
    </div>
  );
}

export default LoginPage;
