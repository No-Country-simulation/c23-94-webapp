import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';

const LoginModal = ({ isOpen, onClose, openModal }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  if (!isOpen) return null;

  const handleLogin = (e) => {
    e.preventDefault();
    if (!email || !password) {
      setErrorMessage('Por favor, complete todos los campos.');
      return;
    }
    console.log('Usuario autenticado:', { email, password });
    onClose(); 
  };

  return (
    <Modal show={isOpen} onHide={onClose} centered size="lg">
      <Modal.Header closeButton>
        <Modal.Title>Iniciar sesión</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className="d-flex align-items-center justify-content-center">
          <div className="flex-grow-1">
            {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
            <Form onSubmit={handleLogin}>
              <Form.Group controlId="formLoginEmail" className="floating-label-group">
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

              <Form.Group controlId="formLoginPassword" className="floating-label-group mt-4">
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
              
              <div className="d-flex justify-content-center mt-3">
                <Button variant="primary" type="submit">
                  Iniciar sesión
                </Button>
              </div>
            </Form>

            {/* Texto con enlace */}
            <div className="text-center mt-3">
              <span>¿No tienes cuenta? </span>
              <button
                onClick={() => {
                  onClose(); 
                  openModal(); 
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
                Regístrate
              </button>
            </div>
          </div>
        </div>
      </Modal.Body>
    </Modal>
  );
};

export default LoginModal;
