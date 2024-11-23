"use client";

import { useEffect, useState } from "react";
import { Table, Spin, Typography, Button } from "antd";
import { Product } from "./models/Product";
import CreateProductDialog from "./components/createProductDialog";
import { ProductAPICaller } from "@/services/apis/product.api";

const { Title } = Typography;

function ProductPage() {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false);
  const [refresh, setRefresh] = useState(false);

  const fetchProducts = async () => {
    try {
      const response = await ProductAPICaller.getAllProducts();
      const data = await response.data;
      setProducts(data);
    } catch (error) {
      console.error("Error fetching products:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, [refresh]);

  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "Price",
      dataIndex: "price",
      key: "price",
    },
    {
      title: "Description",
      dataIndex: "description",
      key: "description",
    },
  ];

  return (
    <div>
      <Title level={2}>Product List</Title>
      <Button
        type="primary"
        className="mb-6"
        onClick={() => {
          setIsCreateDialogOpen(true);
        }}
      >
        Add Product
      </Button>
      <Spin spinning={loading}>
        <Table dataSource={products} columns={columns} rowKey="id" />
      </Spin>

      <CreateProductDialog
        isOpen={isCreateDialogOpen}
        onClose={() => {
          setIsCreateDialogOpen(false);
        }}
        onCreated={() => {
          setRefresh(!refresh);
        }}
      />
    </div>
  );
}

export default ProductPage;
