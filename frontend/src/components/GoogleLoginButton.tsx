import { useEffect, useRef } from "react";
import { useAuth } from "../hooks/useAuth";

export function GoogleLoginButton() {
  const { loginWithGoogle } = useAuth();
  const googleDiv = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (window.google && googleDiv.current) {
      window.google.accounts.id.initialize({
        client_id: import.meta.env.VITE_GOOGLE_CLIENT_ID!,
        callback: (response: google.accounts.id.CredentialResponse) => {
          loginWithGoogle(response.credential);
        },
      });

      window.google.accounts.id.renderButton(googleDiv.current, {
        type: "standard",
        theme: "outline",
        size: "large",
      });
    }
  }, [loginWithGoogle]);

  return <div ref={googleDiv} className="flex justify-center"></div>;
}