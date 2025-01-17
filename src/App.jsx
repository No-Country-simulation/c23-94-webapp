import React from 'react';
import './App.css';
import  HomePage from './pages/homepage';
import  {Comienzo} from './pages/comienzo';
import { BrowserRouter, Route, Routes, Navigate, useLocation } from "react-router-dom";
import { Navbar } from './pages/navbar';

function App() {
  return (
    <BrowserRouter>
      <AppContent />
    </BrowserRouter>
  );
}

function AppContent() {
  const location = useLocation();
  const divClass = location.pathname === "/homepage" ? "App" : "divBody";
  return (
    <>
    {location.pathname !== "/homepage" && <Navbar />}
    <div className={divClass}>
      <Routes>
      <Route path="/homepage" element={<HomePage />} />
      <Route path="/comienzo" element={<Comienzo />} />
      <Route path="*" element={<Navigate to="/homepage" />} />
    </Routes>
    </div>
    </>
    
  );
}
export default App;
