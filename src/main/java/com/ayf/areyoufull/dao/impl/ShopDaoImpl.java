package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.ShopDao;
import com.ayf.areyoufull.entity.Account;
import com.ayf.areyoufull.entity.Address;
import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Shop;
import com.ayf.areyoufull.mapper.AccountMapper;
import com.ayf.areyoufull.mapper.AddressMapper;
import com.ayf.areyoufull.mapper.MerchandiseMapper;
import com.ayf.areyoufull.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDaoImpl implements ShopDao {
    private final ShopMapper shopMapper;
    private final AccountMapper accountMapper;
    private final MerchandiseMapper merchandiseMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public ShopDaoImpl(ShopMapper shopMapper, AccountMapper accountMapper, MerchandiseMapper merchandiseMapper, AddressMapper addressMapper) {
        this.shopMapper = shopMapper;
        this.accountMapper = accountMapper;
        this.merchandiseMapper = merchandiseMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public Shop findShopByShopID(Integer shopID) {
        Shop shop = shopMapper.findByShopID(shopID);
        if (shop == null) return null;
        Account account = accountMapper.findByAccountID(shop.getMerchantID());
        shop.setAccount(account);
        List<Address> addresses = addressMapper.findByAccountID(account.getAccountID());
        if (!addresses.isEmpty())
            shop.setAddress(addresses);
        return shop;
    }

    @Override
    public Shop findShopByMerchantID(Integer merchantID) {
        Shop shop = shopMapper.findByMerchantID(merchantID);
        if (shop == null) return null;
        Account account = accountMapper.findByAccountID(shop.getMerchantID());
        shop.setAccount(account);
        List<Address> addresses = addressMapper.findByAccountID(account.getAccountID());
        if (!addresses.isEmpty())
            shop.setAddress(addresses);
        return shop;
    }

    @Override
    public void newShop(Shop shop) {
        accountMapper.newAccount(shop.getAccount());
        if (!shop.getAddress().isEmpty())
            addressMapper.newAddress(shop.getAddress().get(0));
        shopMapper.newShop(shop);
    }

    @Override
    public void publishMerchandise(Merchandise merchandise) {
        merchandiseMapper.newMerchandise(merchandise);
    }

    @Override
    public void removeMerchandiseByMerchandiseID(Integer merchandiseID) {
        merchandiseMapper.deleteByMerchandiseID(merchandiseID);
    }

    @Override
    public void removeAllMerchandiseFromShop(Integer shopID) {
        merchandiseMapper.deleteByShopID(shopID);
    }

    @Override
    public List<Merchandise> getMerchandisesByShopID(Integer shopID) {
        return merchandiseMapper.findByShopID(shopID);
    }

    @Override
    public List<Merchandise> getMerchandisesByMerchantID(Integer merchantID) {
        return merchandiseMapper.findByMerchandiseID(merchantID);
    }

    @Override
    public Integer getNextID() {
        return shopMapper.getNextID();
    }

    @Override
    public void modifyShopInfo(Shop shop) {
        accountMapper.updateAccount(shop.getAccount());
        List<Address> addresses = addressMapper.findByAccountID(shop.getMerchantID());
//        if (addresses.size() != 1 || !addresses.get(0).getAddressID().equals(shop.getAddress().getAddressID()))
//            throw new BusinessException("更新失败");
        addressMapper.updateByAddress(shop.getAddress().get(0));
        shopMapper.updateShop(shop);
    }

    @Override
    public void terminateByShop(Shop shop) {
        shopMapper.deleteByShopID(shop.getShopID());
        addressMapper.deleteByAccountID(shop.getMerchantID());
        accountMapper.deleteByAccountID(shop.getMerchantID());
    }

    @Override
    public List<Shop> findRandomShopWithAmount(Integer amount) {
        return shopMapper.findRandomShopWithAmount(amount);
    }
}
