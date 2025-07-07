// src/components/PlanForm.tsx

import { useState } from 'react';
import axios from 'axios';
import { type PlanRequest, type PlanResponse } from '../types/plan';

const API_URL = import.meta.env.VITE_API_URL;

export default function PlanForm() {
    const [form, setForm] = useState<PlanRequest>({
        currentAge: 30,
        retirementAge: 65,
        monthlyContribution: 500,
        interestRate: 0.0,
        startingBalance: 5000
    });

    const [response, setResponse] = useState<PlanResponse | null>(null);
    const [error, setError] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setForm(prev => ({
            ...prev,
            [name]: parseFloat(value)
        }));
    };

    const handleSubmit = async () => {
        try {
            const token = localStorage.getItem('accessToken');

            const res = await axios.post<PlanResponse>(`${API_URL}/api/plan/calculate`, form, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            setResponse(res.data);
            setError(null);
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err.message || 'Request failed.');
            } else {
                setError("An unknown error occured");
            }
            
        }
    };

    return (
        <div>
            <h2>WealthCalc Input</h2>
            {Object.keys(form).map((key) => (
                <div key={key}>
                    <label>{key}:</label>
                    <input
                        type="number"
                        name={key}
                        value={form[key as keyof PlanRequest]}
                        onChange={handleChange}
                    />
                </div>
            ))}
            <button onClick={handleSubmit}>Calculate</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            {response && (
                <div>
                    <h3>Projections</h3>
                    <ul>
                        {response.projections.map((p, idx) => (
                            <li key={idx}>Age {p.age}: ${Number(p.total).toLocaleString(undefined,
                                { minimumFractionDigits: 2, maximumFractionDigits: 2 }
                            )}</li>
                        ))}
                    </ul>
                    <p><strong>Final Balance: </strong> ${Number(response.finalBalance).toLocaleString(undefined,
                        { minimumFractionDigits: 2, maximumFractionDigits: 2 }
                    )}</p>
                </div>
            )}
        </div>
    );
    
}