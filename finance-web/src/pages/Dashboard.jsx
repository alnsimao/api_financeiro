import { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

function Dashboard() {
  const navigate = useNavigate();

  // Verifica se o usuário está logado ao carregar a página
  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      navigate('/login');
    }
  }, [navigate]);

  // Função simples e limpa para deslogar
  const handleLogout = () => {
    localStorage.removeItem('token'); // Remove o token do navegador
    navigate('/'); // Redireciona para a Landing Page
  };

  return (
    <div className="bg-[#0f172a] text-white font-sans min-h-screen flex">
      
      {/* 1. MENU LATERAL (Sidebar) */}
      <aside className="w-64 bg-[#1e293b] border-r border-slate-800 flex flex-col justify-between p-6">
        <div>
          {/* Logo / Título */}
          <h2 className="text-2xl font-bold text-emerald-400 mb-8 flex items-center gap-2">
            💰 FinanceApp
          </h2>
          
          {/* Links de Navegação */}
          <nav className="space-y-3">
            <Link to="/dashboard" className="block px-4 py-2.5 rounded-lg bg-emerald-500/10 text-emerald-400 font-semibold transition-colors">
              Início
            </Link>
            
            <Link to="/categorias" className="block px-4 py-2.5 rounded-lg text-slate-300 hover:bg-slate-800 hover:text-white transition-colors">
              Categorias
            </Link>
            
            <Link to="/transacoes" className="block px-4 py-2.5 rounded-lg text-slate-300 hover:bg-slate-800 hover:text-white transition-colors">
              Transações
            </Link>
          </nav>
        </div>

        {/* Botão de Logout */}
        <button 
          onClick={handleLogout}
          className="w-full bg-red-500/10 hover:bg-red-500 text-red-400 hover:text-white py-2 px-4 rounded-lg font-semibold transition-all"
        >
          Sair da Conta
        </button>
      </aside>

      {/* 2. ÁREA CONTEÚDO PRINCIPAL */}
      <main className="flex-1 p-8">
        
        {/* Cabeçalho */}
        <header className="flex justify-between items-center mb-8">
          <div>
            <h1 className="text-3xl font-bold">Olá, bem-vindo de volta! 👋</h1>
            <p className="text-slate-400 text-sm">Aqui está o resumo das suas finanças.</p>
          </div>
          <div className="text-sm bg-slate-800 px-4 py-2 rounded-full border border-slate-700">
            Status: <span className="text-emerald-400 font-bold">Conectado</span>
          </div>
        </header>

        {/* 3. CARDS DE RESUMO RÁPIDO */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
          
          {/* Card Saldo */}
          <div className="bg-[#1e293b] p-6 rounded-2xl border border-slate-800 shadow-lg">
            <p className="text-slate-400 text-sm font-medium">Saldo Geral</p>
            <h3 className="text-3xl font-extrabold text-white mt-2">R$ 5.250,00</h3>
          </div>

          {/* Card Receitas */}
          <div className="bg-[#1e293b] p-6 rounded-2xl border border-slate-800 shadow-lg">
            <p className="text-slate-400 text-sm font-medium">Receitas (Mês)</p>
            <h3 className="text-3xl font-extrabold text-emerald-400 mt-2">+ R$ 7.000,00</h3>
          </div>

          {/* Card Despesas */}
          <div className="bg-[#1e293b] p-6 rounded-2xl border border-slate-800 shadow-lg">
            <p className="text-slate-400 text-sm font-medium">Despesas (Mês)</p>
            <h3 className="text-3xl font-extrabold text-rose-400 mt-2">- R$ 1.750,00</h3>
          </div>

        </div>

        {/* 4. ESPAÇO PARA O PRÓXIMO CONTEÚDO */}
        <div className="bg-[#1e293b] p-8 rounded-2xl border border-slate-800 min-h-[300px] flex flex-col items-center justify-center text-center">
          <p className="text-slate-400 mb-4">Nenhuma atividade recente registrada.</p>
          <Link to="/categorias" className="bg-emerald-500 hover:bg-emerald-600 text-slate-900 px-6 py-2.5 rounded-lg font-bold transition-all">
            Gerenciar Categorias
          </Link>
        </div>

      </main>

    </div>
  );
} 

export default Dashboard;