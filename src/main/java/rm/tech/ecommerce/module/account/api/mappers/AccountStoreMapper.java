package rm.tech.ecommerce.module.account.api.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import rm.tech.ecommerce.module.account.api.dtos.request.StoreByAdminPost;
import rm.tech.ecommerce.module.account.api.dtos.response.StoreResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.store.StoreAccount;

@Component
@RequiredArgsConstructor
public class AccountStoreMapper {
    
    private final ModelMapper modelMapper;

    public StoreAccount convertRequestInEntity(StoreByAdminPost storePost){
        return modelMapper.map(storePost, StoreAccount.class);
    }

    public StoreResponse convertEntityInResponse( StoreAccount store){
        return modelMapper.map(store, StoreResponse.class);
    }
}
