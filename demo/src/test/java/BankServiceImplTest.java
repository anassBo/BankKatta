import com.example.demo.constant.TransactionType;
import com.example.demo.exceptions.UnauthorizedNullTransaction;
import com.example.demo.models.Transaction;
import com.example.demo.services.impl.BankServiceImpl;
import com.google.common.util.concurrent.AtomicDouble;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BankServiceImplTest {
    private BankServiceImpl bankServiceImpl;

    @Before
    public void setUp(){
        bankServiceImpl = new BankServiceImpl();
        bankServiceImpl.addToCache();
    }

    @Test(expected = UnauthorizedNullTransaction.class)
    public void should_throw_exception_when_transation_is_null() {
        bankServiceImpl.deposit(null);
    }

    @Test
    public void should_deposit_to_account() throws IllegalArgumentException,
            IllegalAccessException, SecurityException, NoSuchFieldException {
        // Given
        Transaction transaction = buildTransaction(TransactionType.DEPOSIT);
        Map<String, AtomicDouble> accountMap = getAccountMap();

        // When
        bankServiceImpl.deposit(transaction);

        // Then
        assertTrue(accountMap.containsKey("1"));
        assertEquals("Account balance is not correct", 110d, accountMap.get("1").doubleValue(), 0);
    }

    @Test
    public void should_withdrawl_from_account() throws IllegalArgumentException,
            IllegalAccessException, SecurityException, NoSuchFieldException {
        // Given
        Transaction transaction = buildTransaction(TransactionType.WITHDRAWL);
        Map<String, AtomicDouble> accountMap = getAccountMap();
        // reset amount for accountId since it could be affected by another transaction deposit or withdrawl
        resetAmountForAccountId(accountMap, transaction);

        // When
        bankServiceImpl.withdrawl(transaction);

        // Then
        assertTrue(accountMap.containsKey("1"));
        assertEquals("Account balance is not correct", 90d, accountMap.get("1").doubleValue(), 0);
    }

    private Map<String, AtomicDouble> getAccountMap() throws IllegalArgumentException,
            IllegalAccessException, SecurityException, NoSuchFieldException {
        Field accountMapField = bankServiceImpl.getClass().getDeclaredField("accountMap");
        accountMapField.setAccessible(true);
        return (Map<String, AtomicDouble>) accountMapField.get(bankServiceImpl);
    }

    private Transaction buildTransaction(TransactionType transactionType) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setCurrentBalance(100d);
        transaction.setAccountId(1l);
        transaction.setAmount(10d);
        return transaction;
    }

    private void resetAmountForAccountId(Map<String, AtomicDouble> accountMap, Transaction transaction){
        accountMap.put(transaction.getAccountId().toString(), new AtomicDouble(transaction.getCurrentBalance()));
    }

}
