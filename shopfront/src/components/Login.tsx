"use client";
import { Button, Card, Flex } from "antd";
import Layout, { Content, Header } from "antd/es/layout/layout";
import { signIn } from "next-auth/react";

export default function Login() {
  return (
    <>
      <Layout className="min-h-screen">
        <Header className="bg-white drop-shadow-md flex items-center justify-between lg:h-20">
          <Flex className="items-center cursor-pointer">
            <img src="/YasMiniLogo.png" className="w-24 mr-2" alt="" />
            <h1 className="text-2xl font-semibold text-dark">
              YasMini - Car Shop
            </h1>
          </Flex>
          <h2 className="flex items-center font-semibold text-lg">Sign In</h2>
        </Header>
        <Content className="lg:flex justify-center items-center m-8">
          <Card className="w-1/2">
            <Button type="primary" onClick={() => signIn("keycloak")}>
              Signin with keycloak
            </Button>
          </Card>
        </Content>
      </Layout>
    </>
  );
}
