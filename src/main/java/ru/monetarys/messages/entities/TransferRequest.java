package ru.monetarys.messages.entities;

import lombok.Data;

@Data
public class TransferRequest {

    private Header header;
    private Sender sender;
    private Receiver receiver;
    private IbsoData ibsoData;

}
