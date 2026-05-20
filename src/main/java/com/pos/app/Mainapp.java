package com.pos.app;

import com.pos.controller.ProductController;
import com.pos.repository.ProductRepository;
import com.pos.service.ProductService;
import com.pos.view.ProductView;

public class Mainapp {

    public static void main(String[] args) {

        ProductService service = new ProductService();
        ProductRepository repository = new ProductRepository();

        ProductController controller =
                new ProductController(service, repository);

        javax.swing.SwingUtilities.invokeLater(() -> {
            new ProductView(controller);
        });
    }
}