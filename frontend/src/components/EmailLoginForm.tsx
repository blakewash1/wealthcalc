import { useState } from "react";
import { useAuth } from "../hooks/useAuth";

export function EmailLoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { loginWithEmail } = useAuth();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    loginWithEmail(email, password);
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-2">
      <input
        className="border p-2 w-full"
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
      />
      <input
        className="border p-2 w-full"
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        required
      />
      <button className="bg-blue-600 text-white p-2 w-full rounded">
        Log In
      </button>
    </form>
  );
}