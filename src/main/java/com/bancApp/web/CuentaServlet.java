package com.bancApp.web;

import com.bancApp.HibernateUtil;
import com.bancApp.model.AccountEntity;
import com.bancApp.repository.AccountDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MostrarCuentaServlet", urlPatterns = "/ShowAcc")
public class CuentaServlet extends HttpServlet {
    private static PrintWriter printWriter;

    private static boolean isCompteExiste(AccountDaoImpl compteDao, String inputIdFiscal) {
        return compteDao.findById(inputIdFiscal) != null;
    }

    private static void printCompte(HttpServletResponse response, AccountEntity compte) throws IOException {
        response.setContentType("text/html");
        printWriter = response.getWriter();
        printWriter.println("<HTML>");
        printWriter.println("<BODY>");
        printWriter.println("<H1>Iban: " + compte.getIban() + "</H1>");
        printWriter.println("<p>" + "Nombre Cliente: " + compte.getCompteClientEntity()
                                                               .getNom() + "</p>");
        printWriter.println("<p>" + "Saldo:" + compte.getSaldo() + "</p>");
        printWriter.println("</BODY>");
        printWriter.println("</HTML>");
    }

    private static void printCompteNoExisteix(HttpServletResponse response, String inputIdFiscal) throws IOException {
        response.setContentType("text/html");
        printWriter = response.getWriter();
        printWriter.println("<HTML>");
        printWriter.println("<BODY>");
        printWriter.println("<H1>: " + inputIdFiscal + "</H1>");
        printWriter.println("</BODY>");
        printWriter.println("</HTML>");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction()
               .begin();

        AccountDaoImpl compteDao = new AccountDaoImpl(sessionFactory);

        String inputIdFiscal = request.getParameter("ibanBuscar");

        if (isCompteExiste(compteDao, inputIdFiscal)) {
            AccountEntity compte = compteDao.findById(inputIdFiscal);
            printCompte(response, compte);
        } else {
            printCompteNoExisteix(response, inputIdFiscal);
        }
    }
}
