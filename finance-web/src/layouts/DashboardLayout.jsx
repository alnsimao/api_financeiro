import { Outlet } from "react-router-dom";
import Sidebar from "../components/Sidebar";

export default function DashboardLayout(){


    
    return(<div className="flex min-h-screen bg-slate-900 text-white font-sans min-h-screen flex">
            <Sidebar />
        <main className="flex-1 p-6">
            
            <Outlet />
        </main>
    </div>)
}