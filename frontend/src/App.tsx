import PlanForm from './components/PlanForm';
import { Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import { isAuthenticated } from './utils/auth';

function App() {
    return (
        <div style={{ padding: '2rem' }}>
            <h1>WealthCalc</h1>
            <Routes>
                <Route path="/" element={<Navigate to="/login" />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/plan" element={isAuthenticated() ? <PlanForm /> : <Navigate to="/login" />} />
            </Routes>
        </div>
    );
}

export default App;