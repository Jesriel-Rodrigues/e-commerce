package rm.tech.ecommerce.module.account.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import rm.tech.ecommerce.module.account.api.dtos.request.StoreByAdminPost;
import rm.tech.ecommerce.module.account.api.dtos.response.StoreResponse;
import rm.tech.ecommerce.module.account.services.interfaces.IAccountStoreService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/store")
@AllArgsConstructor
public class AccountStoreController {
    
    private final IAccountStoreService storeService;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN_STOREA')")
    public ResponseEntity<StoreResponse> createStoreByAdmin(@Valid @RequestBody StoreByAdminPost request) {
        return new ResponseEntity<>(storeService.createStoreByAdmin(request), HttpStatus.CREATED);
    }
    
}
