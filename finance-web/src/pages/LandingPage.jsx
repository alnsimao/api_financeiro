import { Link } from 'react-router-dom';

function LandingPage() {
  return (
    <div className="bg-[#0f172a] text-white font-sans">
      <section className="flex flex-col items-center justify-center min-h-screen p-4 text-center bg-[radial-gradient(circle_at_top_right,var(--tw-gradient-stops))] from-emerald-500/10 via-transparent to-transparent">
        <h1 className="text-5xl md:text-7xl font-bold mb-6 leading-tight">
          Controle seu Dinheiro.<br />Domine seu <span className="text-emerald-950">Futuro.</span>
        </h1>
        <p className="text-xl md:text-2xl text-slate-400 max-w-3xl mb-10">
          A plataforma financeira definitiva para quem busca clareza e organização pessoal!!!
        </p>
        <div className="flex gap-4">
          <Link to="/register" className="bg-emerald-500 hover:bg-emerald-600 text-slate-900 px-10 py-4 rounded-full text-xl font-bold transition-all transform hover:scale-105">
            Começar Agora
          </Link>
          <Link to="/login" className="bg-emerald-500 hover:bg-emerald-600 text-slate-900 px-10 py-4 rounded-full text-xl font-bold transition-all transform hover:scale-105">
            Login
          </Link>
        </div>
      </section>
    </div>
  );
}

export default LandingPage;