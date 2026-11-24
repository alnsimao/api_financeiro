import {NavLink} from "react-router-dom";

export default function Sidebar() {
    return (
        <aside className="w-64 bg-[#1e293b] border-r border-slate-800 flex flex-col justify-between p-6">
            <div>
                {/* Logo / Título */}
                <h2 className="text-2xl font-bold text-emerald-400 mb-8 flex items-center gap-2">
                    💰 Finance
                </h2>

            <nav className="flex flex-col gap-2">
                <NavLink to="/dashboard" end>
                    Início
                </NavLink>

                <NavLink to="/dashboard/categories">
                    Categorias
                </NavLink>

                <NavLink to="/dashboard/transactions">
                    Transações
                </NavLink>
            </nav>
            </div>
        </aside>
    );
}
