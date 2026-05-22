import { Link } from 'react-router-dom';

function Login() {
  return (
    <div style={{ padding: '20px', maxWidth: '300px', margin: '50px auto' }}>
      <h2>Login</h2>
      {/* Aqui vai o formulário que faremos no próximo passo */}
      <p>Não tem conta? <Link to="/register">Cadastre-se</Link></p>
      <Link to="/">Voltar para Home</Link>
    </div>
  );
}

export default Login;