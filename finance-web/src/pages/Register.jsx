import { Link } from 'react-router-dom';

function Register() {
  return (
    <div style={{ padding: '20px', maxWidth: '300px', margin: '50px auto' }}>
      <h2>Criar Conta</h2>
      {/* Aqui vai o formulário de cadastro */}
      <p>Já tem conta? <Link to="/login">Faça Login</Link></p>
      <Link to="/">Voltar para Home</Link>
    </div>
  );
}

export default Register;