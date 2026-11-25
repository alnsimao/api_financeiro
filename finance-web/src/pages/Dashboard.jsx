import { Link} from "react-router-dom";

function Dashboard() {
  
  return(
    <>

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

    </>
  );
  
  
} 

export default Dashboard;