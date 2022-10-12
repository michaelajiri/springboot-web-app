package com.infotech.springboot.app.service;

import com.infotech.springboot.app.model.Product;
import com.infotech.springboot.app.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> listAll(int pageNumber, String sortField, String sortDir,
                                 String keyword){
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);

        if(keyword != null){
            return productRepository.findAll(keyword, pageable);
        }

        return productRepository.findAll(pageable);
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public Product getById(Long id){
        return productRepository.findById(id).get();
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }
}