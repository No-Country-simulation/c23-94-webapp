import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import { jwtDecode } from "jwt-decode";
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const LoginModal = ({ isOpen, onClose, openModal }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  if (!isOpen) return null;

  const handleLogin = async (e) => {
    e.preventDefault();

    if (!username || !password) {
      toast.error('Por favor, complete todos los campos.', {
        position: "top-right",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
      });
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
        localStorage.setItem('token', data.data.jwt);
        localStorage.setItem('username', username);
        const decodedToken = jwtDecode(data.data.jwt);
        console.log(decodedToken); 
        const email = decodedToken.email;
        localStorage.setItem("email", email); 
        const role = decodedToken.role;
        localStorage.setItem('role', role);

        
        setUsername('');
        setPassword('');
        setErrorMessage('');
        onClose();
        window.location.reload();
      } else {
        const errorData = await response.json();
        toast.error(errorData.message || 'Error al iniciar sesión.', {
          position: "top-right",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "colored",
        });
      }
    } catch (error) {
      console.error('Error en la solicitud:', error);
      toast.error('Hubo un problema al conectar con el servidor.', {
        position: "top-right",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
      });
    }
  };

  return (
    <>
      <ToastContainer />

      <Modal show={isOpen} onHide={onClose} centered size="lg">
        <Modal.Header closeButton>
          <Modal.Title>Iniciar sesión</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="d-flex align-items-center justify-content-center">
            <div className="flex-grow-1">
              {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
              <Form onSubmit={handleLogin}>
                <Form.Group controlId="formLoginUsername" className="floating-label-group mt-4">
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