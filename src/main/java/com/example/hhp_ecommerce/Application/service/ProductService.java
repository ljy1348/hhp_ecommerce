package com.example.hhp_ecommerce.Application.service;

import com.example.hhp_ecommerce.domain.Cart;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.OrderProductRepository;
import com.example.hhp_ecommerce.domain.repository.ProductDetailRepository;
import com.example.hhp_ecommerce.domain.repository.ProductQuantityRepository;
import com.example.hhp_ecommerce.domain.repository.ProductRepository;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderProductDto;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQuantityRepository productQuantityRepository;
    private final ProductDetailRepository productDetailRepository;

    public List<Product> findAllByProducts(List<Product> products) {
        List<Long> productIds = products.stream()
                .map(Product::getId) // productId 추출
                .collect(Collectors.toList());
        return productRepository.findAllByProducts(productIds);
    }

    public List<Product> findAllByIds(List<Long> ids) {
        return productRepository.findAllByProducts(ids);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product saveQuantity(Product product) {
        return productQuantityRepository.save(product);
    }

    public Product getProductDetail(Long id) {
        Product product1 = productRepository.getById(id);
        Product product2 = productQuantityRepository.getById(id);
        Product product3 = productDetailRepository.getProductDetail(id);

        return new Product(product1.getId(), product1.getName(), product3.getDetail(), product2.getQuantity(), product1.getPrice());
    }

    public Product getProduct(Long id) {
        return productRepository.getById(id);
    }

    public Product getQuantity(Long id) {
        return productQuantityRepository.getById(id);
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> findAllQuantity() {
        return productQuantityRepository.findAll();
    }

    public List<Product> findAllQuantityByIds(List<Long> ids) {
        return productQuantityRepository.findAllById(ids);
    }

    public List<Product> findAllProductQuantityWithLock(List<Product> products) {
        List<Long> productIds = products.stream()
                .map(Product::getId) // productId 추출
                .collect(Collectors.toList());
        return productQuantityRepository.findAllByProductIdWithLock(productIds);
    }

    public List<Product> saveAllProductQuantity(List<Product> products) {
        return productQuantityRepository.saveAll(products);
    }

    public void isOrderQuantityAvailableAndUpdate(List<Product> products, List<Product> orderProducts) {
        for (Product orderProduct : orderProducts) {
            Product product = products.stream()
                    .filter(p -> p.getId() == (orderProduct.getId()))  // equals() 대신 == 사용 (long 타입일 때는 == 사용 가능)
                    .findFirst()
                    .orElse(null);

            // 제품 수량을 차감
            product.updateQuantity(product.getQuantity() - orderProduct.getQuantity());
        }
    }

    public void mergedProductAndQuantity(List<Product> products, List<Product> quantitys) {
        for (Product quantity : quantitys) {
            Product product = products.stream()
                    .filter(p -> p.getId() == (quantity.getId()))
                    .findFirst()
                    .orElse(null);

            // 제품 수량을 차감
            product.updateQuantity(quantity.getQuantity());
        }
    }

    public void mergedProductAndCart(List<Product> products, List<Cart> quantitys) {
        for (Cart cart : quantitys) {
            Product product = products.stream()
                    .filter(p -> p.getId() == (cart.getProductId()))
                    .findFirst()
                    .orElse(null);

            // 제품 수량을 차감
            product.updateQuantity(cart.getQuantity());
        }
    }

    public List<Product> updateOrderListWithProductPrices(List<Product> products, List<Product> orderProducts) {
        List<Product> updatedOrderList = new ArrayList<>();

        for (Product orderProduct : orderProducts) {
            Product product = products.stream()
                    .filter(p -> p.getId() == orderProduct.getId())  // long 타입이므로 == 사용
                    .findFirst()
                    .orElse(null);

            if (product == null) {
                throw new IllegalArgumentException("상품을 찾을 수 없습니다: " + orderProduct.getId());
            }

            // 가격을 products에서 가져와서 orderProduct에 반영
            Product updatedOrderProduct = new Product(orderProduct.getId(), orderProduct.getQuantity(), product.getPrice());
            updatedOrderList.add(updatedOrderProduct);
        }

        return updatedOrderList;
    }

    public int calculateTotalPrice(List<Product> products) {
        int totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

}
