import { getServerSession } from "next-auth";
import { authOptions } from "./api/auth/[...nextauth]/route";
import Login from "../components/Login";
import Logout from "../components/Logout";
import { Button } from "antd";

export default async function Home() {
  const session = await getServerSession(authOptions);
  if (session) {
    return (
      <div className="flex flex-col space-y-3 justify-center items-center h-screen">
        <div>Your name is {session.user?.name}</div>
        <div>
          <Logout />
        </div>

        <div className="flex gap-4 mt-10">
          <Button type="primary" href="/private">
            Private Page
          </Button>
          <Button type="primary" href="/products">
            Product Page
          </Button>
        </div>
      </div>
    );
  }
  return (
    <div className="flex justify-center items-center h-screen">
      <Login />
    </div>
  );
}
