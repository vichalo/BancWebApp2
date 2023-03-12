package com.bancApp.web;

import com.bancApp.ClientService;
import com.bancApp.model.ClientEntity;
import com.bancApp.model.AccountEntity;
import com.bancApp.repository.ClientDaoImpl;
import com.bancApp.repository.AccountDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "create User", urlPatterns = "/createUser")
public class ClientServlet extends HttpServlet {
    private static PrintWriter printWriter;
    private String message;
    private static final long serialVersionUID = 1L;
    public static final String CREATE_SUCCESS_MESSAGE = "¡Nuevo cliente y cuenta creados!";
    public static final String ADD_ACCOUNT_SUCCESS_MESSAGE = "¡Cuenta agregada al cliente existente!";

    private ClientService clientService;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = "";
        try {
            String name = req.getParameter("inputNombreCliente");
            String fiscalId = req.getParameter("inputIdFiscal");
            String email = req.getParameter("inputEmail");
            String country = req.getParameter("inputPais");
            String accountNumber = req.getParameter("inputCuenta");
            int initialDeposit = Integer.parseInt(req.getParameter("inputIngresoInicial"));

            message = clientService.addClientAndAccount(name, fiscalId, email, country, accountNumber, initialDeposit);
        } catch (NumberFormatException e) {
            message = "El valor de Ingreso Inicial no es válido.";
        } catch (Exception e) {
            message = "Hubo un error al procesar la solicitud. Por favor, inténtalo de nuevo más tarde.";
            e.printStackTrace();
        }

        req.setAttribute("message", message);
    }

    public void destroy() {
    }
}
