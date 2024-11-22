import { ProductAPICaller } from "@/services/apis/product.api";
import { NextRequest, NextResponse } from "next/server";


export async function GET(req: NextRequest) {
    try {
        const response = await ProductAPICaller.getAllProducts();
        return NextResponse.json(response.data);
    } catch (error) {
        console.error("Error fetching products:", error);
        return NextResponse.json({ error: "Failed to fetch products" }, { status: 500 });
    }
}


export async function POST(req: NextRequest) {
    try {
        const response = await ProductAPICaller.createProduct(req.body);
        return NextResponse.json(response.data);
    } catch (error) {
        console.error("Error creating product:", error);
        return NextResponse.json({ error: "Failed to create product" }, { status: 500 });
    }
}