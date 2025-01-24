import React, { useState } from 'react';
import { Modal, Button, Form, Toast, ToastContainer } from 'react-bootstrap';

const LoginModal = ({ isOpen, onClose, openModal }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [showToast, setShowToast] = useState(false); // Para manejar el Toast
  const [toastMessage, setToastMessage] = useState(''); // Mensaje dinámico para el Toast
  const [toastBg, setToastBg] = useState('success'); // Fondo del Toast dinámico (éxito/error)

  if (!isOpen) return null;

  const handleLogin = async (e) => {
    e.preventDefault();

    // Mostrar el Toast al presionar el botón del formulario
    setShowToast(true);

    if (!username || !password) {
      setToastMessage('Por favor, complete todos los campos.');
      setToastBg('danger');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/v1/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        const data = await response.json();
        localStorage.setItem('token', data.jwt); // Guarda el token en localStorage
        setToastMessage('Inicio de sesión exitoso.'); // Mensaje de éxito
        setToastBg('success'); // Fondo verde para éxito
        setUsername(''); // Limpia usuario
        setPassword(''); // Limpia contraseña
        setErrorMessage(''); // Limpia errores previos
        onClose(); // Cierra el modal
      } else {
        const errorData = await response.json();
        setToastMessage(errorData.message || 'Error al iniciar sesión.');
        setToastBg('danger'); // Fondo rojo para error
      }
    } catch (error) {
      console.error('Error en la solicitud:', error);
      setToastMessage('Hubo un problema al conectar con el servidor.');
      setToastBg('danger');
    }
  };

  return (
    <>
      {/* ToastContainer para manejar la notificación */}
      <ToastContainer position="top-end" className="p-3">
        <Toast
          onClose={() => setShowToast(false)}
          show={showToast}
          delay={3000}
          autohide
          bg={toastBg} // Color dinámico
        >
          <Toast.Header>
            <strong className="me-auto">{toastBg === 'success' ? 'Éxito' : 'Error'}</strong>
          </Toast.Header>
          <Toast.Body>{toastMessage}</Toast.Body>
        </Toast>
      </ToastContainer>

      <Modal show={isOpen} onHide={onClose} centered size="lg">
        <Modal.Header closeButton>
          <Modal.Title>Iniciar sesión</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="d-flex align-items-center justify-content-center">
            <div className="flex-grow-1">
              {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
              <Form onSubmit={handleLogin}>
                <Form.Group controlId="formLoginUsername" className="floating-label-group">
                  <Form.Control
                    type="text"
                    placeholder=" "
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    className="form-control rounded"
                    required
                  />
                  <Form.Label>Usuario</Form.Label>
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
    </>
  );
};

export default LoginModal;