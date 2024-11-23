import { ProductAPICaller } from "@/services/apis/product.api";
import { Modal, Button, Form, Input, message } from "antd";
import { useEffect } from "react";

interface CreateProductDialogProps {
  isOpen: boolean;
  onCreated: () => void;
  onClose: () => void;
}

function CreateProductDialog({
  isOpen,
  onCreated,
  onClose,
}: CreateProductDialogProps) {
  const [form] = Form.useForm();

  const createProduct = async (product: any) => {
    try {
      const response = await ProductAPICaller.createProduct(product);

      if (response.status === 201) {
        handleCloseDialog();
        onCreated();
      }
    } catch (error) {
      console.error("Error creating product:", error);
    }
  };

  const handleCloseDialog = () => {
    form.resetFields();
    onClose();
  };

  useEffect(() => {
    if (!isOpen) {
      form.resetFields();
    }
  }, [isOpen, form]);

  return (
    <div>
      <Modal
        title="Create Product"
        open={isOpen}
        onCancel={handleCloseDialog}
        footer={false}
      >
        <Form form={form} layout="vertical" onFinish={createProduct}>
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
            <Button key="cancel" onClick={handleCloseDialog}>
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
