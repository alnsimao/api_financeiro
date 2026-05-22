import { Link } from 'react-router-dom';

function LandingPage() {
  return (
    <div style={{ textAlign: 'center', marginTop: '50px' }}>
      <h1>Finance System</h1>
      <p>Organize suas finanças de forma simples e rápida.</p>
      <div style={{ gap: '10px', display: 'flex', justifyContent: 'center' }}>
        <Link to="/login"><button>Fazer Login</button></Link>
        <Link to="/register"><button>Criar Conta</button></Link>
      </div>
    </div>
  );
}

export default LandingPage;