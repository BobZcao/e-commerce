package com.e_commerce.service;

import com.e_commerce.common.ServerResponse;
import com.e_commerce.pojo.Product;
import com.e_commerce.service.impl.IProductServiceImpl;

/**
 * Created by code on 2018/9/2.
 */
public interface IProductService{
    public ServerResponse saveOrUpdateProduct(Product product);
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status);
    public ServerResponse<Object> getDetail(Integer productId);
}
