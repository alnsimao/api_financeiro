import { useState, useEffect } from "react";

export default function Categories() {

    const [categories, setCategories] = useState([]);
    const token = localStorage.getItem("token");

    useEffect(() => {
        fetchCategories();
    }, []);

    const fetchCategories = async () => {
        try {
            const response = await fetch(
                "http://localhost:8080/api/categories/",
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }

            const data = await response.json();

            console.log(data);

            setCategories(data);

        } catch (error) {
            console.error("Error fetching categories:", error);
        }
    };

    return (
        <>
            <h1>Categorias</h1>
            <p>Total de categorias: {categories.length}</p>
            <table className="w-full text-left border-collapse border border-slate-500 mt-4 bg-[#1e293b] text-white rounded-lg overflow-hidden shadow-lg">
    <thead>
        <tr>
            <th>Nome</th>
            <th>Tipo</th>
            <th>Data de criação</th>
        </tr>
    </thead>
    <tbody>
        {categories.map((category) => (
            <tr key={category.id}>
                <td>{category.name}</td>
                <td>
                    <span className="px-3 py-1 rounded-full text-sm font-medium"{category.type === 'INCOME' ? 'bg-green-500 text-white' : 'bg-red-500 text-white'}>
                        {category.type === 'INCOME' ? 'Receita' : 'Despesa'}
                    </span>
                </td>
                <td>{new Date(category.createdAt).toLocaleDateString("pt-BR")}</td>
            </tr>
        ))}
    </tbody>
</table>
        </>
    );
}