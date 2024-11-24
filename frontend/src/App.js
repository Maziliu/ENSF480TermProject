import React from 'react';
import { AuthProvider } from './contexts/AuthContext';
import { SelectionProvider } from './contexts/SelectionContext';
import AppRoutes from './routes/AppRoutes';

const App = () => {
  return (
    <AuthProvider>
      <SelectionProvider>
        <AppRoutes />
      </SelectionProvider>
    </AuthProvider>
  );
};

export default App;
