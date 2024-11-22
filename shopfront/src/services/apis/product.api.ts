import { APIConstants } from "../api.constant";
import instance from "../instance.axios";

export class ProductAPICaller {

    static getAllProducts = () => instance.get(APIConstants.PRODUCTS.GET_ALL_PRODUCTS);
    static createProduct = (data: any) => instance.post(APIConstants.PRODUCTS.CREATE_PRODUCT, data);

}
