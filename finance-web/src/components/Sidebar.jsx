import {NavLink,useNavigate} from "react-router-dom";


export default function Sidebar() {

    const activeClassName = ({isActive}) => "px-4 py-3 rounded-lg font-semibold transition-colors " + 
    (isActive ? "bg-emerald-500/10 text-emerald-400" : "text-slate-300 hover:bg-slate-800 hover:text-white");
    
    const navigate = useNavigate();
    
   const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/login');
    };
    
    return (
        <aside className="w-64 shrink-0 bg-[#1e293b] border-r border-slate-800 flex flex-col justify-between p-6">
            <div>
                {/* Logo / Título */}
                <h2 className="text-2xl font-bold text-emerald-400 mb-8 flex items-center gap-2">
                    💰 Finance
                </h2>

            <nav className="flex flex-col gap-2">
                <NavLink className={activeClassName} to="/dashboard" end>
                    Início
                </NavLink>

                <NavLink className={activeClassName} to="/dashboard/categories">
                    Categorias
                </NavLink>

                <NavLink className={activeClassName} to="/dashboard/transactions">
                    Transações
                </NavLink>
            </nav>
            </div>
             <button 
              onClick={handleLogout}
              className="w-full bg-red-500/10 hover:bg-red-500 text-red-400 hover:text-white py-2 px-4 rounded-lg font-semibold transition-all"
            >
              Sair da Conta
            </button>
        </aside>
    );
}
