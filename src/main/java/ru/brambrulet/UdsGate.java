package ru.brambrulet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.brambrulet.entity.PayAccountEntity;
import ru.brambrulet.external.CardInfoEx;
import ru.brambrulet.external.Document;
import ru.brambrulet.external.Transaction;
import ru.brambrulet.json.ApplicationSettings;
import ru.brambrulet.json.Customer;
import ru.brambrulet.json.CustomerInfo;
import ru.brambrulet.json.Customers;
import ru.brambrulet.json.Item;
import ru.brambrulet.json.Items;
import ru.brambrulet.json.Operations;
import ru.brambrulet.json.sub.MembershipTier;
import ru.brambrulet.json.sub.ResponseJson;
import ru.brambrulet.request.CalcRequest;
import ru.brambrulet.request.CustomersRequest;
import ru.brambrulet.request.DeleteItemRequest;
import ru.brambrulet.request.FindCustomerRequest;
import ru.brambrulet.request.GetItemRequest;
import ru.brambrulet.request.ItemRequest;
import ru.brambrulet.request.ItemsRequest;
import ru.brambrulet.request.OperationRequest;
import ru.brambrulet.request.OperationsRequest;
import ru.brambrulet.request.SettingsRequest;
import ru.brambrulet.service.iface.CustomerService;
import ru.brambrulet.service.iface.PayCodeService;
import ru.brambrulet.service.memory.CustomerServiceInMemory;
import ru.brambrulet.service.memory.PayCodeServiceInMemory;

import static java.util.Objects.isNull;
import static ru.brambrulet.external.TransactionType.CASH;
import static ru.brambrulet.external.TransactionType.PAY;

@Getter
public class UdsGate {
    private final static String CARD_INFO = "Покупайте наших слонов!";
    public static void main(String[] args) {
        UdsGate gate = new UdsGate();

//        Operations operations = OperationsRequest.request(gate);
//
//        Items items = ItemsRequest.request(gate);
//
//        Item item = ItemRequest.newCategory()
//                .name("qwerty")
//                .request(gate);
//
//        item = ItemRequest.updateCategory(item)
//                .name("asdf")
//                .request(gate);
//
//        item = GetItemRequest.request(gate, item.getId());
//
//        ResponseJson response = DeleteItemRequest.request(gate, item.getId());
//
//        Customers customers = CustomersRequest.request(gate);
//
//        CustomerInfo customerInfo = FindCustomerRequest.findWith()
//                .uid(UUID.fromString("d57bd0b8-4d29-1685-098c-c2f833b92974"))
//                .request(gate);
//
//        CustomerInfo calcInfo = CalcRequest.with()
//                .uid(UUID.fromString("d57bd0b8-4d29-1685-098c-c2f833b92974"))
//                .total(BigDecimal.valueOf(100))
//                .points(BigDecimal.valueOf(50))
//                .request(gate);

        int code = 28992;
        CardInfoEx cardInfoEx = gate.farCards604.getCardInfo(
                code,
                0,
                0,
                null,
                null
        );

        cardInfoEx = gate.farCards604.getCardInfo(
                code,
                0,
                0,
                null,
                null
        );

        boolean transactionOk = gate.farCards604.transactionsEx(
                new Transaction[]{
                        new Transaction(PAY, code, 143),
                        new Transaction(CASH, code, 110)
                }, null, null
        );
        boolean x = true;
    }

    //TODO переделать на зугрузку из файлика
    private final long companyId = 549755977357L;
    //TODO переделать на зугрузку из файлика
    private final String apiKey = "JlFbOjJ3UWYob0dUU0BaZyZfSlo1fkN+c2x6TSpKTXMwfnswNG4jXlVpZU5zalcjbFE=";
    private final String autorization = "Basic " + Base64.getEncoder().encodeToString((companyId + ":" + apiKey).getBytes());
    //TODO переделать на зугрузку из файлика
    private int unpayType = 999;
    //TODO переделать инициализацию на фабрику
    private final PayCodeService payCodeService = new PayCodeServiceInMemory();
    //TODO переделать инициализацию на фабрику
    private final CustomerService customerService = new CustomerServiceInMemory();
    public final FarCards604 farCards604 = new FarCards604();
    private ApplicationSettings settings;
    {
        loadSettings();
    }

