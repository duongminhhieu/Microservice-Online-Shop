import { getServerSession } from "next-auth";
import { authOptions } from "@/app/api/auth/[...nextauth]/route";
import { Button } from "antd";
import federatedLogout from "@/utils/federatedLogout";

export default async function Private() {
  const session = await getServerSession(authOptions);
  if (session) {
    return (
      <div className="flex flex-col space-y-3 justify-center items-center h-screen">
        <div>You are accessing a private page</div>
        <div>Your name is {session.user?.name}</div>
        <div>
          {/* Logout */}
          <Button type="primary" onClick={federatedLogout}>
            Logout
          </Button>
        </div>
      </div>
    );
  }
}
