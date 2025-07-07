export function isAuthenticated(): boolean {
  const token = localStorage.getItem("accessToken");
  return Boolean(token);
}