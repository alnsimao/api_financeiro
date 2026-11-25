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
        </>
    );
}