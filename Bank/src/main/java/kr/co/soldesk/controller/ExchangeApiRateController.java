package kr.co.soldesk.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncbank.beans.ExchangeApiRateBean;
import ncbank.service.ExchangeApiRateService;

@WebServlet("/exchange/exchangeRateApi")
public class ExchangeApiRateController extends HttpServlet {

    private ExchangeApiRateService exchangeApiRateService;

    public ExchangeApiRateController() {
        this.exchangeApiRateService = new ExchangeApiRateService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        List<ExchangeApiRateBean> exchangeRateApi = exchangeApiRateService.getAllExchangeRates();
        request.setAttribute("exchangeRateApi", exchangeRateApi);
        request.getRequestDispatcher("/WEB-INF/views/exchange/exchangeRateApi.jsp").forward(request, response);
    }
}
