package dev.example.emarket.api;

import dev.example.emarket.business.abstracts.ICategoryService;
import dev.example.emarket.business.abstracts.IProductService;
import dev.example.emarket.business.abstracts.ISupplierService;
import dev.example.emarket.core.config.ResultData;
import dev.example.emarket.core.config.ResultHelper;
import dev.example.emarket.core.modelmapper.IModelMapperService;
import dev.example.emarket.dto.request.category.ProductSaveRequest;
import dev.example.emarket.dto.request.category.SupplierSaveRequest;
import dev.example.emarket.dto.response.product.ProductResponse;
import dev.example.emarket.dto.response.supplier.SupplierResponse;
import dev.example.emarket.entities.Category;
import dev.example.emarket.entities.Product;
import dev.example.emarket.entities.Supplier;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final ISupplierService supplierService;
    private final IModelMapperService modelMapper;
    private final ICategoryService categoryService;
    private final IProductService productService;

    public ProductController(ISupplierService supplierService,
                             IModelMapperService modelMapper,
                             ICategoryService categoryService,
                             IProductService productService) {

        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<ProductResponse> save(@Valid @RequestBody ProductSaveRequest productSaveRequest){
        Product saveProduct = this.modelMapper.forRequest().map(productSaveRequest, Product.class);

        Category category = this.categoryService.get(productSaveRequest.getCategoryId());
        saveProduct.setCategory(category);

        Supplier supplier = this.supplierService.get(productSaveRequest.getSupplierId());
        saveProduct.setSupplier(supplier);

        this.productService.save(saveProduct);
        ProductResponse productResponse = this.modelMapper.forResponse().map(saveProduct, ProductResponse.class);
        return ResultHelper.created(productResponse);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ProductResponse> get(@PathVariable("id") int id ){
        Product product = this.productService.get(id);
        ProductResponse productResponse = this.modelMapper.forResponse().map(product, ProductResponse.class);
        return ResultHelper.success(productResponse);
    }
    @GetMapping("/{id}/supplier")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<SupplierResponse> getSupplier(@PathVariable("id") int id ){
        Product product = this.productService.get(id);
        SupplierResponse supplierResponse = this.modelMapper.forResponse().map(product, SupplierResponse.class);
        return ResultHelper.success(supplierResponse);
    }

}
