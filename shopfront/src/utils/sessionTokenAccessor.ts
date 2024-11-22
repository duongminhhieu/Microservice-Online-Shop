import { getServerSession } from "next-auth";
import { authOptions } from "../app/api/auth/[...nextauth]/route";

export async function getAccessToken() {

    const session = await getServerSession(authOptions);
    if (session) {
        return session.accessToken;
    }
    return null;
}
