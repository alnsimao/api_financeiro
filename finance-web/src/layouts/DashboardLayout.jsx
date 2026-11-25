import { Outlet, Navigate } from "react-router-dom";
import Sidebar from "../components/Sidebar";

export default function DashboardLayout(){
    
    const token = localStorage.getItem('token');
    if (!token) {
        return <Navigate to="/login" replace />;
    }


    
    return(<div className="flex min-h-screen bg-slate-900 text-white font-sans">
            <Sidebar />
        <main className="flex-1 p-6">
            
            <Outlet />
        </main>
    </div>)
}