import { useState } from 'react';
import { Link } from 'react-router-dom';




function Register() {

  const[email, setEmail] = useState('');
  const[password, setPassword] = useState('');
  
  const handleSubmit = async(e)=> {
    e.preventDefault();
  }

  const URL = "http://localhost:8080/api/auth/register";

  try{
    const response = await fetch(URL,{
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({email, password}),
    });
    if(response.ok) alert ("Usuário Cadastrado!");  
  }  catch(error){
    console.error("Erro na conexão:",error)
  }
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Formulário enviado!");
  };

  

  return (
    <div className='bg-[#0f172a] text-white font-sans min-h-screen flex flex-col items-center justify-center p-4'>
      
      <div className='w-full max-w-md bg-[#1e293b] p-8 rounded-2xl shadow-xl'>
        <h2 className='text-3xl font-bold mb-6 text-center text-emerald-400'>Criar Conta</h2>
        
        <form onSubmit={handleSubmit} className='space-y-4'>
          <div>
            <label className='block text-sm font-medium mb-1' htmlFor='email'>
              E-mail
            </label>
            <input 
              type='email' 
              id='email'
              required
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
              className='w-full px-4 py-2 rounded-lg bg-[#0f172a] border border-slate-700 text-white focus:outline-none focus:border-emerald-500 transition-colors'
              placeholder='••••••••'
            />
          </div>

          <button 
            type='submit' 
            className='w-full bg-emerald-500 hover:bg-emerald-600 text-slate-900 font-bold py-2 px-4 rounded-lg transition-colors mt-2'
          >
            Cadastrar
          </button>
        </form>

        <div className='mt-6 text-center text-sm text-slate-400 space-y-2'>
          <p>
            Já tem conta?{' '}
            <Link to="/login" className='text-emerald-400 hover:underline'>
              Faça Login
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

export default Register;