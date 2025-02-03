import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Routes, Route, BrowserRouter, Navigate, useLocation } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './App.css';
import HomePage from './components/homepage';
import Comienzo from "./components/comienzo";
import { Navbar } from './components/navbar';
import SignUpModal from './components/sign-up'; 
import LoginModal from './components/login';
import Library from './components/libraryComponents/library';
import Loan from "./components/loanComponents/Loan"
import Author from './components/authorsComponents/authors';
import Footer from './components/footer'
import Review from './components/reviewsComponents/review';


function App() {
  return (
    <BrowserRouter>
      <ToastContainer />
      <AppContent />
      <Footer />
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
          <Route path="/library" element={<Library />}/>
          <Route path="/authors" element={<Author />}/>
          <Route path="/loans" element={<Loan />}/>
          <Route path="/review/:id" element={<Review />} />
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
