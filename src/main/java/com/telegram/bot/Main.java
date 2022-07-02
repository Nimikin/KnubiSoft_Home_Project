package com.telegram.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.telegram.bot.DB.User;
import com.telegram.bot.DB.UserRepository;
import com.telegram.bot.core.BotController;

public class Main {
	
	private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

	  try {
          TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
          botsApi.registerBot(new BotController());
      } catch (TelegramApiException e) {
          e.printStackTrace();
      }
   SpringApplication.run(Main.class, args);
  }
  
  @Bean
  public CommandLineRunner demo(UserRepository repository, Update upadte) {
    return (args) -> {
    	Update update = new Update();
    	String firstName = update.getMessage().getChat().getFirstName();
    	String lastName = update.getMessage().getChat().getLastName();
      // save a few customers
      repository.save(new User(firstName, lastName));

      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      for (User user : repository.findAll()) {
        log.info(user.toString());
      }
      log.info("");
      
    };
  }
}