package ru.brambrulet.service.iface;

import java.util.UUID;
import java.util.function.BiPredicate;
import ru.brambrulet.entity.CustomerEntity;
import ru.brambrulet.json.Customer;

public interface CustomerService extends Finder<CustomerEntity>{

    Condition<Long> CHECK_FOR_ID = (customer, id) -> id.equals(customer.getId());
    Condition<Long> CHECK_FOR_UDS_ID = (customer, udsId) -> udsId.equals(customer.getUdsId());
    Condition<UUID> CHECK_FOR_UID = (customer, uid) -> uid.equals(customer.getUid());
    Condition<String> CHECK_FOR_PHONE = (customer, phone) -> phone.equals(customer.getPhone());

    CustomerEntity persist(CustomerEntity customer);

    default CustomerEntity persist(Customer user) {
        if (user == null) {
            return null;
        }

        CustomerEntity customer = new CustomerEntity();

        customer.setUdsId(user.getId());
        customer.setUid(user.getUid());
        customer.setAvatar(user.getAvatar());
        customer.setDisplayName(user.getDisplayName());
        customer.setGender(user.getGender());
        customer.setPhone(user.getPhone());
        customer.setMembershipTierUid(user.getMembershipTier() != null ? user.getMembershipTier().getUid() : null);

        return persist(customer);
    }

    default CustomerEntity findById(Long id) {
        return find(CHECK_FOR_ID, id);
    }

    default CustomerEntity findByUdsId(Long udsId) {
        return find(CHECK_FOR_UDS_ID, udsId);
    }

    default CustomerEntity findByUID(UUID uid) {
        return find(CHECK_FOR_UID, uid);
    }

    default CustomerEntity findByPhone(String phone) {
        return find(CHECK_FOR_PHONE, phone);
    }

    interface Condition<T> extends BiPredicate<CustomerEntity, T> { }
}
