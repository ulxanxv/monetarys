package ru.monetarys.transfer.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferResponse {

    private TransactionResponse transaction;
    private PayerAccount payerAccount;
    private PayeeAccount payeeAccount;

}
