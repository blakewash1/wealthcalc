import { useState } from 'react';
import axios from 'axios';

type PlanRequest = {
    currentAge: number;
    retirementAge: number;
    monthlyContribution: number;
    interestRate: number;
};

export default function PlanForm() {
    const [form, setForm] = useState<PlanRequest>({
        currentAge: 30,
        retirementAge: 65,
        monthlyContribution: 500,
        interestRate: 0.06
    });

    const [response, setResponse] = useState<any>(null);
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
            const res = await axios.post('/api/planner/calculate', form);
            setResponse(res.data);
            setError(null);
        } catch (err: any) {
            setError(err.response?.data?.message || 'Request failed.');
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
                        {response.projections.map((p: any, idx: number) => (
                            <li key={idx}>Age {p.age}: ${p.total}</li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}