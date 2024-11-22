import { Modal, Button, Form, Input } from "antd";

interface CreateProductDialogProps {
  isOpen: boolean;
  onOpen: () => void;
  onClose: () => void;
}

function CreateProductDialog({
  isOpen,
  onOpen,
  onClose,
}: CreateProductDialogProps) {
  const handleCreateProduct = (values: any) => {
    // Handle product creation logic here
    // createProduct(values);
    onClose();
  };

  const createProduct = async (product: any) => {
    try {
      const response = await fetch("/api/products", {
        method: "POST",
        body: JSON.stringify(product),
        headers: {
          "Content-Type": "application/json",
        },
      });
      const data = await response.json();
      console.log(data);
    } catch (error) {
      console.error("Error creating product:", error);
    }
  };

  return (
    <div>
      <Modal
        title="Create Product"
        open={isOpen}
        onCancel={onClose}
        footer={false}
      >
        <Form layout="vertical" onFinish={handleCreateProduct}>
          <Form.Item
            label="Product Name"
            name="name"
            rules={[
              { required: true, message: "Please enter the product name" },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Description"
            name="description"
            rules={[
              {
                required: true,
                message: "Please enter the product description",
              },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Price"
            name="price"
            rules={[
              { required: true, message: "Please enter the product price" },
            ]}
          >
            <Input />
          </Form.Item>

          <div className="flex gap-4">
            <Button key="cancel" onClick={onClose}>
              Cancel
            </Button>
            <Button type="primary" htmlType="submit">
              Create
            </Button>
          </div>
        </Form>
      </Modal>
    </div>
  );
}

export default CreateProductDialog;
