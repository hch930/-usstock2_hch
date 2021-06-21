package org.hch.security.stock.service;

import java.io.IOException;
import java.util.List;

import org.hch.security.stock.model.StockDto;
import org.hch.security.stock.model.StockHistory;

import yahoofinance.Stock;

public interface StockService {
	public StockDto getStockInfo(String stockName) throws Exception;
	
	public List<StockHistory> getHistory(String stockName, int year, String searchType) throws Exception;
	
	public Stock getStock(String stockName) throws IOException;
}
