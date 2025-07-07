import { useEffect } from "react";
import { EmailLoginForm } from "../components/EmailLoginForm";
import { GoogleLoginButton } from "../components/GoogleLoginButton";
import { useAuth } from "../hooks/useAuth";
import { useNavigate } from "react-router-dom";

export default function LoginPage() {
  const { message } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem("accessToken")) {
      navigate("/plan");
    }
  }, [navigate]);

  return (
    <div className="max-w-md mx-auto mt-10 p-4 shadow rounded bg-white">
      <h1 className="text-xl font-bold mb-4">Login</h1>
      <EmailLoginForm />

      <div className="my-4 text-center text-gray-500">OR</div>

      <GoogleLoginButton />

      {message && <p className="mt-4 text-center text-red-600">{message}</p>}
    </div>
  );
}