import "./globals.css";
import { AntdRegistry } from "@ant-design/nextjs-registry";
import SessionGuard from "@/components/SessionGuard";
import { Providers } from "./Provider";

const RootLayout = ({ children }: React.PropsWithChildren) => (
  <html lang="en">
    <body>
      <AntdRegistry>
        <Providers>
          <SessionGuard>{children}</SessionGuard>
        </Providers>
      </AntdRegistry>
    </body>
  </html>
);

export default RootLayout;
