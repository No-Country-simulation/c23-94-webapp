import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Routes, Route, BrowserRouter, Navigate, useLocation } from 'react-router-dom';
import './App.css';
import HomePage from './components/homepage';
import { Comienzo } from './components/comienzo';
import { Navbar } from './components/navbar';
import SignUpModal from './components/sign-up'; 
import LoginModal from './components/login';

function App() {
  return (
    <BrowserRouter>
      <AppContent />
    </BrowserRouter>
  );
}
function AppContent() {
  const [modalOpen, setModalOpen] = useState(false); 
  const [loginModalOpen, setLoginModalOpen] = useState(false); 

  const openModal = () => setModalOpen(true);
  const closeModal = () => setModalOpen(false); 
  
  const openLoginModal = () => setLoginModalOpen(true);
  const closeLoginModal = () => setLoginModalOpen(false); 

  const location = useLocation();
  const divClass = location.pathname === "/homepage" ? "App" : "divBody";

  return (
    <>
      {location.pathname !== "/homepage" && (
        <Navbar openModal={openModal} openLoginModal={openLoginModal} />
      )}
      <div className={divClass}>
        <Routes>
          <Route path="/homepage" element={<HomePage openModal={openModal} />} />
          <Route path="/comienzo" element={<Comienzo />} />
          <Route path="*" element={<Navigate to="/homepage" />} />
        </Routes>
      </div>

      <SignUpModal isOpen={modalOpen} onClose={closeModal} openLoginModal={openLoginModal} />
      <LoginModal
        isOpen={loginModalOpen}
        onClose={closeLoginModal}
        openModal={openModal} 
      />
    </>
  );
}

export default App;
