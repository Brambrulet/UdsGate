package ru.brambrulet.service.iface;


import java.math.BigDecimal;
import java.util.function.BiPredicate;
import ru.brambrulet.entity.CustomerEntity;
import ru.brambrulet.entity.PayAccountEntity;

public interface PayCodeService extends Finder<PayAccountEntity> {

    Condition<Long> CHECK_FOR_ID = (payAccountEntity, id) -> id.equals(payAccountEntity.getId());
    Condition<Long> CHECK_FOR_UNUSED = (payAccountEntity, shortPlayCode) ->
            !payAccountEntity.getUsed() && shortPlayCode.equals(payAccountEntity.getShortPlayCode()
            );

    PayAccountEntity persist(PayAccountEntity payAccountEntity);

    default PayAccountEntity persist(long shortPlayCode, String longPlayCode, BigDecimal orderSumm, CustomerEntity customer) {
        PayAccountEntity payAccountEntity = new PayAccountEntity();

        payAccountEntity.setShortPlayCode(shortPlayCode);
        payAccountEntity.setLongPlayCode(longPlayCode);
        payAccountEntity.setOrderSumm(orderSumm);
        payAccountEntity.setUsed(false);
        payAccountEntity.setCustomer(customer);

        return persist(payAccountEntity);
    }

    default PayAccountEntity findUnused(long card) {
        return find(CHECK_FOR_UNUSED, card);
    }

    interface Condition<T> extends BiPredicate<PayAccountEntity, T> { }
}
