import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import imgsign from '../assets/img-sign-up.png';

const SignUpModal = ({ isOpen, onClose, openLoginModal }) => {
  const [formData, setFormData] = useState({
    last_name: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
    phone: '',
    address: '',
    role: '',
  });
  const [errorMessage, setErrorMessage] = useState('');

  if (!isOpen) return null;

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSignUp = async (e) => {
    e.preventDefault();
    const { password, confirmPassword } = formData;

    if (password !== confirmPassword) {
      setErrorMessage('Las contraseñas no coinciden');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/v1/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        console.log('Usuario registrado:', await response.json());
        setFormData({
          last_name: '',
          username: '',
          email: '',
          password: '',
          confirmPassword: '',
          phone: '',
          address: '',
          role: '',
        });
        setErrorMessage('');
        onClose();
      } else {
        const errorData = await response.json();
        setErrorMessage(errorData.message || 'Error al registrar usuario');
      }
    } catch (error) {
      console.error('Error al registrar usuario:', error);
      setErrorMessage('Ocurrió un error inesperado, intente más tarde.');
    }
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
              <Form.Group controlId="formBasicLastName" className="floating-label-group mt-4">
                <Form.Control
                  type="text"
                  placeholder=" "
                  name="last_name"
                  value={formData.last_name}
                  onChange={handleInputChange}
                  required
                />
                <Form.Label>Apellido</Form.Label>
              </Form.Group>

              <Form.Group controlId="formBasicUsername" className="floating-label-group mt-4">
                <Form.Control
                  type="text"
                  placeholder=" "
                  name="username"
                  value={formData.username}
                  onChange={handleInputChange}
                  required
                />
                <Form.Label>Nombre de usuario</Form.Label>
              </Form.Group>

              <Form.Group controlId="formBasicEmail" className="floating-label-group mt-4">
                <Form.Control
                  type="email"
                  placeholder=" "
                  name="email"
                  value={formData.email}
                  onChange={handleInputChange}
                  required
                />
                <Form.Label>Email</Form.Label>
              </Form.Group>

              <Form.Group controlId="formBasicPassword" className="floating-label-group mt-4">
                <Form.Control
                  type="password"
                  placeholder=" "
                  name="password"
                  value={formData.password}
                  onChange={handleInputChange}
                  required
                />
                <Form.Label>Contraseña</Form.Label>
              </Form.Group>

              <Form.Group controlId="formBasicConfirmPassword" className="floating-label-group mt-4">
                <Form.Control
                  type="password"
                  placeholder=" "
                  name="confirmPassword"
                  value={formData.confirmPassword}
                  onChange={handleInputChange}
                  required
                />
                <Form.Label>Confirmar Contraseña</Form.Label>
              </Form.Group>

              <Form.Group controlId="formBasicPhone" className="floating-label-group mt-4">
                <Form.Control
                  type="text"
                  placeholder=" "
                  name="phone"
                  value={formData.phone}
                  onChange={handleInputChange}
                  required
                />
                <Form.Label>Teléfono</Form.Label>
              </Form.Group>

              <Form.Group controlId="formBasicAddress" className="floating-label-group mt-4">
                <Form.Control
                  type="text"
                  placeholder=" "
                  name="address"
                  value={formData.address}
                  onChange={handleInputChange}
                  required
                />
                <Form.Label>Dirección</Form.Label>
              </Form.Group>

              <Form.Group controlId="formBasicRole" className="floating-label-group mt-4">
                <Form.Control
                  type="text"
                  placeholder=" "
                  name="role"
                  value={formData.role}
                  onChange={handleInputChange}
                  required
                />
                <Form.Label>Rol</Form.Label>
              </Form.Group>

              <div className="d-flex justify-content-center mt-4">
                <Button variant="primary" type="submit">
                  Registrarse
                </Button>
              </div>
            </Form>
            <div className="text-center mt-3">
              <span>¿Ya tienes cuenta? </span>
              <button
                onClick={() => {
                  onClose();
                  openLoginModal();
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