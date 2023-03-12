package com.bancApp;

import com.bancApp.model.AccountEntity;
import com.bancApp.model.ClientEntity;
import com.bancApp.repository.AccountDaoImpl;
import com.bancApp.repository.ClientDaoImpl;
import com.bancApp.web.ClientServlet;

public class ClientService {

    private ClientDaoImpl clientDao;
    private AccountDaoImpl accountDao;

    public ClientService(ClientDaoImpl clientDao, AccountDaoImpl accountDao) {
        this.clientDao = clientDao;
        this.accountDao = accountDao;
    }

    public String addClientAndAccount(String name, String fiscalId, String email, String country, String accountNumber, int initialDeposit) {
        try {
            ClientEntity client = clientDao.findByDni(fiscalId);
            AccountEntity account = accountDao.findById(accountNumber);

            if (client == null && account == null) {
                // Nuevo cliente y cuenta
                client = new ClientEntity(name, fiscalId, email, country);
                account = new AccountEntity(accountNumber, initialDeposit, client);
                clientDao.save(client);
                accountDao.save(account);
                return ClientServlet.CREATE_SUCCESS_MESSAGE;
            } else if (client != null && account == null) {
                // Agregar cuenta a cliente existente
                account = new AccountEntity(accountNumber, initialDeposit, client);
                accountDao.save(account);
                return ClientServlet.ADD_ACCOUNT_SUCCESS_MESSAGE;
            } else {
                // Cliente y cuenta ya existen
                return "El cliente y la cuenta ya existen.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Hubo un error al procesar la solicitud.");
        }
    }
}