    private synchronized void loadSettings() {
        settings = SettingsRequest.request(this);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public class FarCards604 {

        public CardInfoEx getCardInfo(long card, int restaurant, int unitNo, Document input, Document output) {
            CardInfoEx cardInfoEx = new CardInfoEx();
            BigDecimal orderSumm = extractOrderSumm(input);
            CustomerInfo customerInfo = getCustomerInfo(card, orderSumm);

            if (isNull(customerInfo) || customerInfo.isError()) {
                cardInfoEx.exists = false;
                cardInfoEx.infoForDisplay = customerInfo != null ? customerInfo.getErrorMessage() : null;

                return cardInfoEx;
            }

            PayAccountEntity payAccountEntity = payCodeService.findUnused(card);

            cardInfoEx.exists = true;
            cardInfoEx.needToWithdraw = false;
            cardInfoEx.expired = false;
            cardInfoEx.paused = false;
            cardInfoEx.managerConfirmationRequired = false;
            cardInfoEx.blocked = false;
            cardInfoEx.ownerName = payAccountEntity.getCustomer().getDisplayName();
            cardInfoEx.customerId = payAccountEntity.getCustomer().getInternalId();
            cardInfoEx.account = (int)(long)payAccountEntity.getInternalId();
            cardInfoEx.unpayType = unpayType;
            cardInfoEx.bonusId = cardInfoEx.discountId = getLoyaltyLevelId(customerInfo.getUser());
            cardInfoEx.maxDiscountSumm = customerInfo.getPurchase()
                    .getDiscountAmount()
                    .multiply(BigDecimal.valueOf(100)).longValue();
            cardInfoEx.account1Amount = customerInfo.getPurchase()
                    .getPoints()
                    .multiply(BigDecimal.valueOf(100)).longValue();
            cardInfoEx.account2Amount = 0;
            cardInfoEx.account3Amount = 0;
            cardInfoEx.account4Amount = 0;
            cardInfoEx.account5Amount = 0;
            cardInfoEx.account6Amount = 0;
            cardInfoEx.account7Amount = 0;
            cardInfoEx.account8Amount = 0;
            cardInfoEx.cardInfo = customerInfo.getUser().getDisplayName();
            cardInfoEx.infoForDisplay = CARD_INFO;
            cardInfoEx.infoForPrint = CARD_INFO;

            return cardInfoEx;
        }

        public boolean transactionsEx(Transaction[] transactions, Document input, Document output) {
            PayAccountEntity payAccountEntity = payCodeService.findUnused(transactions[0].card);

            OperationRequest.OperationRequestBuilder builder = OperationRequest.create();
            BigDecimal total = BigDecimal.valueOf(0);
            BigDecimal summ;
            BigDecimal skipLoyaltyTotal = BigDecimal.valueOf(0);

            for (Transaction transaction: transactions) {
                switch (transaction.type) {
                    case PAY:
                        summ = pennyToDecimal(-transaction.summ);
                        total = total.add(summ);
                        builder = builder.cash(summ).points(summ);
                        break;

                    case CASH:
                        summ = pennyToDecimal(transaction.summ);
                        total = total.add(summ);
                        break;

                    case DISCOUNT:
                        summ = pennyToDecimal(transaction.summ);
                        total = total.add(summ);
                        skipLoyaltyTotal = skipLoyaltyTotal.add(summ);
                        break;

                    case BONUS:
                        summ = pennyToDecimal(transaction.summ);
                        skipLoyaltyTotal = skipLoyaltyTotal.add(summ);
                        break;

                    default:
                        break;
                }
            }

            return !builder.total(total)
                    .skipLoyaltyTotal(skipLoyaltyTotal)
                    .code(payAccountEntity.getLongPlayCode())
                    .request(UdsGate.this)
                    .isError();
        }

        private CustomerInfo getCustomerInfo(long card, BigDecimal orderSumm) {
            PayAccountEntity payAccountEntity = payCodeService.findUnused(card);

            if (isNull(payAccountEntity)) {
                CustomerInfo customerInfo = FindCustomerRequest.findWith()
                        .code(String.format("%06d", card))
                        .total(orderSumm)
                        .exchangeCode(true)
                        .request(UdsGate.this);

                if (!customerInfo.isError()) {
                    payCodeService.persist(
                            card,
                            customerInfo.getCode(),
                            orderSumm,
                            customerService.persist(customerInfo.getUser())
                    );
                }

                return customerInfo;
            }

            return CalcRequest.with()
                    .code(payAccountEntity.getLongPlayCode())
                    .total(orderSumm)
                    .request(UdsGate.this);
        }
    }

    private BigDecimal pennyToDecimal(long summ) {
        return BigDecimal.valueOf(summ)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal extractOrderSumm(Document input) {
        //TODO написать реальный разбор данных
        return BigDecimal.valueOf(143);
    }

    private int getLoyaltyLevelId(Customer user) {
        if (isNull(user)
                || isNull(user.getMembershipTier())
                || StringUtils.isEmpty(user.getMembershipTier().getUid())) {
            return 0;
        }

        String membershipUid = user.getMembershipTier().getUid();

        return getLoyaltyLevelId(membershipUid)
                .orElseGet(() -> {
                    loadSettings();
                    return getLoyaltyLevelId(membershipUid)
                            .orElse(0);
                });
    }

    private Optional<Integer> getLoyaltyLevelId(String membershipUid) {
        if (isNull(settings.getLoyaltyProgramSettings())
                || isNull(settings.getLoyaltyProgramSettings().getMembershipTiers())) {
            return Optional.empty();
        }

        List<MembershipTier> bonusTiers = settings.getLoyaltyProgramSettings().getMembershipTiers();
        int length = bonusTiers.size();

        for(int i = 0; i < length; ++i) {
            if (membershipUid.equals(bonusTiers.get(i).getUid())) {
                return Optional.of(++i);
            }
        }

        return Optional.empty();
    }
}
