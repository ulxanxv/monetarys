package ru.monetarys.domain.integration;

import lombok.Data;

import java.util.List;

@Data
public class ClientGeneralInfo {

    private String guid;

    private ClientPersonalInfo personalInfo;
    private List<ClientAccountInfo> accountList;
    private ClientContactsInfo contacts;

}
