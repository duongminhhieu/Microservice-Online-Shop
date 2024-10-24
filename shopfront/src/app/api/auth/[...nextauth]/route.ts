// src/app/api/auth/[...nextauth]/route.ts
import NextAuth, { AuthOptions } from "next-auth";
import KeycloakProvider from "next-auth/providers/keycloak"

console.log("KEYCLOAK_ISSUER:", process.env.KEYCLOAK_ISSUER);
console.log("NEXTAUTH_URL:", process.env.NEXTAUTH_URL);

export const authOptions: AuthOptions = {
    providers: [
        KeycloakProvider({
            clientId: process.env.KEYCLOAK_CLIENT_ID,
            clientSecret: process.env.KEYCLOAK_CLIENT_SECRET,
            issuer: process.env.KEYCLOAK_ISSUER
        })
    ]
}
const handler = NextAuth(authOptions);
export { handler as GET, handler as POST }