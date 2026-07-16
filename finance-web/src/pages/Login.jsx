import { Link, useNavigate } from 'react-router-dom'; // Importado useNavigate para te redirecionar após o login
import { useState } from 'react';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate(); // Hook para redirecionamento automático

  const handleSubmit = async (e) => {
    e.preventDefault();

    // 1. Corrigido para URL em maiúsculo para coincidir perfeitamente com a chamada do fetch abaixo
    const URL = "http://localhost:8080/api/auth/login";

    try {
      const response = await fetch(URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }), 
      });

      if (response.ok) {
        const data = await response.json();
        
        
        localStorage.setItem('token', data.token);

        alert("Usuário Logado");
        setEmail('');
        setPassword('');
        
        
        navigate('/'); 
      } else {
        alert("Erro ao logar usuário. Verifique os dados.");
      }
    } catch (error) {
      console.error("Erro na conexão: ", error);
      alert("Não foi possível iniciar a conexão.");
    }
  };

  return (
    <div className='bg-[#0f172a] text-white font-sans min-h-screen flex flex-col items-center justify-center p-4'>
      
      <div className='w-full max-w-md bg-[#1e293b] p-8 rounded-2xl shadow-xl'>
        <h2 className='text-3xl font-bold mb-6 text-center text-emerald-400'>Acessar Conta</h2>
        
        <form onSubmit={handleSubmit} className='space-y-4'>
          <div>
            <label className='block text-sm font-medium mb-1' htmlFor='email'>
              E-mail
            </label>
            <input 
              type='email' 
              id='email'
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className='w-full px-4 py-2 rounded-lg bg-[#0f172a] border border-slate-700 text-white focus:outline-none focus:border-emerald-500 transition-colors'
              placeholder='seu@email.com'
            />
          </div>

          <div>
            <label className='block text-sm font-medium mb-1' htmlFor='password'>
              Senha
            </label>
            <input 
              type='password' 
              id='password'
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className='w-full px-4 py-2 rounded-lg bg-[#0f172a] border border-slate-700 text-white focus:outline-none focus:border-emerald-500 transition-colors'
              placeholder='••••••••'
            />
          </div>

          <button 
            type='submit' 
            className='w-full bg-emerald-500 hover:bg-emerald-600 text-slate-900 font-bold py-2 px-4 rounded-lg transition-colors mt-2'
          >
            Entrar
          </button>
        </form>

        <div className='mt-6 text-center text-sm text-slate-400 space-y-2'>
          <p>
            Não tem conta?{' '}
            <Link to="/register" className='text-emerald-400 hover:underline'>
              Cadastre-se
            </Link>
          </p>
          <div>
            <Link to="/" className='inline-block text-xs text-slate-500 hover:text-slate-300 underline'>
              Voltar para Home
            </Link>
          </div>
        </div>
      </div>

    </div>
  );
}

export default Login;