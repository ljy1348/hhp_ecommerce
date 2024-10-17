package com.example.hhp_ecommerce.Application.service;

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

    public Product getProductDetail(Long id) {
        Product product1 = productRepository.getById(id);
        Product product2 = productQuantityRepository.getById(id);
        Product product3 = productDetailRepository.getProductDetail(id);

        return new Product(product1.getId(), product1.getName(), product3.getDetail(), product2.getQuantity(), product1.getPrice());
    }

    public List<Product> getProductList() {
        List<Product> products = productRepository.findAll();
        List<Product> products1 = productQuantityRepository.findAll();

        // products1의 상품 아이디와 수량을 맵으로 변환 (id를 키로, 수량을 값으로)
        Map<Long, Integer> productQuantityMap = products1.stream()
                .collect(Collectors.toMap(Product::getId, Product::getQuantity));

        // 새로운 상품 객체로 리스트 생성
        List<Product> mergedProductList = products.stream()
                .map(product -> {
                    Integer quantity = productQuantityMap.get(product.getId()); // 동일한 상품 아이디에 해당하는 수량 가져오기
                    if (quantity == null) {
                        quantity = 0; // 수량이 없는 경우 기본값으로 0 설정
                    }
                    return new Product(product.getId(), product.getName(), product.getPrice(), quantity); // 새로운 상품 객체 생성
                })
                .collect(Collectors.toList());

        return mergedProductList; // 새 상품 리스트 반환
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
