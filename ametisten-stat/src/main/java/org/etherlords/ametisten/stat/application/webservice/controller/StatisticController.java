package org.etherlords.ametisten.stat.application.webservice.controller;

import java.util.UUID;

import org.etherlords.ametisten.stat.event.MessageRouter;
import org.etherlords.ametisten.stat.reporting.ReportRepository;
import org.etherlords.ametisten.stat.reporting.game.PlayerReport;
import org.etherlords.ametisten.stat.reporting.game.PlayerReportByNameQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statistic")
public class StatisticController {
	
	@Autowired
	private ReportRepository<PlayerReport, UUID> accountGamesReportRepo;
	
	@RequestMapping(value="/{accountName}", method=RequestMethod.GET)
	@ResponseBody
	public PlayerReport gameStat(@PathVariable("accountName") String accountName) {
		
		PlayerReportByNameQuery playerReportByNameQuery = new PlayerReportByNameQuery(accountName);
		
		accountGamesReportRepo.executeQuery(playerReportByNameQuery);
		
		if (!playerReportByNameQuery.getResultSet().hasMoreElements()) {
			throw new AccountNotFoundException("Can't find account with the given name: " + accountName, accountName);
		}
		
		return playerReportByNameQuery.getResultSet().nextElement();
	}

	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Account not found.")
	ModelAndView accountNotFoundExceptionHandler(AccountNotFoundException exception) {
		return new ModelAndView((String)null, "account_name", exception.getAccountName());
	}
	
}
