import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap'; 
import imgsign from '../assets/img-sign-up.png';

const SignUpModal = ({ isOpen, onClose, openLoginModal }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  if (!isOpen) return null;

  const handleSignUp = (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setErrorMessage('Las contraseñas no coinciden');
      return;
    }
    console.log('Usuario registrado:', { email, password });
    onClose(); 
  };

  return (
    <Modal show={isOpen} onHide={onClose} centered size="lg">
      <Modal.Header closeButton>
        <Modal.Title>Crearse una cuenta</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className="d-flex align-items-center justify-content-center">
          <div className="d-flex flex-column align-items-center justify-content-center" style={{ marginRight: '20px' }}>
            <img 
              src={imgsign} 
              alt="Imagen de registro"
              style={{ maxWidth: '400px', borderRadius: '10px' }} 
            />
          </div>
          <div className="flex-grow-1">
            {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
            <Form onSubmit={handleSignUp}>
              <Form.Group controlId="formBasicEmail" className="floating-label-group">
                <Form.Control
                  type="email"
                  placeholder=" "
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="form-control rounded"
                  required
                />
                <Form.Label>Email</Form.Label>
              </Form.Group>

              <Form.Group controlId="formBasicPassword" className="floating-label-group mt-4">
                <Form.Control
                  type="password"
                  placeholder=" "
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="form-control rounded"
                  required
                />
                <Form.Label>Contraseña</Form.Label>
              </Form.Group>

              <Form.Group controlId="formBasicConfirmPassword" className="floating-label-group mt-4">
                <Form.Control
                  type="password"
                  placeholder=" " 
                  value={confirmPassword}
                  onChange={(e) => setConfirmPassword(e.target.value)}
                  className="form-control rounded"
                  required
                />
                <Form.Label>Confirmar Contraseña</Form.Label>
              </Form.Group>
              
              <div className="d-flex justify-content-center mt-3">
                <Button variant="primary" type="submit">
                  Registrarse
                </Button>
              </div>
            </Form>
            <div className="text-center mt-3">
              <span>¿Ya tienes cuenta? </span>
              <button
                onClick={() => {
                  onClose(); // Cierra el modal de registro
                  openLoginModal(); // Abre el modal de inicio de sesión
                }}
                style={{
                  background: 'none',
                  border: 'none',
                  padding: 0,
                  color: '#007bff',
                  cursor: 'pointer',
                  textDecoration: 'underline',
                }}
              >
                Inicia sesión
              </button>
            </div>
          </div>
        </div>
      </Modal.Body>
    </Modal>
  );
};

export default SignUpModal;
