package dev.example.emarket.api;

import dev.example.emarket.business.abstracts.ISupplierService;
import dev.example.emarket.core.config.Result;
import dev.example.emarket.core.config.ResultData;
import dev.example.emarket.core.config.ResultHelper;
import dev.example.emarket.core.modelmapper.IModelMapperService;
import dev.example.emarket.dto.request.category.SupplierSaveRequest;
import dev.example.emarket.dto.request.category.SupplierUpdateRequest;
import dev.example.emarket.dto.response.CursorResponse;
import dev.example.emarket.dto.response.supplier.SupplierResponse;
import dev.example.emarket.entities.Supplier;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/suppliers")
public class SupplierController  {
    private final ISupplierService supplierService;
    private final IModelMapperService modelMapper;

    public SupplierController(ISupplierService supplierService, IModelMapperService modelMapper) {
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<SupplierResponse> save(@Valid @RequestBody SupplierSaveRequest supplierSaveRequest){
        Supplier saveSupplier = this.modelMapper.forRequest().map(supplierSaveRequest, Supplier.class);
        this.supplierService.save(saveSupplier);
        SupplierResponse supplierResponse = this.modelMapper.forResponse().map(saveSupplier, SupplierResponse.class);
        return ResultHelper.created(supplierResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<SupplierResponse> update(@Valid @RequestBody SupplierUpdateRequest supplierUpdateRequest){
        Supplier updateSupplier = this.modelMapper.forRequest().map(supplierUpdateRequest, Supplier.class);
        this.supplierService.update(updateSupplier);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateSupplier, SupplierResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<SupplierResponse> get(@PathVariable("id") int id ){
        Supplier supplier = this.supplierService.get(id);
        SupplierResponse supplierResponse = this.modelMapper.forResponse().map(supplier, SupplierResponse.class);
        return ResultHelper.success(supplierResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<SupplierResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ){

        Page<Supplier> supplierPage = this.supplierService.cursor(page,pageSize);
        Page<SupplierResponse> supplierResponsePage = supplierPage
                .map(category -> this.modelMapper.forResponse().map(category, SupplierResponse.class));

        return ResultHelper.cursor(supplierResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.supplierService.delete(id);
        return ResultHelper.ok();
    }

}
